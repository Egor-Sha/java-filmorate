package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.services.FilmService;
import javax.validation.Valid;
import java.util.*;

@Slf4j
@RestController
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/films/{id}")
    public Film getFilm(@Valid @PathVariable("id") long id) {
        return filmService.getFilm(id);
    }

    @GetMapping("/films")
    public List<Film> getFilms() {
        return filmService.getAll();
    }

    @PostMapping("/films")
    public Film create(@Valid @RequestBody final Film film) {
        log.info("Добавлен фильм " + film.getName());
        return filmService.create(film);
    }

    @PutMapping("/films")
    public Film put(@Valid @RequestBody final Film film) {
        log.info("Фильм " + film.getName() + " изменен");
        return filmService.update(film);
    }


    @PutMapping("/films/{id}/like/{userId}")
    public void addLike(@PathVariable("id") long id, @PathVariable("userId") long userId) {
        filmService.addLike(id, userId);
    }

    @DeleteMapping("/films/{id}/like/{userId}")
    public void removeLike(@PathVariable("id") long id, @PathVariable("userId") long userId) {
        filmService.removeLike(id, userId);
    }

    @GetMapping("/films/popular")
    public Set<Film> getPopular(@RequestParam(defaultValue = "10", required = false) int count) {
        return filmService.getPopular(count);
    }
}
