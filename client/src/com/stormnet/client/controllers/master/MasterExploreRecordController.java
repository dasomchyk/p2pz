package com.stormnet.client.controllers.master;

import com.stormnet.ja.Record;
import com.stormnet.client.common.AppWindows;
import com.stormnet.client.common.Controller;
import com.stormnet.client.common.WindowConfigs;
import com.stormnet.clientservice.RecordService;
import com.stormnet.clientservice.factory.ServiceFactory;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.io.IOException;

public class MasterExploreRecordController implements Controller<Record> {

    @FXML
    private Label recordId;

    @FXML
    private Label rating;

    @FXML
    private Label clientFirstName;

    @FXML
    private Label clientLastName;

    @FXML
    private Label date;

    @FXML
    private Label time;

    @FXML
    private Label cabinet;

    @FXML
    private Label clientComment;

    @FXML
    private CheckBox isFinished;

    @Override
    public void reloadWindow() {

    }

    @Override
    public void setFormObject(Record object) {
        recordId.setText(object.getId().toString());
        rating.setText(object.getRating());
        clientFirstName.setText(object.getClientFirstName());
        clientLastName.setText(object.getClientLastName());
        date.setText(object.getDate().toString());
        time.setText(object.getTime());
        cabinet.setText(object.getCabinet());
        isFinished.setSelected(object.getFinished());
    }

    @FXML
    public void saveBtnPressed() throws IOException {
        RecordService recordService = ServiceFactory.getServiceFactory().getRecordService();
        Record record = recordService.loadById(Integer.valueOf(recordId.getText()));
        record.setFinished(isFinished.isSelected());
        recordService.update(record);
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.MasterExploreRecordWindow);
        appWindows.reloadWindow(WindowConfigs.MasterMainWindow);
    }

    @FXML
    public void cancelBtnPressed() {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.MasterExploreRecordWindow);
    }
}

