package ru.megastroy.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.megastroy.dao.CategoryDao;
import ru.megastroy.domain.Category;

import java.util.List;

/**
 * Created by pccp on 22.04.2017.
 */
@Repository(value = "CatalogDao")
@Transactional
public class CatalogDaoImpl extends SessionFactoryHolder implements CategoryDao {
    @Override
    public List<Category> getAll() {
        Query query = getSession().createQuery("from Category ");
        List<Category> categoties = query.list();
        return categoties;
    }
    @Override
    public Category get(long id) {
        Query query = getSession().createQuery("from Category c where c.id=:id");

        query.setParameter("id", id);
        List<Category> result = query.list();
        return !result.isEmpty() ? result.get(0) : null;
    }

    @Override
    public void add(Category category) {
        super.save(category);
    }

    @Override
    public void update(Category category) {
        super.update(category);

    }

    @Override
    public void delete(Category category) {
        super.delete(category);

    }
}
