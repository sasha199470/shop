package ru.megastroy.dao;

import ru.megastroy.domain.Category;
import ru.megastroy.domain.Category;

import java.util.List;

/**
 * Created by pccp on 16.04.2017.
 */
public interface CategoryDao {
    List<Category> getAll();
    Category get(long id);
    void add(Category category);
    void update (Category category);
    void delete (Category category);
}
