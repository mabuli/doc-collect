package io.dfjx.module.data.vo;/**
 * <h3>doc-collect</h3>
 * <p>数据记录趋势</p>
 *
 * @author : PanhuGao
 * @date : 2021-03-07 16:36
 **/

import lombok.Data;

/**
 * @创建人 PanHu.Gao
 * @创建时间 2021/3/7
 * @描述
 */
@Data
public class RecordTrendVo {
    /**
     * 记录时间
     */
    private String accessDate;

    /**
     * 记录数
     */
    private String currRecodAmount;
}
