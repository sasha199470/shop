package ru.megastroy.http;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.megastroy.exceptions.*;

/**
 * Created by Sasha Andreeva on 24/02/16.
 */
@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Error {
        @Getter @Setter
        private int code;
        @Getter @Setter
        private String message;
        @Getter @Setter
        private int index;

        public Error(int code, final String message) {
            this.code = code;
            this.message = message;
        }

        public Error(int code, final String message, int index) {
            this.code = code;
            this.message = message;
            this.index = index;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private class ErrorResponse {
        @Getter @Setter
        private Error error;
        @Getter @Setter
        private String status;

        public ErrorResponse(final Error error, final String status) {
            this.error = error;
            this.status = status;
        }
    }

    private static HttpHeaders jsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundResource(RuntimeException ex, WebRequest request) {
        HttpHeaders headers = jsonHeaders();

        ResourceNotFoundException rex = (ResourceNotFoundException) ex;

        ErrorResponse error = new ErrorResponse(
                new Error(404, rex.getDescription() +
                        " [" + rex.getName() + "=" + rex.getId() + "]"),
                "ERR");

        return handleExceptionInternal(ex, error, headers, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({UnAuthorized.class})
    protected ResponseEntity<Object> handleUnauthorizedRequest(UnAuthorized ex, WebRequest request) {
        HttpHeaders headers = jsonHeaders();

        ErrorResponse error = new ErrorResponse(
                new Error(401, "Unauthorized request" + (!"".equals(ex.getMessage()) ? ": " + ex.getMessage() : "")),
                "ERR");

        return handleExceptionInternal(ex, error, headers, HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler({AlreadyRegistered.class})
    protected ResponseEntity<Object> handleAreadyRegisteredEmail(AlreadyRegistered ex, WebRequest request) {
        HttpHeaders headers = jsonHeaders();

        ErrorResponse error = new ErrorResponse(
                new Error(400, ex.getMessage(), ex.getIndex()),
                "ERR"
        );

        return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({BadRequest.class})
    protected ResponseEntity<Object> handleBadRequest(BadRequest ex, WebRequest request) {
        HttpHeaders headers = jsonHeaders();

        final String message = ex.getMessage();
        final int index = ex.getIndex();
        ErrorResponse error = new ErrorResponse(
                new Error(400, StringUtils.isEmpty(message) ? "Bad request" : "Bad request: " + message, index),
                "ERR"
        );

        return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({NotExists.class})
    protected ResponseEntity<Object> handleNotExists(NotExists ex, WebRequest request) {
        HttpHeaders headers = jsonHeaders();

        ErrorResponse error = new ErrorResponse(
                new Error(404, ex.getMessage()),
                "ERR"
        );

        return handleExceptionInternal(ex, error, headers, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleNotExists(Exception ex, WebRequest request) {
        HttpHeaders headers = jsonHeaders();

        ErrorResponse error = new ErrorResponse(
                new Error(500, "Unhandled exception"),
                "ERR"
        );

        ex.printStackTrace();

        return handleExceptionInternal(ex, error, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
