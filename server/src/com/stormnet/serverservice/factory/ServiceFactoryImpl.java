package com.stormnet.serverservice.factory;

import com.stormnet.ja.Admin;
import com.stormnet.ja.Client;
import com.stormnet.ja.Master;
import com.stormnet.serverservice.PersonService;
import com.stormnet.serverservice.RecordService;
import com.stormnet.serverservice.impl.AdminServiceImpl;
import com.stormnet.serverservice.impl.ClientServiceImpl;
import com.stormnet.serverservice.impl.MasterServiceImpl;
import com.stormnet.serverservice.impl.RecordServiceImpl;

public class ServiceFactoryImpl extends ServiceFactory {

    @Override
    public RecordService getRecordService() {
        return new RecordServiceImpl();
    }

    @Override
    public PersonService<Client> getClientService() {
        return new ClientServiceImpl();
    }

    @Override
    public PersonService<Master> getMasterService() {
        return new MasterServiceImpl();
    }

    @Override
    public PersonService<Admin> getAdminService() {
        return new AdminServiceImpl();
    }
}
