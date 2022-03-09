package ru.gretchen.mimimimetr.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CatNotFoundException extends RuntimeException{
    public CatNotFoundException(UUID id) {
        super("Кот с id " + id + " не найден");
    }
}
