package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class Film {
    private long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @Past
    private LocalDate releaseDate;
    @Positive
    private int duration;
}
