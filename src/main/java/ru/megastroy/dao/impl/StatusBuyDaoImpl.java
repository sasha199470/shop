package ru.megastroy.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.megastroy.dao.StatusBuyDao;
import ru.megastroy.domain.StatusBuy;

import java.util.List;

/**
 * Created by pccp on 22.04.2017.
 */
@Repository(value = "StatusBuyDao")
@Transactional
public class StatusBuyDaoImpl extends SessionFactoryHolder implements StatusBuyDao{
    @Override
    public StatusBuy get(long id) {
        Query query = getSession().createQuery("from StatusBuy c where c.id=:id");

        query.setParameter("id", id);
        List<StatusBuy> result = query.list();
        return !result.isEmpty() ? result.get(0) : null;
    }

    @Override
    public void add(StatusBuy statusBuy) {
        super.save(statusBuy);
    }

    @Override
    public void update(StatusBuy statusBuy) {
        super.update(statusBuy);
    }

    @Override
    public void delete(StatusBuy statusBuy) {
        super.delete(statusBuy);
    }
}
