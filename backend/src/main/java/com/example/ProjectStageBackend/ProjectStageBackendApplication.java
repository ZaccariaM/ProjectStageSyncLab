package com.example.ProjectStageBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//try to remove exclude
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ProjectStageBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectStageBackendApplication.class, args);
	}

}
