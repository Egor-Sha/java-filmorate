package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class User {
    int id;
    @Email
    String email;
    @NotEmpty
    String login;
    String name;
    @NotEmpty
    String birthday;
}
