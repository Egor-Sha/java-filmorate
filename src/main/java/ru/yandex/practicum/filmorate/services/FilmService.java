package ru.yandex.practicum.filmorate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.DataNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService {

    private final LocalDate FIRST_MOVIE_DATE = LocalDate.of(1895, 12, 29);
    Comparator<Film> FILM_COMPARATOR = Comparator.comparingLong(Film::getRate);
    UserStorage userStorage;
    FilmStorage filmStorage;
    private long counter = 0L;

    @Autowired
    public FilmService(FilmStorage filmStorage, UserStorage userStorage) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
    }

    public Film getFilm(long id) {
        return filmStorage.get(id);
    }

    public Film create(Film data) {
        validate(data);
        data.setId(++counter);
        filmStorage.create(data);
        return data;
    }

    public Film update(Film data) {
        if (data == null){throw new DataNotFoundException("Film not found");}
        validate(data);
        filmStorage.update(data);
        return data;
    }

    public List<Film> getAll() {
        return filmStorage.getAll();
    }

    protected void validate(Film data) {
        if (data.getReleaseDate().isBefore(FIRST_MOVIE_DATE)) {
            throw new ValidationException("Проверьте дату релиза");
        }
        if (data.getDescription().length()>=200) {
            throw new ValidationException("Описание должно быть до 200 символов");
        }
    }

    public void addLike(long id, long userId) throws DataNotFoundException {
        final Film film = filmStorage.get(id);
        if (film == null) {throw new DataNotFoundException("Film not found");}
        if (userStorage.get(userId) == null) {throw new DataNotFoundException("User not found");}
        film.addLike(id);
    }

    public void removeLike(long id, long userId) {
        final Film film = filmStorage.get(id);
        if (film == null) {throw new DataNotFoundException("Film not found");}
        if (userStorage.get(userId) == null) {throw new DataNotFoundException("User not found");}
        film.removeLike(id);
    }

    public List<Film> getPopular(int count) {
        return filmStorage.getAll().stream()
                .sorted(Comparator.comparingInt((film-> (int) (film.getRate()*(-1)))))
                .limit(count)
                .collect(Collectors.toList());
    }
}




































