package io.dfjx.module.auth.service;


import io.dfjx.module.auth.vo.OauthRoleVO;
import io.dfjx.module.auth.vo.OauthUserDTO;
import io.dfjx.module.auth.vo.OnlineUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

public interface AuthService {

    OnlineUser nowOnlineUser(String accessToken, HttpServletRequest request, HttpServletResponse response);

    Set<String> selectPermissionsByUserIdAndSystemToSet(Long userId);

    String queryUsersByIds(Long userId);

    Boolean loginOut(HttpServletRequest request);

    Boolean checkLogin(HttpServletRequest request);

    List<OauthRoleVO> selectAllRole();

    OauthUserDTO getLoginUserInfo();
}
