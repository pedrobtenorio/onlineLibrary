package com.hbtpedro.onlinelibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OnlinelibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinelibraryApplication.class, args);
	}

}
