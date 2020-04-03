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

import io.dfjx.common.utils.Constant;
import io.dfjx.common.utils.CookieUtils;
import io.dfjx.module.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统页面视图
 * 
 * @author mazong
 * @email mazong@gmail.com
 * @date 2019年11月24日 下午11:05:27
 */
@Controller
public class SysPageController {

	@Value("${auth.login.url}")
	private String loginUrl;

	@Autowired
	private AuthService authService;

	@Value("${auth.logout.url}")
	private String logoutUrl;

	@RequestMapping("module/{module}/{url}.html")
	public String module(@PathVariable("module") String module, @PathVariable("url") String url){
		return "module/" + module + "/" + url;
	}

	@RequestMapping(value = {"/"})
	public String index(){
		return "index";
	}

	@RequestMapping("index.html")
	public String index1(){
		return "index";
	}

	@RequestMapping("query.html")
	public String query(){
		return "query";
	}

	@RequestMapping("login.html")
	public String login(){
		return "login";
	}

	@RequestMapping("main.html")
	public String main(){
		return "main";
	}

	@RequestMapping("404.html")
	public String notFound(){
		return "404";
	}

	@RequestMapping("app.html")
	public String app(){
		return "app";
	}

	@RequestMapping("ca/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response){
		CookieUtils.set(response, Constant.ACCESS_TOKEN, null, 0);
		CookieUtils.set(response, Constant.REFRESH_TOKEN, null, 0);
		authService.loginOut(request);
		return "redirect:"+logoutUrl;
	}

}
