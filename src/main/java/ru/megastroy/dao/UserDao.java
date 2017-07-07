package ru.megastroy.dao;

import ru.megastroy.domain.Buy;
import ru.megastroy.domain.User;

import java.util.List;

/**
 * Created by pccp on 16.04.2017.
 */
public interface UserDao {
    List<User> getAll();
    User get(long id);
    User get(String email);
    void add(User user);
    void update (User user);
    void delete (User user);

}
