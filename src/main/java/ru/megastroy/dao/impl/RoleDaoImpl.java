package ru.megastroy.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.megastroy.dao.RoleDao;
import ru.megastroy.domain.Role;

import java.util.List;

/**
 * Created by pccp on 22.04.2017.
 */
@Repository(value = "RoleDao")
@Transactional
public class RoleDaoImpl extends SessionFactoryHolder implements RoleDao {
    @Override
    public List<Role> getAll() {
        Query query = getSession().createQuery("from Role ");
        List<Role> roles = query.list();
        return roles;
    }
    @Override
    public Role get(long id) {
        Query query = getSession().createQuery("from Role c where c.id=:id");

        query.setParameter("id", id);
        List<Role> result = query.list();
        return !result.isEmpty() ? result.get(0) : null;
    }

    @Override
    public void add(Role role) {
        super.save(role);
    }

    @Override
    public void update(Role role) {
        super.update(role);
    }

    @Override
    public void delete(Role role) {
        super.delete(role);
    }
}
