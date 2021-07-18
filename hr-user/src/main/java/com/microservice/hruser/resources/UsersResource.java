package com.microservice.hruser.resources;

import com.microservice.hruser.entities.Users;
import com.microservice.hruser.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UsersResource {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Users> findById(@PathVariable Long id) {
        Optional<Users> users = usersRepository.findById(id);
        var obj = users.orElseGet(Users::new);
        return ResponseEntity.ok(obj);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Users> findByEmail(@RequestParam String email) {
        var users = usersRepository.findByEmail(email);
        if (users != null) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
