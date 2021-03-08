package io.dfjx.module.data.controller;/**
 * <h3>doc-collect</h3>
 * <p>数据汇聚</p>
 *
 * @author : PanhuGao
 * @date : 2021-03-07 15:24
 **/

import io.dfjx.datasource.annotation.DataSource;
import io.dfjx.module.data.enums.DateEnum;
import io.dfjx.module.data.service.DataCollectService;
import io.dfjx.module.data.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * @创建人 PanHu.Gao
 * @创建时间 2021/3/7
 * @描述
 */
@RestController
@DataSource(value ="postGreSource")
@RequestMapping("/dataCollect")
public class DataCollectController {

    @Autowired
    private DataCollectService dataCollectService;
    /**
     * 数据接入情况
     */
    @ResponseBody
    @RequestMapping(value = "/dataAccess", method = RequestMethod.GET)
     public DataAccessVo dataAccessInfo(){
        return dataCollectService.dataAccessInfoService();
     }
    /**
     * 系统接入领域分布
     */
    @ResponseBody
    @RequestMapping(value = "/sysAcess", method = RequestMethod.GET)
     public List<SysAccessVo> sysAcessInfo(){
        return dataCollectService.sysAcessInfoService();
     }
    /**
     * 数据记录接入趋势
     */
    @ResponseBody
    @RequestMapping(value = "/recordTrend", method = RequestMethod.GET)
     public List<RecordTrendVo> recordTrend(DateEnum dateEnum){
         return dataCollectService.recordTrendService(dateEnum);
     }
    /**
     * 人口/法人库建设情况
     */
    @ResponseBody
    @RequestMapping(value = "/daXingData", method = RequestMethod.GET)
      public PopuNumVo popuNum(){
         return dataCollectService.popuNumService();
      }

    /**
     * 接入数据记录Top10委办局
     */
    @ResponseBody
    @RequestMapping(value = "/recordAccess", method = RequestMethod.GET)
      public List<RecordAccessTopVo> recordAccessTop10(DateEnum dateEnum){
        return dataCollectService.recordAccessTop10Service(dateEnum);
      }

    /**
     * 接入数据条数
     */
    @ResponseBody
    @RequestMapping(value = "/accessRecord", method = RequestMethod.GET)
    public  AccessRecordVo  accessRecord(){
      return dataCollectService.accessRecordService();
    }

    /**
     * 委办局数据
     */
    @ResponseBody
    @RequestMapping(value = "/orgInfo", method = RequestMethod.GET)
    public  List<OrgInfoVo>  orgInfo(){
        return dataCollectService.wbjInfo();
    }

}
