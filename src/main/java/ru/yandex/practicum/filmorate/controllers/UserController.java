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

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") long id) {
        return userService.getUser(id);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAll();
    }

    @PostMapping("/users")
    public User create(@Valid @RequestBody final User user) {
        log.info("Создан пользователь " + user.getLogin());
        return userService.create(user);
    }

    @PutMapping("/users")
    public User put(@Valid @RequestBody final User user) {
        return userService.update(user);
    }

    @PutMapping("/users/{id}/friends/{friendId}")
    public void addFriend(@PathVariable long id, @PathVariable long friendId) {
        userService.addFriend(id, friendId);
    }

    @DeleteMapping("/users/{id}/friends/{friendId}")
    public void removeFriend(@PathVariable long id, @PathVariable long friendId) {
        userService.removeFriend(id, friendId);
    }

    @GetMapping("/users/{id}/friends")
    public List<User> getFriends(@PathVariable("id") long id) {
        return userService.getFriends(id);
    }

    @GetMapping("/users/{id}/friends/common/{otherId}")
    public List<User> getCommonFriends(@PathVariable("id") long id, @PathVariable("otherId") long otherId) {
        return userService.getCommonFriends(id, otherId);
    }
}
