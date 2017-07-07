package ru.megastroy.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Sasha Andreeva on 04/08/16.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotExists extends RuntimeException {

    @Getter
    String message = "";

    public NotExists(final String message) {
        this.message = message;
    }
    public static final long serialVersionUID = 0;
}
