package com.kumquat;

import com.kumquat.common.config.KumquatProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableConfigurationProperties({KumquatProperties.class})
@MapperScan("com.kumquat.*.dao")
@EnableAsync
@EnableCaching
public class KumquatApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(KumquatApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(KumquatApplication.class, args);
	}

}
