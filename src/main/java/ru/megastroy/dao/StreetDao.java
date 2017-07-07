package ru.megastroy.dao;

import ru.megastroy.domain.Buy;
import ru.megastroy.domain.Street;

/**
 * Created by pccp on 16.04.2017.
 */
public interface StreetDao {
    Street get(long id);
    void add(Street street);
    void update (Street street);
    void delete (Street street);
}
