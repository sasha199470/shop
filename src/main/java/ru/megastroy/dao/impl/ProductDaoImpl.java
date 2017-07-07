package ru.megastroy.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.megastroy.dao.ProductDao;
import ru.megastroy.domain.Product;

import java.util.List;

/**
 * Created by pccp on 22.04.2017.
 */
@Repository(value = "ProductDao")
@Transactional
public class ProductDaoImpl extends SessionFactoryHolder implements ProductDao {
    @Override
    public List<Product> getAll() {
        Query query = getSession().createQuery("from Product");
        List<Product> products = query.list();
        return products;
    }
    @Override
    public Product get(long id) {
        Query query = getSession().createQuery("from Product c where c.id=:id");

        query.setParameter("id", id);
        List<Product> result = query.list();
        return !result.isEmpty() ? result.get(0) : null;
    }

    @Override
    public void add(Product product) {
        super.save(product);
    }

    @Override
    public void update(Product product) {
        super.update(product);
    }

    @Override
    public void delete(Product product) {
        super.delete(product);

    }
}
