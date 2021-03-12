package io.dfjx.module.data.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.dfjx.module.data.entity.IdzmOrgExchangeOrgRelation;
import io.dfjx.module.data.vo.ShareRelationOrganVo;

import java.util.List;

/**
 * 
 *
 * @author ccf
 * @email 674441755@qq.com
 * @date 2021-03-07 15:09:23
 */

public interface IdzmOrgExchangeOrgRelationService extends IService<IdzmOrgExchangeOrgRelation> {

	List<IdzmOrgExchangeOrgRelation> listIdzmOrgExchangeOrgRelation();

	List<ShareRelationOrganVo> listShareRelationOrganVo();

}

