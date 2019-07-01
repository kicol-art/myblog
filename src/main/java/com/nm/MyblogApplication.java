package com.nm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@ServletComponentScan//使用filter需要该扫描
@EnableCaching//开启缓存 指定redis需加依赖并配置
public class MyblogApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyblogApplication.class, args);
	}
}
