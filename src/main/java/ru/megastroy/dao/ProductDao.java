package ru.megastroy.dao;

import ru.megastroy.domain.Product;

import java.util.List;

/**
 * Created by pccp on 16.04.2017.
 */
public interface ProductDao {
    List<Product> getAll();
    Product get(long id);
    void add(Product product);
    void update (Product product);
    void delete (Product product);
}
