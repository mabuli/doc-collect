package io.dfjx.module.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.dfjx.common.utils.PageUtils;
import io.dfjx.module.sys.entity.SysClassifyEntity;

import java.util.List;
import java.util.Map;


/**
 * 人口分类服务
 *
 * @author chenbingren
 * @email bingren.chen@seaboxdata.com
 * @date 2020-05-11 15:14
 */
public interface SysClassifyService extends IService<SysClassifyEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SysClassifyEntity> filterList();
}

