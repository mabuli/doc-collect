package io.dfjx.module.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Joiner;
import io.dfjx.common.utils.Constant;
import io.dfjx.common.utils.PageUtils;
import io.dfjx.module.auth.service.AuthService;
import io.dfjx.module.auth.vo.OauthRoleVO;
import io.dfjx.module.sys.dao.SysClassifyDao;
import io.dfjx.module.sys.entity.SysClassifyEntity;
import io.dfjx.module.sys.service.SysClassifyService;
import io.dfjx.module.sys.service.SysRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 人口分类服务实现
 *
 * @author chenbingren
 * @email bingren.chen@seaboxdata.com
 * @date 2020-05-11 15:14
 */
@Service("sysClassifyService")
public class SysClassifyServiceImpl extends ServiceImpl<SysClassifyDao, SysClassifyEntity> implements SysClassifyService {

//    @Override
//    public PageUtils queryPage(Map<String, Object> params) {
//        //分页参数
//        long curPage = 1;
//        long limit = 10;
//
//        if(params.get(Constant.PAGE) != null){
//            curPage = Long.parseLong((String)params.get(Constant.PAGE));
//        }
//        if(params.get(Constant.LIMIT) != null){
//            limit = Long.parseLong((String)params.get(Constant.LIMIT));
//        }
//
//        Page pager = new Page(curPage, limit);
//        IPage<Map<String, Object>> pageData = baseMapper.queryPage(pager, params);
//        return new PageUtils(pageData);
//    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //查询所有的人口分类
        String classifyName = (String)params.get("classifyName");
        List<SysClassifyEntity> recordsList=this.baseMapper.selectList(new QueryWrapper<SysClassifyEntity>().lambda()
                .like(StringUtils.isNotBlank(classifyName),SysClassifyEntity::getClassifyName, classifyName)
                .eq(SysClassifyEntity::getStatus, 0));
        //查询系统中角色的信息

        List<OauthRoleVO> list = authService.selectAllRole();
        Map<String,String> roleMap=new HashMap<>();
        for(OauthRoleVO sysRoleEntity:list){
            roleMap.put(sysRoleEntity.getId()+"",sysRoleEntity.getRoleName());
        }

        //把人口分类角色id列转成成名称输出到页面
        for(SysClassifyEntity e:recordsList){
            if(StringUtils.isNotBlank(e.getRoleIds())){
                List afterList=new ArrayList();
                String[] arr=e.getRoleIds().split(",");
                for(String roleId:arr){
                    afterList.add(roleMap.get(roleId));
                }
                Joiner joiner=Joiner.on(",").skipNulls();
                e.setRoleNames(joiner.join(afterList));
            }
        }
        List<SysClassifyEntity> filterAfterList=new ArrayList<>();

        //查询当前用户的角色
        List<OauthRoleVO> roleId=authService.getLoginUserInfo().getRoles();
        List<String> roleIds= new ArrayList<>();
        if(roleId!=null){
            roleIds=Optional.ofNullable(roleId).orElse(Collections.emptyList()).stream().map(e->e.getId()+"").collect(Collectors.toList());
        }
        //过滤当前角色拥有的接口信息
        for(SysClassifyEntity e:recordsList){
            if(StringUtils.isNotBlank(e.getRoleIds())&&roleIds.size()>0){
                String[] arr=e.getRoleIds().split(",");
                List<String> tempList=new ArrayList<>(Arrays.asList(arr));
                //求交集
                tempList.retainAll(roleIds);
                if(tempList.size()>0){
                    filterAfterList.add(e);
                }
            }
        }
        int curPage = 1;
        int limit = 10;
        if(params.get(Constant.PAGE) != null){
            curPage = Integer.parseInt((String)params.get(Constant.PAGE));
        }
        if(params.get(Constant.LIMIT) != null){
            limit = Integer.parseInt((String)params.get(Constant.LIMIT));
        }
        int indexStart=(curPage-1)*limit;
        int indexEnd=indexStart+limit;
        ArrayList resultPageList=new ArrayList();
        for(int i=indexStart;i<indexEnd&&filterAfterList.size()>i;i++){
            resultPageList.add(filterAfterList.get(i));
        }
        PageUtils pageInfo=new PageUtils(resultPageList,filterAfterList.size(),
                limit,curPage
        );
        return pageInfo;
    }

    @Override
    public List<SysClassifyEntity> filterList() {
        //查询所有的人口分类
        List<SysClassifyEntity> recordsList=this.baseMapper.selectList(
                new QueryWrapper<SysClassifyEntity>().lambda()
                .eq(SysClassifyEntity::getStatus, 0));
        //查询系统中角色的信息
        List<SysClassifyEntity> filterAfterList=new ArrayList<>();
        //查询当前用户的角色
        List<OauthRoleVO> roleId=authService.getLoginUserInfo().getRoles();
        List<String> roleIds= new ArrayList<>();
        if(roleId!=null){
            roleIds=roleId.stream().map(e->e.getId()+"").collect(Collectors.toList());
        }
        //过滤当前角色拥有的接口信息
        for(SysClassifyEntity e:recordsList){
            if(StringUtils.isNotBlank(e.getRoleIds())&&roleIds.size()>0){
                String[] arr=e.getRoleIds().split(",");
                List<String> tempList=new ArrayList<>(Arrays.asList(arr));
                //求交集
                tempList.retainAll(roleIds);
                if(tempList.size()>0){
                    filterAfterList.add(e);
                }
            }
        }
        return filterAfterList;
    }

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private AuthService authService;
}