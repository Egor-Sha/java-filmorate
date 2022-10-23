package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.exceptions.DataAlreadyExistException;
import ru.yandex.practicum.filmorate.exceptions.DataNotFoundException;
import ru.yandex.practicum.filmorate.model.StorageData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractStorage<T extends StorageData> implements Storage<T> {

    private final Map<Long, T> storage = new HashMap<>();

    @Override
    public void create(T data) {
        if(storage.containsKey(data.getId())) {
            throw new DataAlreadyExistException("duplicated: " + data.getId());
        }
        storage.put(data.getId(), data);
    }

    @Override
    public T get(long id) {
        final T data = storage.get(id);
        if (data == null) {
            throw new DataNotFoundException("id = " + id);
        }
        return data;
    }

    @Override
    public void update(T data) {
        if (!storage.containsKey(data.getId())) {
            throw new DataNotFoundException("id = " + data.getId());
        }
        storage.put(data.getId(), data);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(storage.values());
    }
}
