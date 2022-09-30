package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
public class UserController {

    private final Map<String, User> users = new HashMap<>();
    @PostMapping("/users")
    public User create(@Valid @RequestBody User user) throws ValidationException {
        if(users.containsKey(user.getEmail())) {
            throw new ValidationException("Пользователь с электронной почтой " +
                    user.getEmail() + " уже зарегистрирован.");
        }
        user.setId((int) (Math.random() * 10));
        users.put(user.getEmail(), user);
        log.info("Object of " + User.class + " added");
        return user;
    }
    @PutMapping("/users")
    public User put(@Valid @RequestBody User user) throws ValidationException {

        users.put(user.getEmail(), user);
        log.info("Object of " + User.class + " changed");
        return user;
    }
    @GetMapping("/users")
    public Collection<User> findAll() {
        return users.values();
    }
}
