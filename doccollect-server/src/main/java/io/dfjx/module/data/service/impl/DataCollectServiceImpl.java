package io.dfjx.module.data.service.impl;
/**
 * <h3>doc-collect</h3>
 * <p>数据汇聚</p>
 *
 * @author : PanhuGao
 * @date : 2021-03-07 17:12
 **/

import io.dfjx.datasource.annotation.DataSource;
import io.dfjx.module.data.dao.SumMhDataAccessMapper;
import io.dfjx.module.data.enums.DateEnum;
import io.dfjx.module.data.service.DataCollectService;
import io.dfjx.module.data.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @创建人 PanHu.Gao
 * @创建时间 2021/3/7
 * @描述
 */
@Service
public class DataCollectServiceImpl implements DataCollectService {
  @Autowired
  private SumMhDataAccessMapper dataAccessMapper;

    @Override
    public DataAccessVo dataAccessInfoService() {
        DataAccessVo accessData = dataAccessMapper.getAccessData();
        if(ObjectUtils.isEmpty(accessData)){
            accessData = new DataAccessVo();
        }
        //接入系统
        String accessSystem = dataAccessMapper.getAccessSystem();
        //接入字段
        String fieldCount = dataAccessMapper.getFieldCount();
        accessData.setSysId(accessSystem);
        accessData.setToleFiledCount(fieldCount);
        return accessData;
    }

    @Override
    public List<SysAccessVo> sysAcessInfoService() {
        return dataAccessMapper.getSysAccess();
    }

    @Override
    public List<RecordTrendVo> recordTrendService(DateEnum dateEnum) {
        if(DateEnum.WEEK.equals(dateEnum)){
            return dataAccessMapper.getRecordTrendSeven();
        }else if(DateEnum.MONTH.equals(dateEnum)){
            return dataAccessMapper.getRecordTrendThirty();
        }else {
            return new ArrayList<>();
        }

    }

    @Override
    public PopuNumVo popuNumService() {
        PopuNumVo popuNumVo= new PopuNumVo();
        String dxFp = dataAccessMapper.getDxFp();
        String legelCn = dataAccessMapper.getLegelCn();
         popuNumVo.setExstLegalNum(legelCn);
         popuNumVo.setExstPopuNum(dxFp);
        return popuNumVo;
    }

    @Override
    public List<RecordAccessTopVo> recordAccessTop10Service(DateEnum dateEnum) {
        List<RecordAccessTopVo> res = new ArrayList<RecordAccessTopVo>();
        if(DateEnum.ALL.equals(dateEnum)){
            List<RecordAccessTopVo> recordAccessTopAll = dataAccessMapper.getRecordAccessTopAll();
            return  dataAccessMapper.getRecordAccessTopAll();
        }else if(DateEnum.MONTH.equals(dateEnum)){
            return  dataAccessMapper.getRecordAccessTopMonth();
        }else if(DateEnum.HALF_YEAR.equals(dateEnum)){
            return  dataAccessMapper.getRecordAccessTopYear();
        }else {
            return  res;
        }

    }

    @Override
    public AccessRecordVo accessRecordService() {
        AccessRecordVo accessRecordVo = new AccessRecordVo();
        String accessRate = dataAccessMapper.getAccessRate();
        String accessTotal = dataAccessMapper.getAccessRecordToal();
        accessRecordVo.setCurrRecodAmount(accessTotal);
        accessRecordVo.setAddRate(accessRate);
        return accessRecordVo;
    }

    @Override
    public List<OrgInfoVo> wbjInfo() {
        return dataAccessMapper.getWblInfo();
    }
}
