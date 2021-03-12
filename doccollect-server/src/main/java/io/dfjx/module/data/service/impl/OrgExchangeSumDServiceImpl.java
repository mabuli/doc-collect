package io.dfjx.module.data.service.impl;

import io.dfjx.datasource.annotation.DataSource;
import io.dfjx.module.data.dao.OrgExchangeSumDMapper;
import io.dfjx.module.data.entity.OrgExchangeSumD;
import io.dfjx.module.data.service.IOrgExchangeSumDService;
import io.dfjx.module.data.vo.ShareOrganTop5Vo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DataSource(value = "postGreSource")

@Service("orgExchangeSumDService")
public class OrgExchangeSumDServiceImpl extends ServiceImpl<OrgExchangeSumDMapper, OrgExchangeSumD> implements IOrgExchangeSumDService {

    @Autowired
    private OrgExchangeSumDMapper mapper;


    @Override
    public List<ShareOrganTop5Vo> shareOrganTop5() {
        return mapper.shareOrganTop5();
    }
}
