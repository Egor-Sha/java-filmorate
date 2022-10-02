package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@Slf4j
@RestController
public class UserController {
    private final Map<Integer, User> users = new HashMap<>();
    private int id = 1;
    @PostMapping("/users")
    public User create(@Valid @RequestBody User user) throws ValidationException {

        if(user.getLogin().contains(" ")) {
            throw new ValidationException("В логине не должно быть пробелов");
        }
        LocalDate dateBirthday = LocalDate.parse(user.getBirthday());
        if(dateBirthday.isAfter(LocalDate.now())) {
            throw new ValidationException("Проверьте дату рождения");
        }
        if(!StringUtils.hasText(user.getName())) {
            user.setName(user.getLogin());
        }
        user.setId(id++);
        users.put(user.getId(), user);
        log.info("Создан пользователь " + user.getLogin());
        return user;
    }
    @PutMapping("/users")
    public User put(@Valid @RequestBody User user) throws ValidationException {

        if(!(users.containsKey(user.getId()))) {
            throw new ValidationException("Пользователь не найден");
        }

        if(user.getLogin().contains(" ")) {
            throw new ValidationException("В логине не должно быть пробелов");
        }
        if(!StringUtils.hasText(user.getName())) {
            user.setName(user.getLogin());
        }
        LocalDate dateBirthday = LocalDate.parse(user.getBirthday());
        if(dateBirthday.isAfter(LocalDate.now())) {
            throw new ValidationException("Проверьте дату рождения, должно быть в прошлом");
        }
        users.put(user.getId(), user);
        log.info("Пользователь " + user.getLogin() + " изменен");
        return user;
    }
    @GetMapping("/users")
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }
}
