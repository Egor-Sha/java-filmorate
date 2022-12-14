package ru.yandex.practicum.filmorate.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(final String message) {
        super(message);
        log.warn("No data issue: " + message);
    }
}
