package com.stormnet.client.controllers.admin;

import com.stormnet.client.common.AppWindows;
import com.stormnet.client.common.Controller;
import com.stormnet.client.common.WindowConfigs;
import com.stormnet.ja.Record;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdminExploreRecordController implements Controller<Record> {

    @FXML
    private Label recordId;

    @FXML
    private Label rating;

    @FXML
    private Label clientFirstName;

    @FXML
    private Label clientLastName;

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
        rating.setText(object.getRating());
        clientFirstName.setText(object.getClientFirstName());
        clientLastName.setText(object.getClientLastName());
        masterFirstName.setText(object.getMasterFirstName());
        masterLastName.setText(object.getMasterLastName());
        date.setText(object.getDate().toString());
        time.setText(object.getTime());
        cabinet.setText(object.getCabinet());
    }

    @FXML
    public void cancelBtnPressed() {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.AdminExploreRecordWindow);
    }
}

