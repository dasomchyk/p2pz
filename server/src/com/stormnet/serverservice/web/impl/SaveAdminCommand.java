package com.stormnet.serverservice.web.impl;

import com.stormnet.ja.Admin;
import com.stormnet.serverservice.PersonService;
import com.stormnet.serverservice.factory.ServiceFactory;
import com.stormnet.serverservice.web.common.Command;
import com.stormnet.serverservice.web.common.Request;
import com.stormnet.serverservice.web.common.Response;
import com.stormnet.serverservice.web.socket.ResponseCode;

public class SaveAdminCommand implements Command {

    @Override
    public void execute(Request request, Response response) {

        String lastNameStr = (String) request.getParameter("lastName");

        String firstNameStr = (String) request.getParameter("firstName");

        String loginStr = (String) request.getParameter("login");

        String passwordStr = (String) request.getParameter("password");

        Admin person = new Admin();
        person.setFirstName(firstNameStr);
        person.setLastName(lastNameStr);
        person.setLogin(loginStr);
        person.setPassword(passwordStr);

        PersonService<Admin> personService = ServiceFactory.getServiceFactory().getAdminService();
        personService.save(person);

        response.setResponseCode(ResponseCode.OkCode);
    }
}

