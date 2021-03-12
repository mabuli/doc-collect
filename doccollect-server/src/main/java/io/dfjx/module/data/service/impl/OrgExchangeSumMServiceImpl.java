package io.dfjx.module.data.service.impl;

import io.dfjx.datasource.annotation.DataSource;
import io.dfjx.module.data.dao.OrgExchangeSumMMapper;
import io.dfjx.module.data.entity.OrgExchangeSumM;
import io.dfjx.module.data.service.IOrgExchangeSumMService;
import io.dfjx.module.data.vo.ShareOrganTop5Vo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DataSource(value = "postGreSource")

@Service("orgExchangeSumMService")
public class OrgExchangeSumMServiceImpl extends ServiceImpl<OrgExchangeSumMMapper, OrgExchangeSumM> implements IOrgExchangeSumMService {

    @Autowired
    private OrgExchangeSumMMapper mapper;


    @Override
    public List<ShareOrganTop5Vo> shareOrganTop5() {
        return mapper.shareOrganTop5();
    }
}
