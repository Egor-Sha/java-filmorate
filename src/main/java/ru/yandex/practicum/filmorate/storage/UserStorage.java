package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.exceptions.DataNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import java.util.List;

public interface UserStorage {

    List<User> getAll();

    User get(long id) throws DataNotFoundException;

    void create(User data);

    void update(User data) throws DataNotFoundException;

}
