package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.exceptions.DataNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {

    List<Film> getAll();
    Film get(long id) throws DataNotFoundException;
    void create(Film data);
    void update(Film data) throws DataNotFoundException;
    List<Film> getPopular(int count);


}
