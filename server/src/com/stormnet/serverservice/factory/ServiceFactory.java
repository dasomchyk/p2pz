package com.stormnet.serverservice.factory;

import com.stormnet.ja.Admin;
import com.stormnet.ja.Client;
import com.stormnet.ja.Master;
import com.stormnet.serverservice.PersonService;
import com.stormnet.serverservice.RecordService;

public abstract class ServiceFactory {

    public abstract RecordService getRecordService();

    public abstract PersonService<Client> getClientService();

    public abstract PersonService<Master> getMasterService();

    public abstract PersonService<Admin> getAdminService();

    public static ServiceFactory getServiceFactory(){
        return new ServiceFactoryImpl();
    }

}
