package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class User {

    final int id;
    final String email;
    final String login;
    final String name;
    final String birthday;
}
