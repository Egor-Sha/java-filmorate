package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.controllers.UserController;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserControllerTests {
    @Autowired
    UserController userController;
    @Test
    void createUserWithSpacesInLoginTest () throws ValidationException {
        User user = new User();
        user.setEmail("test1@yandextest.com");
        user.setLogin("log in");
        final ValidationException exception = assertThrows(
                ValidationException.class,
                () -> {
                    userController.create(user);
                });
        assertEquals("В логине не должно быть пробелов", exception.getMessage());
    }

    @Test
    void createUserWithoutNameTest () throws ValidationException {
        User user = new User();
        user.setEmail("test3@yandextest.com");
        user.setLogin("loginAndName");
        user.setBirthday(LocalDate.of(1995, 12, 29));
        user.setName("");
        userController.create(user);
        assertEquals("loginAndName", user.getName(), "Имя и логин не совпадают");
    }
}
