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
package io.dfjx.core.base;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.dfjx.common.utils.Constant;
import io.dfjx.common.utils.PageUtils;
import io.dfjx.core.model.BO;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Transactional
public abstract class SysBaseService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements ISysBaseService<T>{

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

    public PageUtils queryPage(Map<String, Object> params){

        return new PageUtils(null);
    }

    public List<Map<String, Object>> queryAll(Map<String, Object> params){
        List<Map<String, Object>> list = this.listMaps(new QueryWrapper<T>().select("*"));
        return  list;
    }
}
