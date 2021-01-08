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
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.dfjx.common.utils.Constant;
import io.dfjx.module.sys.dao.SysMenuDao;
import io.dfjx.module.sys.entity.SysMenuEntity;
import io.dfjx.module.sys.entity.SysRoleMenuEntity;
import io.dfjx.module.sys.service.SysMenuService;
import io.dfjx.module.sys.service.SysRoleMenuService;
import io.dfjx.module.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	@Override
	public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
		List<SysMenuEntity> menuList = queryListParentId(parentId);
		if(menuIdList == null){
			return menuList;
		}
		
		List<SysMenuEntity> userMenuList = new ArrayList<>();
		for(SysMenuEntity menu : menuList){
			if(menuIdList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
	public List<SysMenuEntity> queryListParentId(Long parentId) {
		return baseMapper.queryListParentId(parentId);
	}

	@Override
	public List<SysMenuEntity> queryNotButtonList() {
		return baseMapper.queryNotButtonList();
	}

//	@Override
//	public List<SysMenuEntity> getUserMenuList(HttpServletRequest request, Long userId) {
//		//系统管理员，拥有最高权限
//		/*if(userId == Constant.SUPER_ADMIN){
//			return getAllMenuList(null);
//		}*/
//
////		//用户菜单列表
////		List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
////		return getAllMenuList(menuIdList);
//
//		String token = CookieUtils.get(request, Constant.ACCESS_TOKEN).getValue();
//		if(StringUtils.isNotBlank(token)){
//			token = token.toLowerCase().replace("bearer", "");
//		}
//		Map<Long, String> mapCodes = oauthUserTemplate.selectPermissionsByUserIdAndSystem(TagUserUtils.userId(), Constant.APP_NAME, token);
//		List<SysMenuEntity> menuIdList = baseMapper.queryByPermsCode(mapCodes);
//
//		List<SysMenuEntity> rootTrees = new ArrayList<SysMenuEntity>();
//		for (SysMenuEntity tree : menuIdList) {
//			if(tree.getParentId() == 0){
//				rootTrees.add(tree);
//			}
//			for (SysMenuEntity t : menuIdList) {
//				if(t.getParentId() == tree.getMenuId()){
//					if(tree.getList() == null){
//						List<SysMenuEntity> myChildrens = new ArrayList<SysMenuEntity>();
//						myChildrens.add(t);
//						tree.setList(myChildrens);
//					}else{
//						tree.getList().add(t);
//					}
//				}
//			}
//		}
//		return rootTrees;
//	}


	@Override
	public List<SysMenuEntity> getUserMenuList(Long userId) {
		 List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
		return getAllMenuList(menuIdList);
	}

	@Override
	public List<SysMenuEntity> queryByPermsCode(List<String> codes) {
		if (codes.size() == 0) {
			return new ArrayList<>();
		}
		List<SysMenuEntity> menuIdList = baseMapper.queryByPermsCode(codes);
		List<SysMenuEntity> rootTrees = new ArrayList<SysMenuEntity>();
		for (SysMenuEntity tree : menuIdList) {
			if(tree.getParentId() == 0){
				rootTrees.add(tree);
			}
			for (SysMenuEntity t : menuIdList) {
				if(t.getParentId() == tree.getMenuId()){
					if(tree.getList() == null){
						List<SysMenuEntity> myChildrens = new ArrayList<SysMenuEntity>();
						myChildrens.add(t);
						tree.setList(myChildrens);
					}else{
						tree.getList().add(t);
					}
				}
			}
		}
		return rootTrees;
	}

	@Override
	public void delete(Long menuId){
		//删除菜单
		this.removeById(menuId);
		//删除菜单与角色关联
		sysRoleMenuService.remove(new QueryWrapper<SysRoleMenuEntity>().eq("menu_id", menuId));
	}

	/**
	 * 获取所有菜单列表
	 */
	private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList){
		//查询根菜单列表
		List<SysMenuEntity> menuList = queryListParentId(0L, menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);
		
		return menuList;
	}

	/**
	 * 递归
	 */
	private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList){
		List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();
		
		for(SysMenuEntity entity : menuList){
			//目录
			if(entity.getType() == Constant.MenuType.CATALOG.getValue()){
				entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}
		
		return subMenuList;
	}
}
