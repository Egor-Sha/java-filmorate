package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class FilmController {

    private final Map<String, Film> films = new HashMap<>();
    @PostMapping("/films")
    public Film create(@Valid @RequestBody Film film) throws ValidationException {
        if(films.containsKey(film.getName())) {
            throw new ValidationException("Пользователь с электронной почтой " +
                    film.getName() + " уже зарегистрирован.");
        }
        film.setId((int) (Math.random() * 10));
        films.put(film.getName(), film);
        log.info("Object of " + Film.class + " added");
        return film;
    }
    @PutMapping("/films")
    public Film put(@RequestBody Film film) throws ValidationException {

        films.put(film.getName(), film);
        log.info("Object of " + Film.class + " changed");
        return film;
    }
    @GetMapping("/films")
    public Collection<Film> findAll() {
        return films.values();
    }
}
