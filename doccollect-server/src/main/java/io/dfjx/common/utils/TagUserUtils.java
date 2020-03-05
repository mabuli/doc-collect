package io.dfjx.common.utils;

import com.seaboxdata.auth.api.utils.UserUtils;
import com.seaboxdata.auth.api.vo.OauthLoginUserVO;
import io.dfjx.module.sys.entity.SysUserEntity;
import io.dfjx.module.sys.service.SysUserService;


public class TagUserUtils {

    private static SysUserService sysUserService = (SysUserService) SpringContextUtils.getBean("sysUserService");

    public static Long userId(){
//        return SecurityUtils.getUser().getUserId();

        OauthLoginUserVO userVO = UserUtils.getUserDetails();
        return userVO.getUserId();
//        return 1L;
    }

    public static String userName(){
        return sysUserService.getById(userId()).getUsername();
//        return "admin";
    }

    public static SysUserEntity getTagUser(){
        SysUserEntity userEntity = sysUserService.getById(userId());
        return userEntity;
    }

}
