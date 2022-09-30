package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.exceptions.NewException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserController {

    private final Map<String, User> users = new HashMap<>();

    @PostMapping
    public User create(@RequestBody User user) throws NewException {
        if(user.getEmail() == null || user.getEmail().isBlank()) {
            throw new NewException("Адрес электронной почты не может быть пустым.");
        }
        if(users.containsKey(user.getEmail())) {
            throw new NewException("Пользователь с электронной почтой " +
                    user.getEmail() + " уже зарегистрирован.");
        }
        users.put(user.getEmail(), user);
        return user;
    }

    @GetMapping
    public Collection<User> findAll() {
        return users.values();
    }



    @PutMapping
    public User put(@RequestBody User user) throws NewException {
        if(user.getEmail() == null || user.getEmail().isBlank()) {
            throw new NewException("Адрес электронной почты не может быть пустым.");
        }
        users.put(user.getEmail(), user);

        return user;
    }



}
