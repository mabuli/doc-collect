package io.dfjx.module.data.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.dfjx.module.data.entity.LdzmGkOrg;
import io.dfjx.module.data.vo.DomainShareVo;

import java.util.List;

/**
 * @Title:io.dfjx.module.query.controller
 * @Description:
 * @Author: 陈松
 * @Date: 2021/3/6 16:57
 * @Version: 1.0
 */
public interface ILdzmGkOrgService extends IService<LdzmGkOrg> {

	List<DomainShareVo> listDomainShare();
}

