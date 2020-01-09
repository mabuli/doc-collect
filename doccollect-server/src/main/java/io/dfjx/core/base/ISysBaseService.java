/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.dfjx.core.base;

import com.baomidou.mybatisplus.extension.service.IService;
import io.dfjx.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public interface ISysBaseService<T> extends IService<T> {

    PageUtils queryPage(Map<String, Object> params);

    List<Map<String, Object>> queryAll(Map<String, Object> params);

}
