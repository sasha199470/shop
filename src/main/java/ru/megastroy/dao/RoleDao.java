package ru.megastroy.dao;

import ru.megastroy.domain.Role;

import java.util.List;

/**
 * Created by pccp on 16.04.2017.
 */
public interface RoleDao {
    List<Role> getAll();
    Role get(long id);
    void add(Role role);
    void update (Role role);
    void delete (Role role);
}
