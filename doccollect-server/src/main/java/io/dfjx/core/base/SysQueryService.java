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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.dfjx.common.utils.PageUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */

public abstract class SysQueryService <M extends ISysQueryDao<T>, T> implements ISysQueryService<T>{

}
