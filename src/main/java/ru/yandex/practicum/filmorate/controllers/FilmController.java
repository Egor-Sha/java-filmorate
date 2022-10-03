package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@Slf4j
@RestController
public class FilmController {

    private LocalDate FIRST_MOVIE_DATE = LocalDate.of(1895, 12, 29);
    private long id = 1;
    private final Map<Long, Film> films = new HashMap<>();

    @PostMapping("/films")
    public Film create(@Valid @RequestBody Film film) throws ValidationException {

        validate(film);
        film.setId(id++);
        films.put(film.getId(), film);
        log.info("Добавлен фильм " + film.getName());
        return film;
    }

    @PutMapping("/films")
    public Film put(@Valid @RequestBody Film film) throws ValidationException {
        if (!(films.containsKey(film.getId()))) {
                throw new ValidationException("Фильм не найден, проверьте название");
        }

        validate(film);
        films.put(film.getId(), film);
        log.info("Фильм " + film.getName() + " изменен");
        return film;
    }

    @GetMapping("/films")
    public List<Film> getFilms() {

        return new ArrayList<>(films.values());
    }

    private void validate(Film film) throws ValidationException {
        LocalDate dateRelease = film.getReleaseDate();
        if (dateRelease.isBefore(FIRST_MOVIE_DATE)) {
                throw new ValidationException("Проверьте дату релиза");

        }
        if (film.getDescription().length()>200) {
                throw new ValidationException("Описание должно быть до 200 символов");
        }
    }
}
