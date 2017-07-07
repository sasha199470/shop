package ru.megastroy.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.megastroy.controllers.core.SessionController;
import ru.megastroy.domain.User;
import ru.megastroy.exceptions.AlreadyRegistered;
import ru.megastroy.exceptions.BadRequest;
import ru.megastroy.exceptions.NotExists;
import ru.megastroy.utility.EmailValidator;
import ru.megastroy.utility.PasswordHasher;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by Sasha Andreeva on 13.04.17.
 */
@Slf4j
@Transactional
@RestController
public class AuthController extends SessionController {

    @RequestMapping(value = "auth/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(final HttpServletRequest request,
                                            @RequestBody final UserRequest userRequest) {
        final String ip = getRequestIp(request);

        log.info("User register procedure from {}", ip);

        final String email = userRequest.getEmail();
        final EmailValidator emailValidator = new EmailValidator();
        if (!emailValidator.validate(email)) {
            log.error("Registration with invalid email: {}", email);
            throw new BadRequest("Invalid email " + email);
        }

        if(userDao.get(email) != null) {
            log.error("Email registration with already used email: {}", email);
            throw new AlreadyRegistered("Email " + email + " is already used in system");
        }

        final String password = PasswordHasher.hashPassword(userRequest.getPassword());
        final User user = new User(email, password);

        userDao.add(user);

        final String jwt = setSessionUser(user);
        return createdResponse(user, true, jwt, isSslRequest(request));
    }

    @RequestMapping(value = "auth/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(final HttpServletRequest request,
                                         @RequestBody final UserRequest userRequest) {
        final String ip = getRequestIp(request);

        log.info("Login userRequest procedure from {}:{}", ip, userRequest.getEmail());

        if (StringUtils.isEmpty(userRequest.getEmail()) || StringUtils.isEmpty(userRequest.getPassword())) {
            throw new BadRequest("Missed one or more request fields", 1);
        }

        final String email = userRequest.getEmail();
        final User user = userDao.get(email);
        if(user == null) {
            throw new NotExists("Account with this email doesn't exist:" + email);
        }
        final String password = userRequest.getPassword();
        if (!PasswordHasher.checkPassword(password, user.getPassword())) {
            log.error("No password hash in db");
            throw new BadRequest("Invalid password");
        }

        final String jwt = setSessionUser(user);
        return createdResponse(user, false, jwt, isSslRequest(request));
    }

    @RequestMapping(value = "auth/logout", method = RequestMethod.GET)
    public ResponseEntity<?> logout(final HttpServletRequest request) {
        final String ip = getRequestIp(request);

        log.info("Logout userRequest procedure from {}", ip);
        getSessionUser(request);

        return closeSession();
    }

    private static class UserRequest implements Serializable {
        @Getter
        private String email;

        @Getter
        private String password;

        @JsonCreator
        public UserRequest(@JsonProperty("email") String email,
                           @JsonProperty("password") String password) {
            this.email = email;
            this.password = password;
        }
    }
}
