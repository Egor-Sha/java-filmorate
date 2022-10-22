package ru.yandex.practicum.filmorate.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataAlreadyExistException extends RuntimeException {
    public DataAlreadyExistException(final String message) {
        super(message);
        log.warn("Data exists issue: " + message);
    }
}