package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.DataAlreadyExistException;
import ru.yandex.practicum.filmorate.exceptions.DataNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import java.util.*;

@Component
public class InMemoryUserStorage implements UserStorage {

    private long counter = 0L;
    private final Map<Long, User> storage = new HashMap<>();

    @Override
    public void create(User data) {
        if(storage.containsKey(data.getId())) {
            throw new DataAlreadyExistException("duplicated: " + data.getId());
        }
        data.setId(++counter);
        storage.put(data.getId(), data);
    }

    @Override
    public User get(long id) {
        final User data = storage.get(id);
        if (data == null) {
            throw new DataNotFoundException("id = " + id);
        }
        return data;
    }

    @Override
    public void update(User data) {
        if (!storage.containsKey(data.getId())) {
            throw new DataNotFoundException("id = " + data.getId());
        }
        storage.put(data.getId(), data);
    }

    @Override
    public ArrayList<User> getAll() {
        return new ArrayList<>(storage.values());
    }
}
