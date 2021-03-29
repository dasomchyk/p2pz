package com.stormnet.serverservice.dao.exception;

import com.stormnet.serverservice.dao.factory.DaoTypes;

public class UnknownDaoTypeException extends RuntimeException {

    public UnknownDaoTypeException(DaoTypes type) {

        super("Can not find DAO Factory for  " + type + "!");
    }
}
