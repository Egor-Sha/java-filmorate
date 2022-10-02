package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.controllers.FilmController;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FilmControllerTests {

    @Autowired
    FilmController filmController;

    @Test
    void createFilmWithInvalidDateTest () throws ValidationException {
        Film film = new Film();
        film.setReleaseDate("1700-12-17");
        final ValidationException exception = assertThrows(
                ValidationException.class,
                () -> {
                    filmController.create(film);
                });
        assertEquals("Проверьте дату релиза", exception.getMessage());
    }

    @Test
    void createFilmWithTooLargeDescriptionTest () throws ValidationException {
        Film film = new Film();
        film.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean " +
                "commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis " +
                "parturient montes, nascetur ridiculus mus. Donec qua");
        film.setReleaseDate("1900-12-17");
        final ValidationException exception = assertThrows(
                ValidationException.class,
                () -> {
                    filmController.create(film);
                });
        assertEquals("Описание должно быть до 200 символов", exception.getMessage());
    }
}
