package io.dfjx.module.data.service.impl;

import io.dfjx.datasource.annotation.DataSource;
import io.dfjx.module.data.dao.SumMhDataAccessMapper;
import io.dfjx.module.data.entity.SumMhDataAccess;
import io.dfjx.module.data.service.ISumMhDataAccessService;
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
@Service("sumMhDataAccessService")
public class SumMhDataAccessServiceImpl extends ServiceImpl<SumMhDataAccessMapper, SumMhDataAccess> implements ISumMhDataAccessService {

    @Autowired
    private SumMhDataAccessMapper mapper;

}
