package com.stormnet.serverservice.impl;

import com.stormnet.ja.Admin;
import com.stormnet.serverservice.PersonService;
import com.stormnet.serverservice.dao.PersonDao;
import com.stormnet.serverservice.dao.factory.DaoFactory;

import java.util.List;

public class AdminServiceImpl implements PersonService<Admin> {

    private PersonDao<Admin> dao;

    public AdminServiceImpl() {
        this.dao = DaoFactory.getFactory().getAdminDao();
    }

    @Override
    public void save(Admin person) {
        dao.save(person);
    }

    @Override
    public List<Admin> loadAll() {
        return dao.loadAll();
    }

    @Override
    public Admin loadById(Integer id) {
        return dao.loadById(id);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

    @Override
    public void update(Admin person) {
        dao.update(person);
    }

    @Override
    public Admin loadPersonByLoginAndPassword(String login, String password) {
        return dao.loadPersonByLoginAndPassword(login, password);
    }
}
