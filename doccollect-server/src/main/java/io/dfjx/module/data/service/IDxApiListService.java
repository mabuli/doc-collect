package io.dfjx.module.data.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.dfjx.module.data.entity.DxApiList;
import io.dfjx.module.data.vo.TerraceSupportVo;

/**
 * 
 *
 * @author ccf
 * @email 674441755@qq.com
 * @date 2021-03-07 17:22:06
 */
public interface IDxApiListService extends IService<DxApiList> {

    TerraceSupportVo getTerraceSupportVo();

}

