package com.stormnet.serverservice.dao.memory;

import com.stormnet.ja.Id;
import com.stormnet.ja.Person;
import com.stormnet.serverservice.dao.PersonDao;

import java.util.List;

public class MemoryPersonDao implements PersonDao {
    @Override
    public Person loadPersonByLoginAndPassword(String login, String password) {
        return null;
    }

    @Override
    public List loadAll() {
        return null;
    }

    @Override
    public Id loadById(Integer id) {
        return null;
    }

    @Override
    public void save(Id object) {

    }

    @Override
    public void update(Id object) {

    }

    @Override
    public void delete(Integer id) {

    }

}
