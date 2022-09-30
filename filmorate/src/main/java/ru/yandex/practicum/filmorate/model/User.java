package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Data
public class User {

    @NotNull
    int id;
    @Email
    String email;
    @NotBlank
    String login; //check spaces
    String name; //if empty then login
    @Past
    String birthday; //format
}
