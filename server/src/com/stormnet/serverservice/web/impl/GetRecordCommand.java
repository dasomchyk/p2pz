package com.stormnet.serverservice.web.impl;

import com.stormnet.ja.Record;
import com.stormnet.serverservice.web.common.Command;
import com.stormnet.serverservice.web.common.Request;
import com.stormnet.serverservice.web.common.Response;
import com.stormnet.serverservice.web.socket.ResponseCode;
import com.stormnet.serverservice.RecordService;
import com.stormnet.serverservice.factory.ServiceFactory;

public class GetRecordCommand implements Command {

    @Override
    public void execute(Request request, Response response) {
        String idStr = (String) request.getParameter("id");
        Integer id = Integer.valueOf(idStr);

        if (idStr.equals("")) {
            System.out.println("Null id received");
            response.setResponseCode(ResponseCode.NotFoundCode);
            return;
        }

        RecordService recordService = ServiceFactory.getServiceFactory().getRecordService();
        Record record = recordService.loadById(id);

        if (record == null) {
            System.out.println("Object not found");
            response.setResponseCode(ResponseCode.NotFoundCode);
            return;
        }

        response.getJsonWriter().key("response-ja");
        response.getJsonWriter().array();
        response.getJsonWriter().object();
        response.getJsonWriter().key("id").value(record.getId().toString());
        response.getJsonWriter().key("clientId").value(record.getClientId().toString());
        response.getJsonWriter().key("isFinished").value(record.getFinished().toString());
        response.getJsonWriter().key("masterLastName").value(record.getMasterLastName());
        response.getJsonWriter().key("masterFirstName").value(record.getMasterFirstName());
        response.getJsonWriter().key("clientLastName").value(record.getClientLastName());
        response.getJsonWriter().key("clientFirstName").value(record.getClientFirstName());
        response.getJsonWriter().key("masterId").value(record.getMasterId().toString());
        response.getJsonWriter().key("date").value(record.getDate().toString());
        response.getJsonWriter().key("time").value(record.getTime());
        response.getJsonWriter().key("cabinet").value(record.getCabinet());
        response.getJsonWriter().key("rating").value(record.getRating());
        response.getJsonWriter().endObject();
        response.getJsonWriter().endArray();

        response.setResponseCode(ResponseCode.OkCode);
    }
}