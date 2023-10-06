package com.lucy.pass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages={"com.lucy.pass"})
public class PassApplication {

	public static void main(String[] args) {
		SpringApplication.run(PassApplication.class, args);
	}

}
