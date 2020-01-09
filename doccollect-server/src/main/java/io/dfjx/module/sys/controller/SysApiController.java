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

import io.dfjx.common.utils.R;
import io.dfjx.common.utils.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 系统页面API
 *
 * @author mazong
 * @email gitname@github.com
 * @date 2019年11月31日 下午11:05:27
 */
@RestController
public class SysApiController {

	private static Logger logger = LoggerFactory.getLogger(SysApiController.class);

	@RequestMapping("api/{serviceName}/{methodName}")
	public R api(@PathVariable String serviceName, @PathVariable String methodName, HttpServletRequest request, HttpServletResponse response){
		Object servcieObj = SpringContextUtils.getBean(serviceName);
		Object result = R.ok();
		try {
			Method method = servcieObj.getClass().getDeclaredMethod(methodName, Map.class);
			result = method.invoke(servcieObj, request.getParameterMap());
		} catch (Exception ex) {
			checkException(ex, serviceName, methodName, response);
		}
		return (R) result;
	}

	@RequestMapping("api/{serviceName}/download")
	public void download(@PathVariable String serviceName, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Object servcieObj = SpringContextUtils.getBean(serviceName);
		String methodName = "download";
		try {
			Method method = servcieObj.getClass().getDeclaredMethod(methodName, Map.class);
			method.invoke(servcieObj, request.getParameterMap());
		} catch (Exception ex) {
			checkException(ex, serviceName, methodName, response);
		}
	}
	private void checkException(Exception ex, String serviceName, @PathVariable String methodName, HttpServletResponse response){
		if (ex.getCause() instanceof NullPointerException){
			response.setStatus(450);
		}
		if (ex.getCause() instanceof ArrayIndexOutOfBoundsException){
			response.setStatus(450);
		}
		logger.info("调用失败 servicename：{}，methodname：{}", serviceName, methodName);
		logger.error("异常信息 ",ex.getCause());
		logger.error(ex.getMessage());
	}
}
