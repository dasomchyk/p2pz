package com.stormnet.serverservice.web.impl;

import com.stormnet.ja.Master;
import com.stormnet.serverservice.web.common.Command;
import com.stormnet.serverservice.web.common.Request;
import com.stormnet.serverservice.web.common.Response;
import com.stormnet.serverservice.web.socket.ResponseCode;
import com.stormnet.serverservice.PersonService;
import com.stormnet.serverservice.factory.ServiceFactory;

public class UpdateMasterCommand implements Command {


    public void execute(Request request, Response response) {

        String idStr = (String) request.getParameter("id");
        Integer id = Integer.valueOf(idStr);

        if (idStr.equals("")) {
            System.out.println("Null id received");
            response.setResponseCode(ResponseCode.NotFoundCode);
            return;
        }

        String lastNameStr = (String) request.getParameter("lastName");

        String firstNameStr = (String) request.getParameter("firstName");

        String loginStr = (String) request.getParameter("login");

        String passwordStr = (String) request.getParameter("password");

        String cabinetStr = (String) request.getParameter("cabinet");

        Master person = new Master();
        person.setId(id);
        person.setFirstName(firstNameStr);
        person.setLastName(lastNameStr);
        person.setLogin(loginStr);
        person.setPassword(passwordStr);
        person.setCabinet(cabinetStr);

        PersonService<Master> personService = ServiceFactory.getServiceFactory().getMasterService();
        personService.update(person);

        response.setResponseCode(ResponseCode.OkCode);
    }
}
