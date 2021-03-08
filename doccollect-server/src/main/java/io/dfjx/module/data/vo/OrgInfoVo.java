package io.dfjx.module.data.vo;/**
 * <h3>doc-collect</h3>
 * <p>委办局信息</p>
 *
 * @author : PanhuGao
 * @date : 2021-03-08 02:44
 **/

import lombok.Data;

/**
 * @创建人 PanHu.Gao
 * @创建时间 2021/3/8
 * @描述
 */
@Data
public class OrgInfoVo {
    /**
     * 委办局名称
     */
    private String wbjName;

    /**
     * 机构级别
     */
    private String orgLevel;
}
