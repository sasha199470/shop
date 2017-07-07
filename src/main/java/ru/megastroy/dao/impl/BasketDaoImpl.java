package ru.megastroy.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.megastroy.dao.BasketDao;
import ru.megastroy.domain.Basket;

import java.util.List;

/**
 * Created by pccp on 12.06.2017.
 */
@Repository(value = "BasketDao")
@Transactional
public class BasketDaoImpl extends SessionFactoryHolder implements BasketDao{
    @Override
    public List<Basket> getAll() {
        Query query = getSession().createQuery("from Basket ");
        List<Basket> baskets = query.list();
        return baskets;
    }

    @Override
    public Basket get(long id) {
            Query query = getSession().createQuery("from Basket c where c.id=:id");

            query.setParameter("id", id);
            List<Basket> result = query.list();
            return !result.isEmpty() ? result.get(0) : null;
    }

    @Override
    public void add(Basket basket) {
        super.save(basket);
    }

    @Override
    public void update(Basket basket) {
        super.update(basket);
    }

    @Override
    public void delete(Basket basket) {
        super.delete(basket);
    }
}
