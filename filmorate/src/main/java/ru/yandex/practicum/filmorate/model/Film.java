package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class Film {

    @NotNull
    int id;
    @NotBlank
    String name;
    @NotBlank
    @Max(200)
    String description;
    @NotBlank
    String releaseDate; //after 28.12.1895 + format
    @Positive
    int duration;

}
