package com.stormnet.serverservice.dao;

import com.stormnet.ja.Id;

import java.util.List;

public interface BaseDao<T extends Id> {

    List<T> loadAll();

    T loadById(Integer id);

    void save(T object);

    void update(T object);

    void delete(Integer id);
}
