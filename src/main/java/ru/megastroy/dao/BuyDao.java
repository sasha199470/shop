package ru.megastroy.dao;

import ru.megastroy.domain.Buy;

import java.util.List;

/**
 * Created by pccp on 16.04.2017.
 */
public interface BuyDao {
    List<Buy> getAll();
    Buy get(long id);
    void add(Buy byu);
    void update (Buy buy);
    void delete (Buy buy);
}
