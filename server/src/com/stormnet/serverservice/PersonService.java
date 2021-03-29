package com.stormnet.serverservice;

import com.stormnet.ja.Person;

import java.util.List;

public interface PersonService<T extends Person> {

    void save(T person);

    List<T> loadAll();

    T loadById(Integer id);

    void delete(Integer id);

    void update(T person);

    T loadPersonByLoginAndPassword(String login, String password);

}

