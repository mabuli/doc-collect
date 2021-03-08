package io.dfjx.module.data.service;

import io.dfjx.module.data.enums.DateEnum;
import io.dfjx.module.data.vo.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @创建人 PanHu.Gao
 * @创建时间 2021/3/7
 * @描述
 */
public interface DataCollectService {
    /**
     * 数据接入情况
     */
    public DataAccessVo dataAccessInfoService();

    /**
     * 系统接入领域分布
     */
    public List<SysAccessVo> sysAcessInfoService();
    /**
     * 数据记录接入趋势
     */
    public List<RecordTrendVo> recordTrendService(DateEnum dateEnum);

    /**
     * 人口/法人库建设情况
     */
    public PopuNumVo popuNumService();

    /**
     * 接入数据记录Top10委办局
     */
    public List<RecordAccessTopVo> recordAccessTop10Service(DateEnum dateEnum);

    /**
     * 接入数据条数
     */
    public  AccessRecordVo  accessRecordService();

    /**
     * 委办局信息
     */
    public List<OrgInfoVo> wbjInfo();
}
