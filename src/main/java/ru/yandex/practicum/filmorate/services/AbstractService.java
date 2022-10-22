package ru.yandex.practicum.filmorate.services;

import ru.yandex.practicum.filmorate.model.StorageData;
import ru.yandex.practicum.filmorate.storage.Storage;
import java.util.List;

public abstract class AbstractService<T extends StorageData> {

    private long counter = 0L;
    Storage<T> storage;

    protected abstract void validate(T data);

    public T create(T data) {
        validate(data);
        data.setId(++counter);
        storage.create(data);
        return data;
    }

    public T get(long id) {
        return storage.get(id);
    }

    public T update(T data) {
        storage.get(data.getId());
        data.setId(data.getId());
        validate(data);
        storage.update(data);
        return data;
    }

    public List<T> getAll() {
        return storage.getAll();
    }
}
