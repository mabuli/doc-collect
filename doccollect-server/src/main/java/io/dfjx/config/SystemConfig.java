package io.dfjx.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "system-config")
public class SystemConfig {

	private String fileDir;	//文件保存目录
//	private String casServiceUrl;	//cas服务目录
	private String projectUrl;	//本系统登录地址
//	private String portalApp;	//portal注册系统名称
//	private String portalSoapUrl;	//portal服务地址
}
