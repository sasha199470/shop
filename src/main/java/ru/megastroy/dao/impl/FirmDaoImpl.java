package ru.megastroy.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.megastroy.dao.FirmDao;
import ru.megastroy.domain.Category;
import ru.megastroy.domain.Firm;

import java.util.List;

/**
 * Created by pccp on 22.04.2017.
 */
@Repository(value = "FirmDao")
@Transactional
public class FirmDaoImpl extends SessionFactoryHolder implements FirmDao {
    @Override
    public List<Firm> getAll() {
        Query query = getSession().createQuery("from Firm ");
        List<Firm> firms = query.list();
        return firms;
    }
    @Override
    public Firm get(long id) {
        Query query = getSession().createQuery("from Firm c where c.id=:id");

        query.setParameter("id", id);
        List<Firm> result = query.list();
        return !result.isEmpty() ? result.get(0) : null;
    }

    @Override
    public void add(Firm firm) {
        super.save(firm);
    }

    @Override
    public void update(Firm firm) {
        super.update(firm);
    }

    @Override
    public void delete(Firm firm) {
        super.delete(firm);
    }
}
