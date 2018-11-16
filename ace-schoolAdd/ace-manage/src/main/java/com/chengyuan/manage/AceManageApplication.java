package com.chengyuan.manage;

import com.ace.cache.EnableAceCache;
import com.github.wxiaoqi.security.auth.client.EnableAceAuthClient;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableEurekaClient
@EnableCircuitBreaker
@EnableFeignClients({"com.github.wxiaoqi.security.auth.client.feign"})
@EnableScheduling
@EnableAceAuthClient
@EnableAceCache
@EnableTransactionManagement
@MapperScan("com.chengyuan.manage.mapper")
@EnableSwagger2Doc
@SpringBootApplication
public class AceManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(AceManageApplication.class, args);
	}
}
