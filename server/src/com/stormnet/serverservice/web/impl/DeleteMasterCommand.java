package com.stormnet.serverservice.web.impl;

import com.stormnet.ja.Master;
import com.stormnet.serverservice.PersonService;
import com.stormnet.serverservice.factory.ServiceFactory;
import com.stormnet.serverservice.web.common.Command;
import com.stormnet.serverservice.web.common.Request;
import com.stormnet.serverservice.web.common.Response;
import com.stormnet.serverservice.web.socket.ResponseCode;

public class DeleteMasterCommand implements Command {

    @Override
    public void execute(Request request, Response response) {

        String idStr = (String) request.getParameter("id");
        Integer id = Integer.valueOf(idStr);

        if (idStr.equals("")) {
            System.out.println("Null id received");
            response.setResponseCode(ResponseCode.NotFoundCode);
            return;
        }

        PersonService<Master> personService = ServiceFactory.getServiceFactory().getMasterService();
        personService.delete(id);

        response.setResponseCode(ResponseCode.OkCode);
    }
}
