package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import java.util.Map;

@Component
public class InMemoryFilmStorage extends AbstractStorage<Film> implements FilmStorage {

    Map<Long, Film> storage;

}
