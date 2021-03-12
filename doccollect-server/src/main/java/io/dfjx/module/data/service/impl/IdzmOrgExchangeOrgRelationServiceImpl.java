package io.dfjx.module.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.dfjx.datasource.annotation.DataSource;
import io.dfjx.module.data.dao.IdzmOrgExchangeOrgRelationMapper;
import io.dfjx.module.data.entity.IdzmOrgExchangeOrgRelation;
import io.dfjx.module.data.service.IdzmOrgExchangeOrgRelationService;
import io.dfjx.module.data.vo.ShareRelationOrganVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ASong
 */
@DataSource(value = "postGreSource")
@Service
public class IdzmOrgExchangeOrgRelationServiceImpl
		extends ServiceImpl<IdzmOrgExchangeOrgRelationMapper, IdzmOrgExchangeOrgRelation>
		implements IdzmOrgExchangeOrgRelationService {

	@Autowired
	private IdzmOrgExchangeOrgRelationMapper mapper;

	@Override
	public List<IdzmOrgExchangeOrgRelation> listIdzmOrgExchangeOrgRelation() {
		return this.lambdaQuery().list();
	}

	@Override
	public List<ShareRelationOrganVo> listShareRelationOrganVo() {
		return mapper.listShareRelationOrganVo();
	}
}
