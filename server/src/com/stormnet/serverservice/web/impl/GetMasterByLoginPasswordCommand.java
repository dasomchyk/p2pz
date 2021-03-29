package com.stormnet.serverservice.web.impl;

import com.stormnet.ja.Master;
import com.stormnet.serverservice.PersonService;
import com.stormnet.serverservice.factory.ServiceFactory;
import com.stormnet.serverservice.web.common.Command;
import com.stormnet.serverservice.web.common.Request;
import com.stormnet.serverservice.web.common.Response;
import com.stormnet.serverservice.web.socket.ResponseCode;

public class GetMasterByLoginPasswordCommand implements Command {

    @Override
    public void execute(Request request, Response response) {

        String loginStr = (String) request.getParameter("login");
        String passwordStr = (String) request.getParameter("password");


        if (loginStr.equals("") || passwordStr.equals("")) {
            System.out.println("Null login or password received");
            response.setResponseCode(ResponseCode.NotFoundCode);
            return;
        }

        PersonService<Master> personService = ServiceFactory.getServiceFactory().getMasterService();
        Master person = personService.loadPersonByLoginAndPassword(loginStr, passwordStr);

        if (person == null) {
            System.out.println("Object not found");
            response.setResponseCode(ResponseCode.NotFoundCode);
            return;
        }

        response.getJsonWriter().key("response-ja");
        response.getJsonWriter().array();
        response.getJsonWriter().object();
        response.getJsonWriter().key("id").value(person.getId().toString());
        response.getJsonWriter().key("lastName").value(person.getLastName());
        response.getJsonWriter().key("firstName").value(person.getFirstName());
        response.getJsonWriter().key("login").value(person.getLogin());
        response.getJsonWriter().key("password").value(person.getPassword());
        response.getJsonWriter().key("cabinet").value(person.getCabinet());
        response.getJsonWriter().endObject();
        response.getJsonWriter().endArray();

        response.setResponseCode(ResponseCode.OkCode);
    }
}
