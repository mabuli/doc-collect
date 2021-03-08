package io.dfjx.module.data.vo;/**
 * <h3>doc-collect</h3>
 * <p>法人库信息</p>
 *
 * @author : PanhuGao
 * @date : 2021-03-07 16:46
 **/

import lombok.Data;

/**
 * @创建人 PanHu.Gao
 * @创建时间 2021/3/7
 * @描述
 */
@Data
public class PopuNumVo {

    /**
     * 大兴区人口数据
     */
    private String exstPopuNum;

    /**
     *大兴区法人数据
     */
    private String exstLegalNum;
}
