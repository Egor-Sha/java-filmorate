package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.DataAlreadyExistException;
import ru.yandex.practicum.filmorate.exceptions.DataNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryFilmStorage implements FilmStorage {

    private final Map<Long, Film> storage = new HashMap<>();

    @Override
    public void create(Film data) {
        if(storage.containsKey(data.getId())) {
            throw new DataAlreadyExistException("duplicated: " + data.getId());
        }
        storage.put(data.getId(), data);
    }

    @Override
    public Film get(long id) {
        final Film data = storage.get(id);
        if (data == null) {
            throw new DataNotFoundException("id = " + id);
        }
        return data;
    }

    @Override
    public void update(Film data) {
        if (!storage.containsKey(data.getId())) {
            throw new DataNotFoundException("Data not found, id = " + data.getId());
        }
        storage.put(data.getId(), data);
    }

    @Override
    public List<Film> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public List<Film> getPopular(int count) {
        return null;
    }

}
