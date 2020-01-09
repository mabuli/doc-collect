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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色
 * 
 * @author mazong
 * @email mazong@gmail.com
 * @date 2019年9月18日 上午9:27:38
 */
@Data
@TableName("sys_role")
public class SysRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 角色ID
	 */
	@TableId
	private Long roleId;

	/**
	 * 角色名称
	 */
	@NotBlank(message="角色名称不能为空")
	private String roleName;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 部门ID
	 */
	@NotNull(message="部门不能为空")
	private Long deptId;

	/**
	 * 部门名称
	 */
	@TableField(exist=false)
	private String deptName;

	@TableField(exist=false)
	private List<Long> menuIdList;
	@TableField(exist=false)
	private List<Long> deptIdList;
	@TableField(exist=false)
	private List<String> meaIdList;
	@TableField(exist=false)
	private List<String> appIdList;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 角色类型
	 */
	private String roleType;
}
