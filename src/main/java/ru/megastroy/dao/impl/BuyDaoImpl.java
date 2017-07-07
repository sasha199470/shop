package ru.megastroy.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.megastroy.dao.BuyDao;
import ru.megastroy.domain.Buy;

import java.util.List;

/**
 * Created by pccp on 20.04.2017.
 */
@Repository(value = "BuyDao")
@Transactional
public class BuyDaoImpl extends SessionFactoryHolder implements BuyDao {
    @Override
    public List<Buy>  getAll() {
        Query query = getSession().createQuery("from Buy ");
        List<Buy> buys = query.list();
        return buys;
    }
    @Override
    public Buy get(long id) {
        Query query = getSession().createQuery("from Buy c where c.id=:id");

        query.setParameter("id", id);
        List<Buy> result = query.list();
        return !result.isEmpty() ? result.get(0) : null;
    }

    @Override
    public void add(Buy byu) {
        super.save(byu);
    }

    @Override
    public void update(Buy buy) {
        super.update(buy);
    }

    @Override
    public void delete(Buy buy){
        super.delete(buy);
    }

}
