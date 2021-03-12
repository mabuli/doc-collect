package io.dfjx.module.data.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.dfjx.datasource.annotation.DataSource;
import io.dfjx.module.data.entity.DxApiList;
import io.dfjx.module.data.entity.IdzmOrgExchangeOrgRelation;
import io.dfjx.module.data.vo.ShareRelationOrganVo;
import io.dfjx.module.data.vo.TerraceSupportVo;
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
public interface IdzmOrgExchangeOrgRelationMapper extends BaseMapper<IdzmOrgExchangeOrgRelation> {

	@Select({
			"select src_org_name as id,src_org_name as name ,sum(exchange_sum) as  value from gxjh.ldzm_org_exchange_org_relation group by src_org_name"
	})
	List<ShareRelationOrganVo> listShareRelationOrganVo();
}
