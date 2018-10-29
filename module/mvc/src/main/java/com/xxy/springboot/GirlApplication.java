package com.xxy.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan({"com.xxy.**"})
@ComponentScan(basePackages = {"com.xxy.**"})
@EnableJpaRepositories(basePackages = {"com.xxy.**"})
@EnableCaching
@EnableTransactionManagement
@ImportResource({"classpath:dubbo-provider.xml"})
public class GirlApplication {
	public static void main(String[] args) {
		SpringApplication.run(GirlApplication.class, args);
	}
}
