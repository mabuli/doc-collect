/**
 * Copyright 2018 权限管理源 http://www.xxx.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package io.dfjx.module.sys.controller;


import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import io.dfjx.common.utils.R;
import io.dfjx.common.utils.StringTools;
import io.dfjx.config.SystemConfig;
import io.dfjx.module.sys.service.SysRoleService;
import io.dfjx.module.sys.shiro.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 登录相关
 * 
 * @author mazong
 * @email mazong@gmail.com
 * @date 2019年11月10日 下午1:15:31
 */
@Controller
public class SysLoginController {
    @Autowired
    private Producer producer;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SystemConfig systemConfig;

    private static final String SYSTEM_PROFILE = "dev";

    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        // 保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    /**
     * 登录
     */
    @ResponseBody
    @RequestMapping(value = "/sys/login", method = RequestMethod.POST)
    // thp改 去除验证码
    // public R login(String username, String password, String captcha) {
    public R login(String username, String password) {
        // String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        // if(!captcha.equalsIgnoreCase(kaptcha)){
        // return R.error("验证码不正确");
        // }

        try {
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return R.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return R.error("账号或密码不正确");
        } catch (LockedAccountException e) {
            return R.error("账号已被锁定,请联系管理员");
        } catch (AuthenticationException e) {
            return R.error("账户验证失败");
        }

        return R.ok().put("url", getIndexUrl());
    }

    /**
     * 退出
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        ShiroUtils.logout();
        return "redirect:loginsso";
        //return "redirect:login.html";
	}

    @RequestMapping("loginsso")
    public String loginsso(){
        return "redirect:"+getCasLogout();
//        if(SYSTEM_PROFILE.equals(SpringContextUtils.getActiveProfile())){
//            return "redirect:login.html";
//        }else{
//            return "redirect:"+getCasLogout();
//        }

    }

    @RequestMapping("indexsso")
    public String indexsso(HttpServletRequest request){
        String main = "index";
//        if(SYSTEM_PROFILE.equals(SpringContextUtils.getActiveProfile())){
//            return main;
//        }
//        CasFilter sso = new CasFilter();
//        boolean isLogin = sso.doLogin(request);
//        if(!isLogin){
//            return "redirect:"+getCasLogin();
//        }
        return getIndexUrl();
    }

    private String getCasLogout(){
        String url = systemConfig.getCasServiceUrl() + "/logout?service=" + StringTools.urlEncode(systemConfig.getProjectUrl());
        return url;
    }
    private String getCasLogin(){
        String url = systemConfig.getCasServiceUrl() + "/login?service=" + StringTools.urlEncode(systemConfig.getProjectUrl());
        return url;
    }

	private String getIndexUrl(){
        return URL_ADMIN_INDEX;
//        SysUserEntity current = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
//        if(current == null)
//            return URL_ADMIN_INDEX;
//        List<Map<String,Object>> roleList = sysRoleService.queryRoleList(current.getUserId());
//        Map<String,Object> role = roleList != null ? roleList.get(0) : null;
//        String roleType = StringTools.mstr(role, "role_type");
//        String appId = StringTools.mstr(role, "app_id");
//        if(role == null)
//            return URL_ADMIN_INDEX;
//        if(ROLE_TYPE_USER.equals(roleType)) {
//            if(appId.length() == 0)
//                return "";
//            return URL_USER_INDEX + "?a=" + appId;
//        }
//        return URL_ADMIN_INDEX;
    }

    private static final String URL_ADMIN_INDEX = "index.html";//"index";//
    private static final String URL_USER_INDEX = "app.html";
    private static final String ROLE_TYPE_USER = "user";
}
