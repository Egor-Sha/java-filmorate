package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.services.UserService;

import javax.validation.Valid;
import java.util.*;

@Slf4j
@RestController
public class UserController {
    UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return service.getAll();
    }

    @PostMapping("/users")
    public User create(@Valid @RequestBody final User user) {
        log.info("Создан пользователь " + user.getLogin());
        return service.create(user);
    }

    @PutMapping("/users")
    public User put(@Valid @RequestBody final User user) {
        return service.update(user);
    }

    @PutMapping("/users/{id}/like/{userId}")
    public void addFriend(@PathVariable long id, @PathVariable long userId) {
        service.addFriend(id, userId);
    }

    @DeleteMapping("/users/{id}/like/{userId}")
    public void removeFriend(@PathVariable long id, @PathVariable long userId) {
        service.removeFriend(id, userId);
    }


}
