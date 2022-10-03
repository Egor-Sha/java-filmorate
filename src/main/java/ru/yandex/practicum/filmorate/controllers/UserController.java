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
    private final Map<Long, User> users = new HashMap<>();
    private long id = 1;

    @PostMapping("/users")
    public User create(@Valid @RequestBody User user) {

        validate(user);
        user.setId(id++);
        users.put(user.getId(), user);
        log.info("Создан пользователь " + user.getLogin());
        return user;
    }

    @PutMapping("/users")
    public User put(@Valid @RequestBody User user) {

        if (!(users.containsKey(user.getId()))) {
                throw new ValidationException("Пользователь не найден");
        }

        validate(user);
        users.put(user.getId(), user);
        log.info("Пользователь " + user.getLogin() + " изменен");
        return user;
    }

    @GetMapping("/users")
    public List<User> getUsers() {

        return new ArrayList<>(users.values());
    }

    private void validate(User user) throws ValidationException {
        if (user.getLogin().contains(" ")) {
                throw new ValidationException("В логине не должно быть пробелов");
        }

        if (!StringUtils.hasText(user.getName())) {
            user.setName(user.getLogin());
        }
    }
}
