package com.stormnet.client.exceptions;

import com.stormnet.client.common.WindowConfigs;

public class CanNotCreateWindowException extends RuntimeException {

    public CanNotCreateWindowException(Throwable cause, WindowConfigs windowName) {

        super("Can not create Window: " + windowName, cause);
    }
}
