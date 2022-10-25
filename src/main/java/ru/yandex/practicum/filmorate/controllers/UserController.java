package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.DataNotFoundException;
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

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") long id) throws DataNotFoundException {
        return service.getUser(id);
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

    @PutMapping("/users/{id}/friends/{friendId}")
    public void addFriend(@PathVariable long id, @PathVariable long friendId) {
        service.addFriend(id, friendId);
    }

    @DeleteMapping("/users/{id}/friends/{friendId}")
    public void removeFriend(@PathVariable long id, @PathVariable long friendId) {
        service.removeFriend(id, friendId);
    }

    @GetMapping("/users/{id}/friends")
    public Set<Long> getSetOfFriends(@PathVariable long id) {
        return service.getFriends(id);
    }

    @GetMapping("/users/{id}/friends/common/{otherId}")
    public Set<Long> getSetOfCommonFriends(@PathVariable long id, @PathVariable long otherId) {
        return service.getCommonFriends(id, otherId);
    }

}
