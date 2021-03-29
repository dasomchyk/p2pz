package com.stormnet.serverservice.web.common;

public interface Command {

    void execute(Request request, Response response);

}

