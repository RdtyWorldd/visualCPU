package ru.itmm.visualcpu.dao;

import java.util.List;

public interface CrudDAO<T> {
    void add(T t);
    void delete(T t);
    void update(T t);

    void delete(int id);

    void updateAll(List<T> list);
    void deleteAll();

    List<T> getAll();
}
