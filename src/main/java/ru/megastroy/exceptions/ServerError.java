package ru.megastroy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Sasha Andreeva on 16/06/16.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerError extends RuntimeException {
    public ServerError(String message) { super(message); }
    public static final long serialVersionUID = 0;
}
