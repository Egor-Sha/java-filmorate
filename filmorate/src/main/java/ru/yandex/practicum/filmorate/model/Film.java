package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class Film {

    int id;
    @NotEmpty
    String name;
    @NotEmpty
    String description;
    @NotEmpty
    String releaseDate;
    @Positive
    int duration;

}
