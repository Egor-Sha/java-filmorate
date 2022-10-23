package ru.yandex.practicum.filmorate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.Storage;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    Storage<User> storage;
    private long counter = 0L;

    @Autowired
    public UserService(Storage<User> storage) {
        this.storage = storage;
    }

    public User create(User data) {
        validate(data);
        data.setId(++counter);
        storage.create(data);
        return data;
    }

    public User get(long id) {
        return storage.get(id);
    }

    public User update(User data) {
        storage.get(data.getId());
        data.setId(data.getId());
        validate(data);
        storage.update(data);
        return data;
    }

    public List<User> getAll() {
        return storage.getAll();
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
        final User user = storage.get(userId);
        final User friend = storage.get(friendId);
        user.getFriendsId().add(friendId);
        friend.getFriendsId().add(userId);
    }

    public void removeFriend(long userId, long friendId) {
        final User user = storage.get(userId);
        final User friend = storage.get(friendId);
        storage.get(friendId);
        user.getFriendsId().remove(friendId);
        friend.getFriendsId().remove(userId);
    }

    public Set<Long> getFriends(long userId) {
        final User user = storage.get(userId);
        return user.getFriendsId();
    }

    public Set<Long> getCommonFriends(long userId, long friendId) {
        final User user = storage.get(userId);
        final User friend = storage.get(friendId);
        Set<Long> common = new HashSet<>();
        common.addAll(user.getFriendsId());
        common.addAll(friend.getFriendsId());
        return common;
    }
}
