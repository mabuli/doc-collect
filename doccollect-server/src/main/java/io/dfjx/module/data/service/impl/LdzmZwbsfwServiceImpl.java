package io.dfjx.module.data.service.impl;

import io.dfjx.datasource.annotation.DataSource;
import io.dfjx.module.data.dao.LdzmZwbsfwMapper;
import io.dfjx.module.data.entity.LdzmZwbsfw;
import io.dfjx.module.data.service.ILdzmZwbsfwService;
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

@Service("ldzmZwbsfwService")
public class LdzmZwbsfwServiceImpl extends ServiceImpl<LdzmZwbsfwMapper, LdzmZwbsfw> implements ILdzmZwbsfwService {

    @Autowired
    private LdzmZwbsfwMapper mapper;

}
