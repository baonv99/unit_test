package com.vnpay.springapigradle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class SpringapigradleApplication {
	public static void main(String[] args) {
		try {
			SpringApplication app = new SpringApplication(SpringapigradleApplication.class);
			app.setDefaultProperties(Collections.singletonMap("server.port", 12000));
			app.run(args);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
