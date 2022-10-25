package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.DataNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.services.FilmService;
import javax.validation.Valid;
import java.util.*;

@Slf4j
@RestController
public class FilmController {
    FilmService service;

    @Autowired
    public FilmController(FilmService service) {
        this.service = service;
    }

    @GetMapping("/films/{id}")
    public Film getFilm(@Valid @PathVariable("id") long id) throws DataNotFoundException {
        return service.getFilm(id);
    }

    @GetMapping("/films")
    public List<Film> getFilms() {
        return service.getAll();
    }

    @PostMapping("/films")
    public Film create(@Valid @RequestBody final Film film) {
        log.info("Добавлен фильм " + film.getName());
        return service.create(film);
    }

    @PutMapping("/films")
    public Film put(@Valid @RequestBody final Film film) {
        log.info("Фильм " + film.getName() + " изменен");
        return service.update(film);
    }


    @PutMapping("/films/{id}/like/{userId}")
    public void addLike(@PathVariable long id, @PathVariable long userId) {
        service.addLike(id, userId);
    }

    @DeleteMapping("/films/{id}/like/{userId}")
    public void removeLike(@PathVariable long id, @PathVariable long userId) {
        service.removeLike(id, userId);
    }

    @GetMapping("GET /films/popular?count={count}")
    public List<Film> getPopular(@RequestParam(defaultValue = "10") @PathVariable int count) {
        return service.getPopular(count);
    }
}
