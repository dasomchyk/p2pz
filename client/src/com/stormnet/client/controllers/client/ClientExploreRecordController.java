package com.stormnet.client.controllers.client;

import com.stormnet.client.common.AppWindows;
import com.stormnet.client.common.Controller;
import com.stormnet.client.common.WindowConfigs;
import com.stormnet.ja.Record;
import com.stormnet.clientservice.RecordService;
import com.stormnet.clientservice.factory.ServiceFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientExploreRecordController implements Controller<Record>, Initializable {

    @FXML
    private Label recordId;

    @FXML
    private ComboBox<String> rating;

    @FXML
    private Label masterFirstName;

    @FXML
    private Label masterLastName;

    @FXML
    private Label date;

    @FXML
    private Label time;

    @FXML
    private Label cabinet;

    @Override
    public void reloadWindow() {

    }

    @Override
    public void setFormObject(Record object) {
        recordId.setText(object.getId().toString());
        rating.setValue(object.getRating());
        masterFirstName.setText(object.getMasterFirstName());
        masterLastName.setText(object.getMasterLastName());
        date.setText(object.getDate().toString());
        time.setText(object.getTime());
        cabinet.setText(object.getCabinet());

    }

    @FXML
    public void saveBtnPressed() throws IOException {
        RecordService recordService = ServiceFactory.getServiceFactory().getRecordService();
        Record record = recordService.loadById(Integer.valueOf(recordId.getText()));
        record.setRating(rating.getValue());
        recordService.update(record);
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.ClientExploreRecordWindow);
        appWindows.reloadWindow(WindowConfigs.ClientMainWindow);
    }

    @FXML
    public void cancelBtnPressed() {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.ClientExploreRecordWindow);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> allRates = rating.getItems();

        for (int i = 1; i < 6; i++) {
            allRates.add(String.valueOf(i));
        }
    }
}
