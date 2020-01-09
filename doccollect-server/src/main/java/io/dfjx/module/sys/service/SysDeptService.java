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

package io.dfjx.module.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.dfjx.common.utils.PageUtils;
import io.dfjx.module.sys.entity.SysDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 * @author mazong
 * @email mazong@gmail.com
 * @date 2019-06-20 15:23:47
 */
public interface SysDeptService extends IService<SysDeptEntity> {

	PageUtils queryPage(Map<String, Object> params);

	List<SysDeptEntity> queryList(Map<String, Object> map);

	/**
	 * 查询子部门ID列表
	 * @param parentId  上级部门ID
	 */
	List<Long> queryDetpIdList(Long parentId);

	/**
	 * 获取子部门ID，用于数据过滤
	 */
	List<Long> getSubDeptIdList(Long deptId);

}
