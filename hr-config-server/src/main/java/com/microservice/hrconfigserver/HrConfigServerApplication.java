package com.microservice.hrconfigserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class HrConfigServerApplication implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(HrConfigServerApplication.class); 
	
    public static void main(String[] args) {
        SpringApplication.run(HrConfigServerApplication.class, args);
    }
    
    @Value("${spring.cloud.config.server.git.uri}")
    private String gitUri;
    
	@Override
	public void run(String... args) throws Exception {
		logger.info("Present configuration GITHUB: " + isPresentConfigurationGitRepository());
	}
	
	private boolean isPresentConfigurationGitRepository() {
		return isStringValid(gitUri);
	}

	private boolean isStringValid(String value) {
		return value != null && value.trim().length() > 0;
	}

}
