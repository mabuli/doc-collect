package io.dfjx.module.fm.service;

import io.dfjx.common.utils.R;
import io.dfjx.core.base.SysBaseEntity;

import java.util.Map;

/**
 * 项目信息表
 *
 * @author mazong
 * @email mazong@gmail.com
 * @date 2019-12-23 15:04:17
 */
public interface ISysFormFldService extends IFmBaseService<SysBaseEntity> {
    R insertColumn(Map<String, Object> params);

    R delColumn(Map<String, Object> params);
}

