package ru.megastroy.dao;

import ru.megastroy.domain.Buy;
import ru.megastroy.domain.Subcategory;
import ru.megastroy.domain.Subcategory;

import java.util.List;

/**
 * Created by pccp on 16.04.2017.
 */
public interface SubcategoryDao {
    List<Subcategory> getAll();
    Subcategory get (long id);
    void add(Subcategory subcategory);
    void update (Subcategory subcategory);
    void delete (Subcategory subcategory);
}
