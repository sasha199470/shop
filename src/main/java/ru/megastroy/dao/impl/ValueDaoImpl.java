package ru.megastroy.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.megastroy.dao.ValueDao;
import ru.megastroy.domain.Value;

import java.util.List;

/**
 * Created by pccp on 12.06.2017.
 */
@Repository(value = "ValueDao")
@Transactional
public class ValueDaoImpl extends SessionFactoryHolder implements ValueDao {
    @Override
    public List<Value> getAll() {
        Query query = getSession().createQuery("from Value ");
        List<Value> values = query.list();
        return values;
    }

    @Override
    public Value get(long id) {
        Query query = getSession().createQuery("from Value c where c.id=:id");

        query.setParameter("id", id);
        List<Value> result = query.list();
        return !result.isEmpty() ? result.get(0) : null;
    }

    @Override
    public void add(Value value) {
        super.save(value);

    }

    @Override
    public void update(Value value) {
        super.update(value);
    }

    @Override
    public void delete(Value value) {
        super.delete(value);
    }
}
