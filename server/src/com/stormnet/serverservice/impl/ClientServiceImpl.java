package com.stormnet.serverservice.impl;

import com.stormnet.ja.Client;
import com.stormnet.serverservice.dao.PersonDao;
import com.stormnet.serverservice.dao.factory.DaoFactory;
import com.stormnet.serverservice.PersonService;

import java.util.List;

public class ClientServiceImpl implements PersonService<Client> {

    private PersonDao<Client> dao;

    public ClientServiceImpl() {

        this.dao = DaoFactory.getFactory().getClientDao();
    }

    @Override
    public void save(Client person) {
        dao.save(person);
    }


    @Override
    public List<Client> loadAll() {
        return dao.loadAll();
    }

    @Override
    public Client loadById(Integer id) {
        return dao.loadById(id);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

    @Override
    public void update(Client person) {
        dao.update(person);
    }

    @Override
    public Client loadPersonByLoginAndPassword(String login, String password) {
        return dao.loadPersonByLoginAndPassword(login, password);
    }
}
