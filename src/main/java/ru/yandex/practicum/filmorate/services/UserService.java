package ru.yandex.practicum.filmorate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.yandex.practicum.filmorate.exceptions.DataNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    UserStorage userStorage;
    private long counter = 0L;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public User getUser(long id) {
        return userStorage.get(id);
    }

    public User create(User data) {
        validate(data);
        data.setId(++counter);
        userStorage.create(data);
        return data;
    }

    public User update(User data) throws DataNotFoundException {
        if (data == null){throw new DataNotFoundException("Нет данных");}
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

    public void addFriend(long userId, long friendId) throws DataNotFoundException {
        final User user = getUser(userId);
        final User friend = getUser(userId);
        if (user == null) {throw new DataNotFoundException("User not found");}
        if (friend == null) {throw new DataNotFoundException("Friend not found");}
        user.getFriendsId().add(friendId);
        friend.getFriendsId().add(userId);
    }

    public void removeFriend(long userId, long friendId) throws DataNotFoundException {
        final User user = getUser(userId);
        final User friend = getUser(userId);
        if (user == null) {throw new DataNotFoundException("User not found");}
        if (friend == null) {throw new DataNotFoundException("Friend not found");}
        user.getFriendsId().remove(friendId);
        friend.getFriendsId().remove(userId);
    }

    public Set<Long> getFriends(long userId) {
        final User user = userStorage.get(userId);
        return user.getFriendsId();
    }

    public Set<Long> getCommonFriends(long userId, long friendId) {
        final User user = userStorage.get(userId);
        final User friend = userStorage.get(friendId);
        Set<Long> common = new HashSet<>();
        common.addAll(user.getFriendsId());
        common.addAll(friend.getFriendsId());
        return common;
    }

}
