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

package io.dfjx.module.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.dfjx.common.annotation.DataFilter;
import io.dfjx.common.utils.Constant;
import io.dfjx.common.utils.PageUtils;
import io.dfjx.common.utils.Query;
import io.dfjx.module.sys.dao.SysUserDao;
import io.dfjx.module.sys.entity.SysDeptEntity;
import io.dfjx.module.sys.entity.SysUserEntity;
import io.dfjx.module.sys.service.SysDeptService;
import io.dfjx.module.sys.service.SysUserRoleService;
import io.dfjx.module.sys.service.SysUserService;
import io.dfjx.module.sys.shiro.ShiroUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 * 
 * @author mazong
 * @email mazong@gmail.com
 * @date 2019年9月18日 上午9:46:09
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysDeptService sysDeptService;

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return baseMapper.queryAllMenuId(userId);
	}

	@Override
	@DataFilter(subDept = true, user = false)
	public PageUtils queryPage(Map<String, Object> params) {
		String username = (String)params.get("username");

		IPage<SysUserEntity> page = this.page(
			new Query<SysUserEntity>().getPage(params),
			new QueryWrapper<SysUserEntity>()
				.like(StringUtils.isNotBlank(username),"username", username)
				.apply(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
		);

		for(SysUserEntity sysUserEntity : page.getRecords()){
			SysDeptEntity sysDeptEntity = sysDeptService.getById(sysUserEntity.getDeptId());
			sysUserEntity.setDeptName(sysDeptEntity.getName());
		}

		return new PageUtils(page);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveUser(SysUserEntity user) {
		user.setCreateTime(new Date());
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setSalt(salt);
		user.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt()));
		this.save(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysUserEntity user) {
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword(null);
		}else{
			SysUserEntity userEntity = this.getById(user.getUserId());
			user.setPassword(ShiroUtils.sha256(user.getPassword(), userEntity.getSalt()));
		}
		this.updateById(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}


	@Override
	public boolean updatePassword(Long userId, String password, String newPassword) {
        SysUserEntity userEntity = new SysUserEntity();
        userEntity.setPassword(newPassword);
        return this.update(userEntity,
        	new QueryWrapper<SysUserEntity>().eq("user_id", userId).eq("password", password));
    }

}
