package io.dfjx.module.data.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.dfjx.module.data.entity.OrgExchangeSumM;
import io.dfjx.module.data.vo.ShareOrganTop5Vo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Title:io.dfjx.module.query.controller
 * @Description:
 * @Author: 陈松
 * @Date: 2021/3/6 16:57
 * @Version: 1.0
 */
@Repository
@Mapper
public interface OrgExchangeSumMMapper extends BaseMapper<OrgExchangeSumM> {

	@Select({
			" SELECT  " +
					"         tgt_org_name AS tgtOrgName,  " +
					"         round(cast(exchange_sum as numeric )/10000 ,2)  AS exchangeSum  " +
					"         FROM  " +
					"         gxjh.org_exchange_sum_m  " +
					"         ORDER BY  " +
					"         CAST ( exchange_sum AS NUMERIC ) DESC limit 5"
	})
	List<ShareOrganTop5Vo> shareOrganTop5();
}
