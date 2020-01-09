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

package io.dfjx.module.sys.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户与角色对应关系
 *
 * @author mazong
 * @email mazong@gmail.com
 * @date 2019年9月18日 上午9:28:39
 */
@Data
@TableName("sys_user_role")
public class SysUserRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableId
	private Long id;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 角色名称
	 */
	@TableField(exist=false)
	private String roleName;


}
