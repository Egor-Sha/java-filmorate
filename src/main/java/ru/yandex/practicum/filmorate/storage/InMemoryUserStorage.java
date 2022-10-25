package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.DataAlreadyExistException;
import ru.yandex.practicum.filmorate.exceptions.DataNotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryUserStorage implements UserStorage {

    private final Map<Long, User> storage = new HashMap<>();

    @Override
    public void create(User data) {
        if(storage.containsKey(data.getId())) {
            throw new DataAlreadyExistException("duplicated: " + data.getId());
        }
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
    public List<User> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void addFriend(long userId, long friendId) {};

    @Override
    public void removeFriend(long userId, long friendId) {};

    @Override
    public List<User> getFriends(Long userId) {
        return null;
    }

    @Override
    public List<User> getCommonFriends(Long userId, Long otherId) {
        return null;
    }

}
