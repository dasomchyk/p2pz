package com.stormnet.client.exceptions;

import com.stormnet.client.common.WindowConfigs;

public class UnknownWindowException extends RuntimeException {

    public UnknownWindowException(WindowConfigs config) {

        super("Can not find window with name - " + config);
    }
}