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

package io.dfjx.module.sys.controller;

import io.dfjx.common.annotation.SysLog;
import io.dfjx.common.utils.PageUtils;
import io.dfjx.common.utils.R;
import io.dfjx.common.validator.ValidatorUtils;
import io.dfjx.module.sys.entity.SysClassifyEntity;
import io.dfjx.module.sys.service.SysClassifyService;
import io.dfjx.module.sys.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 人口分类菜单
 *
 * @author chenbingren
 * @email bingren.chen@seaboxdata.com
 * @date 2020-05-11 14:33:47
 */
@RestController
@RequestMapping("/sys/classify")
public class SysClassifyController extends AbstractController {

	@Autowired
	private SysClassifyService sysClassifyService;

	@Autowired
	private SysRoleService sysRoleService;
	/**
	 * 分页列出人口分类
	 */
	@RequestMapping("/page")
	@RequiresPermissions("sys:classify:page")
	public R page(@RequestParam Map<String, Object> params){
		List<Long> userId=getUser().getRoleIdList();
		PageUtils page = sysClassifyService.queryPage(params);

		return R.ok().put("page", page);
	}

	/**
	 * 所有当前用户的分类
	 */
	@RequestMapping("/select")
	public R select(){

		List<SysClassifyEntity> list = sysClassifyService.filterList();
		return R.ok().put("list", list);
	}

	/**
	 * 根据ID查询人口分类
	 */
	@RequestMapping("/info/{classifyId}")
	@RequiresPermissions("sys:classify:info")
	public R info(@PathVariable("classifyId") Long classifyId){
		SysClassifyEntity classify = sysClassifyService.getById(classifyId);

		return R.ok().put("classify", classify);
	}


	/**
	 * 删除分类
	 */
	@SysLog("删除人口分类")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:classify:delete")
	public R delete(@RequestBody Long[] classifyIds){
		sysClassifyService.removeByIds(Arrays.asList(classifyIds));

		return R.ok();
	}

	/**
	 * 保存配置
	 */
	@SysLog("保存人口分类")
	@RequestMapping("/save")
	@RequiresPermissions("sys:classify:save")
	public R save(@RequestBody SysClassifyEntity classify){
		ValidatorUtils.validateEntity(classify);

		sysClassifyService.save(classify);

		return R.ok();
	}

	/**
	 * 修改配置
	 */
	@SysLog("修改人口分类")
	@RequestMapping("/update")
	@RequiresPermissions("sys:classify:update")
	public R update(@RequestBody SysClassifyEntity classify){
		ValidatorUtils.validateEntity(classify);

		sysClassifyService.updateById(classify);

		return R.ok();
	}

}
