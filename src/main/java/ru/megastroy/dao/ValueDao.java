package ru.megastroy.dao;

import ru.megastroy.domain.Value;

import java.util.List;

/**
 * Created by pccp on 12.06.2017.
 */
public interface ValueDao {
        List<Value> getAll();
        Value get(long id);
        void add(Value value);
        void update (Value value);
        void delete (Value value);


}
