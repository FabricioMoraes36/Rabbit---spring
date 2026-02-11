package com.rabbit.SpringRabbit.User;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserPublisher userPublisher;

    public UserController(UserPublisher userPublisher) {
        this.userPublisher = userPublisher;
    }

    @PostMapping("/{id}")
    public void create(@PathVariable String id){
        userPublisher.publish(id);
    }
}
