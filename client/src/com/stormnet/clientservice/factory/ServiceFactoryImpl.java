package com.stormnet.clientservice.factory;

import com.stormnet.clientservice.PersonService;
import com.stormnet.clientservice.RecordService;
import com.stormnet.clientservice.impl.AdminServiceImpl;
import com.stormnet.clientservice.impl.ClientServiceImpl;
import com.stormnet.clientservice.impl.MasterServiceImpl;
import com.stormnet.clientservice.impl.RecordServiceImpl;
import com.stormnet.ja.Admin;
import com.stormnet.ja.Client;
import com.stormnet.ja.Master;

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
