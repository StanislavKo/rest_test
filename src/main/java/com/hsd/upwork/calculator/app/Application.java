package com.hsd.upwork.calculator.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.hsd.upwork.calculator.controller.ArithmeticController;

@SpringBootApplication(scanBasePackages = "com.hsd.upwork.calculator.controller,com.hsd.upwork.calculator.component,com.hsd.upwork.calculator.config,com.hsd.upwork.calculator.interceptor")
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

		for (String name : applicationContext.getBeanDefinitionNames()) {
			logger.debug("bean name={}", name);
		}
	}

}