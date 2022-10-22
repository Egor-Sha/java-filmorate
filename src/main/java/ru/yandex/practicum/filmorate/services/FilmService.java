package ru.yandex.practicum.filmorate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.Storage;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService extends AbstractService<Film> {

    private final LocalDate FIRST_MOVIE_DATE = LocalDate.of(1895, 12, 29);
    Comparator<Film> FILM_COMPARATOR = Comparator.comparingLong(Film::getRate);
    Storage<User> userStorage;
    Storage<Film> storage;

    @Autowired
    public FilmService(Storage<Film> storage, Storage<User> userStorage) {
        this.storage = storage;
        this.userStorage = userStorage;
    }

    @Override
    protected void validate(Film data) {
        if (data.getReleaseDate().isBefore(FIRST_MOVIE_DATE)) {
            throw new ValidationException("Проверьте дату релиза");
        }
        if (data.getDescription().length()>200) {
            throw new ValidationException("Описание должно быть до 200 символов");
        }
    }

    public void addLike(long id, long userId) {
        final Film film = storage.get(id);
        userStorage.get(userId);
        film.addLike(userId);
    }

    public void removeLike(long id, long userId) {
        final Film film = storage.get(id);
        userStorage.get(userId);
        film.removeLike(userId);
    }

    public List<Film> getPopular(int count) {
        return storage.getAll().stream()
                .sorted(FILM_COMPARATOR)
                .limit(count)
                .collect(Collectors.toList());
    }
}




































