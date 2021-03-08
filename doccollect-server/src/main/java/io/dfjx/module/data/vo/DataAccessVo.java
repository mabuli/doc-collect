package io.dfjx.module.data.vo;/**
 * <h3>doc-collect</h3>
 * <p>数据接入情况</p>
 *
 * @author : PanhuGao
 * @date : 2021-03-07 15:37
 **/

import lombok.Data;

import java.math.BigDecimal;

/**
 * @创建人 PanHu.Gao
 * @创建时间 2021/3/7
 * @描述
 */
@Data
public class DataAccessVo {

    /**
     * 接入委办局
     */
    private String tolWbjAmount;
    /**
     * 接入系统数
     */
    private String sysId;
    /**
     * 接入表数
     */
    private String tolTabAmount;
    /**
     * 接入字段数
     */
    private String toleFiledCount;
    /**
     * 接入数据记录
     */
    private BigDecimal actRecodAmount;
}
