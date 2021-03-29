package com.stormnet.serverservice.web.common;

public interface Request {

    String getCommandName();

    Object getParameter(String paramName);
}
