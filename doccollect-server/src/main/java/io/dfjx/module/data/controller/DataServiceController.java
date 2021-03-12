package io.dfjx.module.data.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.dfjx.datasource.annotation.DataSource;
import io.dfjx.module.data.entity.*;
import io.dfjx.module.data.enums.DateEnum;
import io.dfjx.module.data.service.*;
import io.dfjx.module.data.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Title:io.dfjx.module.query.controller
 * @Description:
 * @Author: 陈松
 * @Date: 2021/3/6 16:57
 * @Version: 1.0
 */
@DataSource(value = "postGreSource")
@RestController
@RequestMapping("/dataService")
public class DataServiceController {


	@Autowired
	private IOrgExchangeSumYService orgExchangeSumYService;

	@Autowired
	private IOrgExchangeSumDService orgExchangeSumDService;

	@Autowired
	private IOrgExchangeSumMService orgExchangeSumMService;


	@Autowired
	private IExchangeSumTaskService exchangeSumTaskService;

	@Autowired
	private IExchangeDTrendService exchangeDTrend;

	@Autowired
	private IDxApiListService dxApiListService;

	@Autowired
	private ILdzmGkOrgService ldzmGkOrgService;

	@Autowired
	private ILdzmGjfwService ldzmGjfwService;

	@Autowired
	private IdzmOrgExchangeOrgRelationService idzmOrgExchangeOrgRelationService;

	/**
	 * 共享交换活跃机构top5
	 *
	 * @param dateEnum
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/shareOrganTop5", method = RequestMethod.GET)
	public List<ShareOrganTop5Vo> shareOrganTop5(DateEnum dateEnum) {
		List<ShareOrganTop5Vo> result = new ArrayList<>();
		if (DateEnum.MONTH.equals(dateEnum)) {
			result = orgExchangeSumMService.shareOrganTop5();
		} else if (DateEnum.HALF_YEAR.equals(dateEnum)) {
			result = orgExchangeSumYService.shareOrganTop5();
		} else if (DateEnum.ALL.equals(dateEnum)) {
			result = orgExchangeSumDService.shareOrganTop5();
		}

		return result;
	}

	/**
	 * 共享交换活跃机构、任务、数据 量
	 *
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/shareNum", method = RequestMethod.GET)
	public ShareNumVo shareNum() {
		List<ExchangeSumTask> list = exchangeSumTaskService.listExchangeSumTask();
		if (!CollectionUtils.isEmpty(list)) {
			ExchangeSumTask exchangeSumTask = list.get(0);
			return ExchangeSumTask.ofShareNumVo(exchangeSumTask);
		}
		return new ShareNumVo();
	}

	/**
	 * 共享交换数据量变化趋势
	 *
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/exchangeTrend", method = RequestMethod.GET)
	public List<ExchangeDTrendVo> exchangeTrend(DateEnum dateEnum) {
		IPage<ExchangeDTrend> page;
		if (DateEnum.WEEK.equals(dateEnum)) {
			page = new Page<>(1, 7);
		} else if (DateEnum.MONTH.equals(dateEnum)) {
			page = new Page<>(1, 30);
		} else {
			return new ArrayList<>();
		}
		return exchangeDTrend.listExchangeDTrend(page).stream().map(ExchangeDTrend::ofExchangeDTrendVo).collect(Collectors.toList());
	}

	/**
	 * 重点平台支撑情况
	 *
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/getTerritoryShareRatio", method = RequestMethod.GET)
	public TerraceSupportVo getTerraceSupportVo() {

		return dxApiListService.getTerraceSupportVo();
	}

	/**
	 * 各领域共享交换数量占比
	 *
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/listDomainShare", method = RequestMethod.GET)
	public List<DomainShareVo> listDomainShare() {
		return ldzmGkOrgService.listDomainShare();
	}

	/**
	 * 工具服务
	 *
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/listToolsServiceVo", method = RequestMethod.GET)
	public List<ToolsServiceVo> listToolsServiceVo() {
		return ldzmGjfwService.listToolsServiceVo();
	}

	/**
	 * 共享关系部门图
	 *
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/shareRelation", method = RequestMethod.GET)
	public ShareRelationVo shareRelation() {
		ShareRelationVo result = new ShareRelationVo();
		List<ShareRelationOrganVo> data = new ArrayList<>();
		List<LinkVo> links = new ArrayList<>();
		// 关联关系
		List<IdzmOrgExchangeOrgRelation> idzmOrgExchangeOrgRelations = idzmOrgExchangeOrgRelationService.listIdzmOrgExchangeOrgRelation();
		// 所有的源部门
		List<ShareRelationOrganVo> shareRelationOrganVos = idzmOrgExchangeOrgRelationService.listShareRelationOrganVo();
		Map<String, ShareRelationOrganVo> tempMap = shareRelationOrganVos.stream().collect(Collectors.toMap(ShareRelationOrganVo::getId, item -> item));
		List<Long> list = shareRelationOrganVos.stream().map(item -> item.getValue()).collect(Collectors.toList());
		Map<Long, Double> normalize = this.normalize(list, 15D, 50D);
		shareRelationOrganVos.stream().forEach(item -> {
			Double aLong = normalize.get(item.getValue());
			item.setSymbolSize(aLong);
			data.add(item);
		});
		idzmOrgExchangeOrgRelations.stream().forEach(item -> {
			ShareRelationOrganVo shareRelationOrganVo = tempMap.get(item.getTgtOrgName());
			if (shareRelationOrganVo == null) {
				ShareRelationOrganVo model = new ShareRelationOrganVo();
				model.setId(item.getTgtOrgName());
				model.setName(item.getTgtOrgName());
				model.setValue(null);
				model.setSymbolSize(10D);
				data.add(model);
			}
			LinkVo linkVo = new LinkVo();
			linkVo.setId(item.getSrcOrgName());
			linkVo.setSource(item.getSrcOrgName());
			linkVo.setTarget(item.getTgtOrgName());
			links.add(linkVo);

		});
		result.setData(data);
		result.setLinks(links);

		return result;
	}

	/**
	 * 数据归一化到a至b
	 * <p>
	 * （1）首先找到原本样本数据X的最小值Min及最大值Max
	 * （2）计算系数：k=（b-a)/(Max-Min)
	 * （3）得到归一化到[a,b]区间的数据：Y=a+k(X-Min) 或者 Y=b+k(X-Max)
	 *
	 * @param list
	 * @return
	 */
	public Map<Long, Double> normalize(List<Long> list, Double a, Double b) {
		Map<Long, Double> map = new HashMap<>();
		Long maxNum = list.stream().max(Comparator.comparing(Long::valueOf)).get();
		Long minNum = list.stream().min(Comparator.comparing(Long::valueOf)).get();
		Double k = (b - a) / (maxNum - minNum);
		list.stream().forEach(item -> {
			Double value = a + k * (item - a);
			map.put(item, value);
		});

		return map;
	}


}
