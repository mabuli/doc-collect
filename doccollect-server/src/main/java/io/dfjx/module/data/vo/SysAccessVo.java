package io.dfjx.module.data.vo;/**
 * <h3>doc-collect</h3>
 * <p>系统接入领域</p>
 *
 * @author : PanhuGao
 * @date : 2021-03-07 16:31
 **/

import lombok.Data;

/**
 * @创建人 PanHu.Gao
 * @创建时间 2021/3/7
 * @描述
 */
@Data
public class SysAccessVo {
    /**
     * 系统
     */
    private String gkName;

    /**
     * 占比
     */
    private String zb;

    /**
     * 数量
     */
    private String accessCount;
}
