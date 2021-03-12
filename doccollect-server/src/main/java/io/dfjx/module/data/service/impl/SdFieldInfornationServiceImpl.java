package io.dfjx.module.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.dfjx.datasource.annotation.DataSource;
import io.dfjx.module.data.dao.SdFieldInfornationMapper;
import io.dfjx.module.data.entity.SdFieldInfornation;
import io.dfjx.module.data.service.ISdFieldInfornationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@DataSource(value = "postGreSource")

@Service("sdFieldInfornationService")
public class SdFieldInfornationServiceImpl extends ServiceImpl<SdFieldInfornationMapper, SdFieldInfornation> implements ISdFieldInfornationService {

    @Autowired
    private SdFieldInfornationMapper mapper;


}
