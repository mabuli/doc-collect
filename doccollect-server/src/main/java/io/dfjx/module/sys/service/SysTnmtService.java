package io.dfjx.module.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.dfjx.common.utils.PageUtils;
import io.dfjx.module.sys.entity.SysTnmtEntity;

import java.util.Map;

/**
 * 租户ID
 *
 * @author mazong
 * @email mazong@gmail.com
 * @date 2019-08-18 21:17:59
 */
public interface SysTnmtService extends IService<SysTnmtEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

