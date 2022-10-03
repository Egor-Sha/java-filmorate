package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class User {
    private long id;
    @Email
    private String email;
    @NotEmpty
    private String login;
    private String name;
    @Past
    private LocalDate birthday;
}
