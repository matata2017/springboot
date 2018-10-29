package com.xxy.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author xxy
 */
@SpringBootApplication
@EnableAutoConfiguration
@EntityScan({"com.xxy.**"})
@ComponentScan(basePackages = {"com.xxy.**"})
@EnableJpaRepositories(basePackages = {"com.xxy.**"})
@EnableCaching
@EnableTransactionManagement
@ImportResource({"classpath:dubbo-consumer.xml"})
public class EsApplication {
	public static void main(String[] args) {
		SpringApplication.run(EsApplication.class, args);
	}
}
