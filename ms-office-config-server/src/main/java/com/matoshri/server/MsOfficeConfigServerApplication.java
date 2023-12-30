package com.matoshri.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MsOfficeConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsOfficeConfigServerApplication.class, args);
	}

}
