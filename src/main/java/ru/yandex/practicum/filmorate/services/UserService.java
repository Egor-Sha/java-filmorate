package ru.yandex.practicum.filmorate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.yandex.practicum.filmorate.exceptions.DataNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public User getUser(long id) {
        return userStorage.get(id);
    }

    public User create(User data) {
        validate(data);
        userStorage.create(data);
        return data;
    }

    public User update(User data) {
        if (data == null) {
            throw new DataNotFoundException("Нет данных");
        }
        validate(data);
        userStorage.update(data);
        return data;
    }

    public List<User> getAll() {
        return userStorage.getAll();
    }

    protected void validate(User data) {
        if (data.getLogin().contains(" ")) {
            throw new ValidationException("В логине не должно быть пробелов");
        }
        if (!StringUtils.hasText(data.getName())) {
            data.setName(data.getLogin());
        }
    }

    public void addFriend(long userId, long friendId) {
        final User user = getUser(userId);
        final User friend = getUser(friendId);
        if (user == null) {
            throw new DataNotFoundException("Пользователь не найден");
        }
        if (friend == null) {
            throw new DataNotFoundException("Друг пользователя не найден");
        }
        user.getFriendsId().add(friendId);
        friend.getFriendsId().add(userId);
    }

    public void removeFriend(long userId, long friendId) {
        final User user = getUser(userId);
        final User friend = getUser(friendId);
        if (user == null) {
            throw new DataNotFoundException("Пользователь не найден");
        }
        if (friend == null) {
            throw new DataNotFoundException("Друг пользователя не найден");
        }
        user.getFriendsId().remove(friendId);
        friend.getFriendsId().remove(userId);
    }

    public List<User> getFriends(long userId) {
        User user = getUser(userId);
        if (user == null) {
            throw new DataNotFoundException("Пользователь не найден");
        }
        return user.getFriendsId().stream().map(userStorage::get).collect(Collectors.toList());
    }

    public List<User> getCommonFriends(long userId, long friendId) {
        final User user = userStorage.get(userId);
        final User friend = userStorage.get(friendId);
        if (user == null) {
            throw new DataNotFoundException("Пользователь не найден");
        }
        if (friend == null) {
            throw new DataNotFoundException("Друг пользователя не найден");
        }
        Set<Long> userFriends = user.getFriendsId();
        Set<Long> friendFriends = friend.getFriendsId();
        return userFriends.stream()
                .filter(friendFriends::contains)
                .map(userStorage::get)
                .collect(Collectors.toList());
    }
}