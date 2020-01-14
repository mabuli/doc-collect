package io.dfjx.module.fm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.dfjx.common.utils.PageUtils;
import io.dfjx.core.base.SysBaseEntity;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 项目信息表
 *
 * @author mazong
 * @email mazong@gmail.com
 * @date 2019-12-23 15:04:17
 */
public interface IFmBaseService<T> extends IService<T> {
    int insertMap(Map<String, Object> params);
    int updateMap(Map<String, Object> params);
    int deleteMap(Collection<? extends Serializable> ids);
    Map<String, Object> queryById(Long id);
    PageUtils queryPage(Map<String, Object> params);
    PageUtils queryList(Map<String, Object> params);
    List<Map<String, Object>> queryAll(Map<String, Object> params);
    //int upload(Map<String, Object> params);
}

