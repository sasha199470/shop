package ru.megastroy.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.megastroy.dao.UserDao;
import ru.megastroy.domain.User;

import java.util.List;

/**
 * Created by pccp on 22.04.2017.
 */
@Repository(value = "UserDao")
@Transactional
public class UserDaoImpl extends SessionFactoryHolder implements UserDao{
    @Override
    public List<User> getAll() {
        Query query = getSession().createQuery("from User ");
        List<User> users = query.list();
        return users;
    }
    @Override
    public User get(long id) {
        Query query = getSession().createQuery("from User c where c.id=:id");

        query.setParameter("id", id);
        List<User> result = query.list();
        return !result.isEmpty() ? result.get(0) : null;
    }

    @Override
    public User get(String email) {
        Query query = getSession().createQuery("from User c where c.email=:email");

        query.setParameter("email", email);
        List<User> result = query.list();
        return !result.isEmpty() ? result.get(0) : null;
    }

    @Override
    public void add(User user) {
        super.save(user);
    }

    @Override
    public void update(User user) {
        super.update(user);
    }

    @Override
    public void delete(User user) {
        super.delete(user);
    }
}
