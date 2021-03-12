package io.dfjx.module.data.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.dfjx.module.data.entity.LdzmGkOrg;
import io.dfjx.module.data.vo.DomainShareVo;
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
public interface LdzmGkOrgMapper extends BaseMapper<LdzmGkOrg> {

	@Select({" select t0.gk_name" +
			" ,round((t0.cnt / cast((select count(*) from gxjh.ldzm_gk_org) as numeric))*100,2) || '%' as gkNameRatio " +
			"     from (select gk_name,count(*) as cnt from gxjh.ldzm_gk_org group by gk_name) t0"
	})
	List<DomainShareVo> listDomainShare();
}
