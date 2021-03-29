package com.stormnet.serverservice.dao.factory;

import com.stormnet.ja.Admin;
import com.stormnet.ja.Client;
import com.stormnet.ja.Master;
import com.stormnet.serverservice.dao.PersonDao;
import com.stormnet.serverservice.dao.RecordDao;
import com.stormnet.serverservice.dao.exception.UnknownDaoTypeException;
import com.stormnet.serverservice.dao.memory.MemoryDaoFactory;
import com.stormnet.serverservice.dao.xml.XmlDaoFactory;

public abstract class DaoFactory {

    public abstract RecordDao getRecordDao();

    public abstract PersonDao<Client> getClientDao();

    public abstract PersonDao<Master> getMasterDao();

    public abstract PersonDao<Admin> getAdminDao();

    public static DaoFactory getFactory() {
        return getFactory(DaoTypes.XmlDao);
    }

    public static DaoFactory getFactory(DaoTypes type) {
        switch (type) {
            case MemoryDao: {
                return new MemoryDaoFactory();
            }

            case XmlDao: {
                return new XmlDaoFactory();
            }
        }

        throw new UnknownDaoTypeException(type);
    }
}
