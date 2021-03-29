package com.stormnet.serverservice.dao.memory;

import com.stormnet.serverservice.dao.PersonDao;
import com.stormnet.serverservice.dao.RecordDao;
import com.stormnet.serverservice.dao.factory.DaoFactory;

public class MemoryDaoFactory extends DaoFactory {


    @Override
    public RecordDao getRecordDao() {
        return new MemoryRecordDao();
    }

    @Override
    public PersonDao getClientDao() {
        return null;
    }

    @Override
    public PersonDao getMasterDao() {
        return null;
    }

    @Override
    public PersonDao getAdminDao() {
        return null;
    }

}