package io.dfjx.module.data.service.impl;

import io.dfjx.datasource.annotation.DataSource;
import io.dfjx.module.data.dao.OrgExchangeSumYMapper;
import io.dfjx.module.data.entity.OrgExchangeSumY;
import io.dfjx.module.data.service.IOrgExchangeSumYService;
import io.dfjx.module.data.vo.ShareOrganTop5Vo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DataSource(value = "postGreSource")

@Service("orgExchangeSumYService")
public class OrgExchangeSumYServiceImpl extends ServiceImpl<OrgExchangeSumYMapper, OrgExchangeSumY> implements IOrgExchangeSumYService {

    @Autowired
    private OrgExchangeSumYMapper mapper;

    @Override
    public List<ShareOrganTop5Vo> shareOrganTop5() {
        return mapper.shareOrganTop5();
    }
}
