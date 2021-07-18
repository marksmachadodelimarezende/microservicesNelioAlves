package com.microservice.hroauth.services;

import com.microservice.hroauth.entities.Users;
import com.microservice.hroauth.feignClients.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private UserFeignClient userFeignClient;

    @Autowired
    public UserService(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    public Users findByEmail(String email) {
        log.debug("Efetuando chamada: userFeignClient.findByEmail(email).getBody()");
        var user = userFeignClient.findByEmail(email).getBody();
        if (user == null) {
            log.error("Email not fount: " + email);
            throw new IllegalArgumentException("Email not found");
        }
        log.debug("User found: " + user);
        return user;
    }
}
