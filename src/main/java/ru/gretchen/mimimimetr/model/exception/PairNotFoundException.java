package ru.gretchen.mimimimetr.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PairNotFoundException extends RuntimeException{
    public PairNotFoundException(UUID id) {
        super("Пара котиков с id " + id + " не найдена");
    }
}
