package ru.megastroy.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.megastroy.dao.SubcategoryDao;
import ru.megastroy.domain.Subcategory;

import java.util.List;

/**
 * Created by pccp on 22.04.2017.
 */
@Repository(value = "SubcategoryDao")
@Transactional
public class SubcategoryDaoImpl extends SessionFactoryHolder implements SubcategoryDao {
    @Override
    public List<Subcategory> getAll() {
        Query query = getSession().createQuery("from Subcategory ");
        List<Subcategory> subdirectories = query.list();
        return subdirectories;
    }
    @Override
    public Subcategory get(long id) {
        Query query = getSession().createQuery("from Subcategory c where c.id=:id");

        query.setParameter("id", id);
        List<Subcategory> result = query.list();
        return !result.isEmpty() ? result.get(0) : null;
    }

    @Override
    public void add(Subcategory subcategory) {
        super.save(subcategory);
    }

    @Override
    public void update(Subcategory subcategory) {
        super.update(subcategory);
    }

    @Override
    public void delete(Subcategory subcategory) {
        super.update(subcategory);
    }
}
