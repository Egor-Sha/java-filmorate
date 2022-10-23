package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Map;

@Component
public class InMemoryUserStorage extends AbstractStorage<User> implements UserStorage {

    Map<Long, User> storage;

}
