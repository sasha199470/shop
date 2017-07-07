package ru.megastroy.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.megastroy.dao.StreetDao;
import ru.megastroy.domain.Street;

import java.util.List;

/**
 * Created by pccp on 22.04.2017.
 */
@Repository(value = "StreetDao")
@Transactional
public class StreetDaoImpl extends SessionFactoryHolder implements StreetDao{
    @Override
    public Street get(long id) {
        Query query = getSession().createQuery("from Street c where c.id=:id");

        query.setParameter("id", id);
        List<Street> result = query.list();
        return !result.isEmpty() ? result.get(0) : null;
    }

    @Override
    public void add(Street street) {
        super.save(street);
    }

    @Override
    public void update(Street street) {
        super.update(street);
    }

    @Override
    public void delete(Street street) {
        super.delete(street);
    }
}
