package com.stormnet.serverservice.impl;

import com.stormnet.ja.Record;
import com.stormnet.serverservice.dao.RecordDao;
import com.stormnet.serverservice.dao.factory.DaoFactory;
import com.stormnet.serverservice.RecordService;

import java.util.List;

public class RecordServiceImpl implements RecordService {

    private RecordDao dao;

    public RecordServiceImpl() {

        this.dao = DaoFactory.getFactory().getRecordDao();
    }

    @Override
    public void save(Record record) {

        dao.save(record);
    }

    @Override
    public List<Record> loadAll() {

        return dao.loadAll();
    }

    @Override
    public Record loadById(Integer id) {

        return dao.loadById(id);
    }

    @Override
    public void delete(Integer id) {

        dao.delete(id);
    }

    @Override
    public void update(Record record) {

        dao.update(record);
    }
}

