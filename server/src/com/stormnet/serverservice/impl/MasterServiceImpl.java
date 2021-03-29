package com.stormnet.serverservice.impl;

import com.stormnet.ja.Master;
import com.stormnet.serverservice.dao.PersonDao;
import com.stormnet.serverservice.dao.factory.DaoFactory;
import com.stormnet.serverservice.PersonService;

import java.util.List;

public class MasterServiceImpl implements PersonService<Master> {

    private PersonDao<Master> dao;

    public MasterServiceImpl() {
        this.dao = DaoFactory.getFactory().getMasterDao();
    }

    @Override
    public void save(Master person) {
        dao.save(person);
    }

    @Override
    public List<Master> loadAll() {
        return dao.loadAll();
    }

    @Override
    public Master loadById(Integer id) {
        return dao.loadById(id);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

    @Override
    public void update(Master person) {
        dao.update(person);
    }

    @Override
    public Master loadPersonByLoginAndPassword(String login, String password) {
        return dao.loadPersonByLoginAndPassword(login, password);
    }
}
