package com.stormnet.serverservice.dao.xml;

import com.stormnet.ja.Admin;
import com.stormnet.ja.Client;
import com.stormnet.ja.Master;
import com.stormnet.serverservice.dao.PersonDao;
import com.stormnet.serverservice.dao.RecordDao;
import com.stormnet.serverservice.dao.factory.DaoFactory;

public class XmlDaoFactory extends DaoFactory {

    @Override
    public RecordDao getRecordDao() {
        return new XmlRecordDao();
    }

    @Override
    public PersonDao<Client> getClientDao() {
        return new XmlClientDao();
    }

    @Override
    public PersonDao<Master> getMasterDao() {
        return new XmlMasterDao();
    }

    @Override
    public PersonDao<Admin> getAdminDao() {
        return new XmlAdminDao();
    }
}
