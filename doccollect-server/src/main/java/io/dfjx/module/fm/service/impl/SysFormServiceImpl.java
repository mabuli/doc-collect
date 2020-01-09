package io.dfjx.module.fm.service.impl;

import io.dfjx.core.base.SysBaseEntity;
import io.dfjx.module.fm.dao.FmComDao;
import io.dfjx.module.fm.service.ISysFormService;
import org.springframework.stereotype.Service;

@Service("sysFormService")
public class SysFormServiceImpl extends FmBaseServiceImpl<FmComDao, SysBaseEntity> implements ISysFormService {
    public SysFormServiceImpl(){
        BaseTable = "sys_form";
        BaseKey = "id";
    }
}
