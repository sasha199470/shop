package ru.megastroy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

/**
 * Created by Sasha Andreeva on 24/02/16.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {
    private String name;
    private long id = 0;

    public ResourceNotFoundException(final String name, long id) {
        this.name = name;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return "The requested URL was not found on the server. " +
                "If you entered the URL manually please check your spelling and try again.";
    }
}
