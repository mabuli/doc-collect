/**
 * <h3>标题 : Quick通用系统框架 </h3>
 * <h3>描述 : 通用服务类</h3>
 * <h3>日期 : 2014-03-23</h3>
 * <h3>版权 : Copyright (C) 海口鑫网计算机网络有限公司</h3>
 * 
 * <p>
 * @author admin admin@xinwing.com.cn
 * @version <b>v1.0.0</b>
 *          
 * <b>修改历史:</b>
 * -------------------------------------------
 * 修改人 修改日期 修改描述
 * -------------------------------------------
 *          
 *          
 * </p>
 */
package io.dfjx.module.fm.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.dfjx.common.utils.Constant;
import io.dfjx.common.utils.PageUtils;
import io.dfjx.module.fm.dao.FmBaseDao;
import io.dfjx.module.fm.service.IFmBaseService;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
@Transactional
public abstract class FmBaseServiceImpl<M extends FmBaseDao<T>, T> extends ServiceImpl<M, T> implements IFmBaseService<T> {

    public String BaseTable = "";
    public String BaseKey = "";


    public Page getPager(Map<String, Object> params){
        long curPage = 1;
        long limit = 10;

        if(params.get(Constant.PAGE) != null){
            curPage = Long.parseLong((String)params.get(Constant.PAGE));
        }
        if(params.get(Constant.LIMIT) != null){
            limit = Long.parseLong((String)params.get(Constant.LIMIT));
        }

        Page pager = new Page(curPage, limit);

        return pager;
    }

    public String getWhere(Map<String, Object> params){
        return "";
    }

    @Override
    public List<Map<String, Object>> queryAll(Map<String, Object> params){
        String whereStr = getWhere(params);
        List<Map<String, Object>> list = baseMapper.queryAll(BaseTable, BaseKey, whereStr);
        return  list;
    }

    @Override
    public PageUtils queryList(Map<String, Object> params){
        String whereStr = getWhere(params);
        Page pageParm = getPager(params);
        List<Map<String, Object>> pageData = baseMapper.queryList(BaseTable, BaseKey, whereStr, pageParm);
        return new PageUtils(pageData, (int)pageParm.getTotal(), (int)pageParm.getSize(), (int)pageParm.getCurrent());
    }

    @Override
    public int insertMap(Map<String, Object> params) {
        params.put("id", "");
        params.remove(BaseKey);
        int c = baseMapper.insertMap(BaseTable, BaseKey, params);
        params.put(BaseKey, params.get("id"));
        return c;
    }

    @Override
    public int updateMap(Map<String, Object> params) {
        return baseMapper.updateMap(BaseTable, BaseKey, params);
    }

    @Override
    public int deleteMap(Collection<? extends Serializable> ids){
        return baseMapper.deleteMap(BaseTable, BaseKey, ids);
    }

    @Override
    public Map<String, Object> queryById(Long id) {
        return baseMapper.selectMapById(BaseTable, BaseKey, id);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params){
        Page pageParm = getPager(params);
        List<Map<String, Object>> pageData = baseMapper.queryPage(BaseTable, BaseKey, params, pageParm);
        return new PageUtils(pageData, (int)pageParm.getTotal(), (int)pageParm.getSize(), (int)pageParm.getCurrent());
    }

    protected boolean ck(Map<String, Object> params, String key){
        if(!params.containsKey(key)) return false;
        Object obj = params.get(key);
        if(obj == null || obj.toString().length() == 0) return false;
        return true;
    }
    protected String mstr(Map<String, Object> params, String key){
        Object obj = params.get(key);
        if(obj == null || obj.toString().length() == 0) return "";
        return obj.toString();
    }
}
