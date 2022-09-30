package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Film {

    final int id;
    final String name;
    final String description;
    final String releaseDate;
    final int duration;

}
