package io.dfjx.module.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.dfjx.module.sys.entity.SysTnmtEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 租户ID
 * 
 * @author mazong
 * @email mazong@gmail.com
 * @date 2019-08-18 21:17:59
 */
@Mapper
public interface SysTnmtDao extends BaseMapper<SysTnmtEntity> {
	
}
