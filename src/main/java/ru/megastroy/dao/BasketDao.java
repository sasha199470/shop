package ru.megastroy.dao;

import ru.megastroy.domain.Basket;

import java.util.List;

/**
 * Created by pccp on 12.06.2017.
 */
public interface BasketDao {

        List<Basket> getAll();
        Basket get(long id);
        void add(Basket basket);
        void update (Basket basket);
        void delete (Basket basket);


}
