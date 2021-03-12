package io.dfjx.module.data.service.impl;

import io.dfjx.datasource.annotation.DataSource;
import io.dfjx.module.data.dao.LdzmGkOrgMapper;
import io.dfjx.module.data.entity.LdzmGkOrg;
import io.dfjx.module.data.service.ILdzmGkOrgService;
import io.dfjx.module.data.vo.DomainShareVo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@DataSource(value = "postGreSource")

@Service("ldzmGkOrgService")
public class LdzmGkOrgServiceImpl
        extends ServiceImpl<LdzmGkOrgMapper, LdzmGkOrg> implements ILdzmGkOrgService {

    @Autowired
    private LdzmGkOrgMapper mapper;

    @Override
    public List<DomainShareVo> listDomainShare() {
        return mapper.listDomainShare();
    }
}
