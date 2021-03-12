package io.dfjx.module.data.service.impl;

import io.dfjx.datasource.annotation.DataSource;
import io.dfjx.module.data.dao.MhDataAccessExpandDetailsMapper;
import io.dfjx.module.data.entity.MhDataAccessExpandDetails;
import io.dfjx.module.data.service.IMhDataAccessExpandDetailsService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@DataSource(value = "postGreSource")

@Service("mhDataAccessExpandDetailsService")
public class MhDataAccessExpandDetailsServiceImpl extends ServiceImpl<MhDataAccessExpandDetailsMapper, MhDataAccessExpandDetails> implements IMhDataAccessExpandDetailsService {

    @Autowired
    private MhDataAccessExpandDetailsMapper mapper;

}
