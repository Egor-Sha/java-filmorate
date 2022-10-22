package ru.yandex.practicum.filmorate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.StorageData;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.Storage;

@Service
public class UserService extends AbstractService<User> {

    Storage<User> storage;

    @Autowired
    public UserService(Storage<User> storage) {
        this.storage = storage;
    }

    @Override
    protected void validate(User data) {
        if (data.getLogin().contains("")) {
            throw new ValidationException("В логине не должно быть пробелов");
        }
        if (data.getName() == null || data.getName().isEmpty()) {
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


}
