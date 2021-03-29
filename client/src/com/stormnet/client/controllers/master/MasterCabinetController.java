package com.stormnet.client.controllers.master;


import com.stormnet.client.common.AppWindows;
import com.stormnet.client.common.Controller;
import com.stormnet.client.common.Session;
import com.stormnet.client.common.WindowConfigs;
import com.stormnet.ja.Master;
import com.stormnet.ja.Record;
import com.stormnet.clientservice.PersonService;
import com.stormnet.clientservice.RecordService;
import com.stormnet.clientservice.factory.ServiceFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class MasterCabinetController implements Controller {

    @FXML
    private Label id;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private Label login;

    @FXML
    private Label rating;

    @FXML
    private TextField cabinet;

    @FXML
    private PasswordField password;

    public void saveBtnPressed() throws IOException {
        if (firstName.getText().equals("") || lastName.getText().equals("") || password.getText().equals("") || cabinet.getText().equals("")) {
            throw new RuntimeException("Please, fill all fields.");
        }

        PersonService<Master> masterService = ServiceFactory.getServiceFactory().getMasterService();
        Master currentPerson = masterService.loadById(Integer.valueOf(id.getText()));

        currentPerson.setFirstName(firstName.getText());
        currentPerson.setLastName(lastName.getText());
        currentPerson.setPassword(password.getText());
        currentPerson.setCabinet(cabinet.getText());

        masterService.update(currentPerson);
        AppWindows.getInstance().hideWindow(WindowConfigs.MasterCabinetWindow);
    }

    public void cancelBtnPressed() {
        AppWindows.getInstance().hideWindow(WindowConfigs.MasterCabinetWindow);

    }

    @Override
    public void reloadWindow() throws IOException {
        PersonService<Master> masterService = ServiceFactory.getServiceFactory().getMasterService();
        Master currentPerson = masterService.loadById(Session.getInstance().getPersonId());
        id.setText(currentPerson.getId().toString());
        firstName.setText(currentPerson.getFirstName());
        lastName.setText(currentPerson.getLastName());
        login.setText(currentPerson.getLogin());
        password.setText(currentPerson.getPassword());
        cabinet.setText(currentPerson.getCabinet());

        RecordService recordService = ServiceFactory.getServiceFactory().getRecordService();
        List<Record> allRecords = recordService.loadAll();
        int totalRateSum = 0;
        int marksCount = 0;
        double myRate = 0;
        for (Record record : allRecords) {
            if (!record.getRating().equals("") && record.getMasterId().equals(Session.getInstance().getPersonId())) {
                totalRateSum += Integer.valueOf(record.getRating());
                marksCount++;
            }
        }

        if (marksCount != 0) {
            myRate = (double) totalRateSum / marksCount;
        }
        DecimalFormat ratingAccuracy = new DecimalFormat("#.00");
        rating.setText(String.valueOf(ratingAccuracy.format(myRate)));
    }

    @Override
    public void setFormObject(Object object) {
    }
}
