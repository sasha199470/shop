package ru.megastroy.dao;

import ru.megastroy.domain.Firm;

import java.util.List;

/**
 * Created by pccp on 16.04.2017.
 */
public interface FirmDao {
    List<Firm> getAll();
    Firm get(long id);
    void add(Firm firm);
    void update (Firm firm);
    void delete (Firm firm);
}
