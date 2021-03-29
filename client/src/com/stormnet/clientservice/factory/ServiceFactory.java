package com.stormnet.clientservice.factory;

import com.stormnet.clientservice.PersonService;
import com.stormnet.clientservice.RecordService;
import com.stormnet.ja.Admin;
import com.stormnet.ja.Client;
import com.stormnet.ja.Master;

public abstract class ServiceFactory {

    public abstract RecordService getRecordService();

    public abstract PersonService<Client> getClientService();

    public abstract PersonService<Master> getMasterService();

    public abstract PersonService<Admin> getAdminService();

    public static ServiceFactory getServiceFactory(){
        return new ServiceFactoryImpl();
    }

}