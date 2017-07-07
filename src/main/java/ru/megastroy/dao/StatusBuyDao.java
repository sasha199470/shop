package ru.megastroy.dao;

import ru.megastroy.domain.Buy;
import ru.megastroy.domain.StatusBuy;

import java.util.List;

/**
 * Created by pccp on 16.04.2017.
 */
public interface StatusBuyDao {
    StatusBuy get(long id);
    void add(StatusBuy statusBuy);
    void update (StatusBuy statusBuy);
    void delete (StatusBuy statusBuy);
}
