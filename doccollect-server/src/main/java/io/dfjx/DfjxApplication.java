package io.dfjx;
/**
 * 2019 东方金信
 *
 *
 *
 *
 */

import io.dfjx.event.listener.ApplicationPreparedListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan
@MapperScan(basePackages = {"io.dfjx.module.*.dao"})
public class DfjxApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 注意这里要指向原先用main方法执行的Application启动类
		return builder.sources(DfjxApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DfjxApplication.class);
		app.addListeners(
				new ApplicationPreparedListener()
		);
		app.run(args);
	}

}