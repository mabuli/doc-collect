package io.dfjx.module.data.vo;/**
 * <h3>doc-collect</h3>
 * <p>接入数据记录Top10</p>
 *
 * @author : PanhuGao
 * @date : 2021-03-07 16:55
 **/

import lombok.Data;

/**
 * @创建人 PanHu.Gao
 * @创建时间 2021/3/7
 * @描述
 */
@Data
public class RecordAccessTopVo {
    /**
     * 委办局
     */
    private String comBureauNm;

    /**
     * 接入数量
     */
    private String sjlAmountW;


}
