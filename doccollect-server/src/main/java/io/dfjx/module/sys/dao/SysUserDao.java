/**
 * Copyright 2018 东方金信
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.dfjx.module.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.dfjx.module.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 系统用户
 * 
 * @author mazong
 * @email mazong@gmail.com
 * @date 2019年9月18日 上午9:34:11
 */
@Repository
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);
	
	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByUserName(String username);

	/**
	 * 根据用户名删除用户
	 */
	void deleteByUserName(String username);

}
