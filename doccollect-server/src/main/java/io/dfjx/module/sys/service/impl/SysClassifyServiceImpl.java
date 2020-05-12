package io.dfjx.module.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.dfjx.common.utils.Constant;
import io.dfjx.common.utils.PageUtils;
import io.dfjx.common.utils.Query;
import io.dfjx.module.sys.dao.SysClassifyDao;
import io.dfjx.module.sys.dao.SysTnmtDao;
import io.dfjx.module.sys.entity.SysClassifyEntity;
import io.dfjx.module.sys.entity.SysConfigEntity;
import io.dfjx.module.sys.entity.SysTnmtEntity;
import io.dfjx.module.sys.service.SysClassifyService;
import io.dfjx.module.sys.service.SysTnmtService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 人口分类服务实现
 *
 * @author chenbingren
 * @email bingren.chen@seaboxdata.com
 * @date 2020-05-11 15:14
 */
@Service("sysClassifyService")
public class SysClassifyServiceImpl extends ServiceImpl<SysClassifyDao, SysClassifyEntity> implements SysClassifyService {

//    @Override
//    public PageUtils queryPage(Map<String, Object> params) {
//        //分页参数
//        long curPage = 1;
//        long limit = 10;
//
//        if(params.get(Constant.PAGE) != null){
//            curPage = Long.parseLong((String)params.get(Constant.PAGE));
//        }
//        if(params.get(Constant.LIMIT) != null){
//            limit = Long.parseLong((String)params.get(Constant.LIMIT));
//        }
//
//        Page pager = new Page(curPage, limit);
//        IPage<Map<String, Object>> pageData = baseMapper.queryPage(pager, params);
//        return new PageUtils(pageData);
//    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String classifyName = (String)params.get("classifyName");

        IPage<SysClassifyEntity> page = this.page(
                new Query<SysClassifyEntity>().getPage(params),
                new QueryWrapper<SysClassifyEntity>()
                        .like(StringUtils.isNotBlank(classifyName),"classify_name", classifyName)
                        .eq("status", 0)
        );

        return new PageUtils(page);
    }
}