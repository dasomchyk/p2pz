package com.stormnet.serverservice.web.impl;

import com.stormnet.ja.Record;
import com.stormnet.serverservice.web.common.Command;
import com.stormnet.serverservice.web.common.Request;
import com.stormnet.serverservice.web.common.Response;
import com.stormnet.serverservice.web.socket.ResponseCode;
import com.stormnet.serverservice.RecordService;
import com.stormnet.serverservice.factory.ServiceFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UpdateRecordCommand implements Command {

    public void execute(Request request, Response response) {

        String idStr = (String) request.getParameter("id");
        Integer id = Integer.valueOf(idStr);

        if (idStr.equals("")) {
            System.out.println("Null id received");
            response.setResponseCode(ResponseCode.NotFoundCode);
            return;
        }

        String clientIdStr = (String) request.getParameter("clientId");
        Integer clientId = Integer.valueOf(clientIdStr);

        String isFinishedStr = (String) request.getParameter("isFinished");
        Boolean isFinished = Boolean.valueOf(isFinishedStr);

        String masterLastNameStr = (String) request.getParameter("masterLastName");

        String masterFirstNameStr = (String) request.getParameter("masterFirstName");

        String clientLastNameStr = (String) request.getParameter("clientLastName");

        String clientFirstNameStr = (String) request.getParameter("clientFirstName");

        String dateStr = (String) request.getParameter("date");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        LocalDate date = LocalDate.parse(dateStr, formatter);

        String timeStr = (String) request.getParameter("time");

        String masterIdStr = (String) request.getParameter("masterId");
        Integer masterId = Integer.valueOf(masterIdStr);

        String cabinetStr = (String) request.getParameter("cabinet");

        String ratingStr = (String) request.getParameter("rating");

        Record record = new Record();
        record.setId(id);
        record.setFinished(isFinished);
        record.setClientId(clientId);
        record.setMasterLastName(masterLastNameStr);
        record.setMasterFirstName(masterFirstNameStr);
        record.setClientLastName(clientLastNameStr);
        record.setClientFirstName(clientFirstNameStr);
        record.setDate(date);
        record.setTime(timeStr);
        record.setMasterId(masterId);
        record.setCabinet(cabinetStr);
        record.setRating(ratingStr);

        RecordService recordService = ServiceFactory.getServiceFactory().getRecordService();
        recordService.update(record);

        response.setResponseCode(ResponseCode.OkCode);
    }
}
