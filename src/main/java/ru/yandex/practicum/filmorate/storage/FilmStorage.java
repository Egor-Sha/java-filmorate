package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.DataNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import java.util.List;

@Component
public interface FilmStorage {

    List<Film> getAll();

    Film get(long id) throws DataNotFoundException;

    void create(Film data);

    void update(Film data) throws DataNotFoundException;

}
