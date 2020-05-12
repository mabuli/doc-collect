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


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 人口分类实体类
 *
 * @author chenbingren
 * @email bingren.chen@seaboxdata.com
 * @date 2020-05-11 15:14
 */
@Data
@TableName("sys_classify")
public class SysClassifyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分类ID
	 */
	@TableId
	private Long classifyId;

	/**
	 * 分类名称
	 */
	private String classifyName;

	/**
	 * 接口服务
	 */
	private String serviceApi;

	/**
	 * 关联角色
	 */
	private String roleIds;

	/**
	 * 删除标记  -1：已删除  0：正常
	 */
	@TableLogic
	private Integer status;

	/**
	 * 排序
	 */
	private Integer orderNum;

	/**
	 * 创建时间
	 */
	private Date createTime;
}
