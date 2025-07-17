package com.book.mastering_spring_boot_3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MasteringSpringBoot3Application {

	public static void main(String[] args) {
		SpringApplication.run(MasteringSpringBoot3Application.class, args);
	}

}
