package com.stormnet.client.controllers.client;

import com.stormnet.client.common.AppWindows;
import com.stormnet.client.common.Controller;
import com.stormnet.client.common.Session;
import com.stormnet.client.common.WindowConfigs;
import com.stormnet.ja.Client;
import com.stormnet.ja.Master;
import com.stormnet.ja.Record;
import com.stormnet.clientservice.PersonService;
import com.stormnet.clientservice.RecordService;
import com.stormnet.clientservice.factory.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientGetRecordController implements Controller, Initializable {

    @FXML
    private ComboBox VisitTime;

    @FXML
    private ComboBox<Master> ChooseMaster;

    @FXML
    private DatePicker ChooseDate;

    @FXML
    public void timeListGeneration() throws IOException {

        RecordService recordService = ServiceFactory.getServiceFactory().getRecordService();
        List<Record> allRecords = recordService.loadAll();
        List<Record> allRecordsOfThisMaster = new ArrayList<>();

        for (Record record : allRecords) {
            int masterID = record.getMasterId();
            if (masterID == ChooseMaster.getValue().getId()) {
                allRecordsOfThisMaster.add(record);
            }
        }

        List<Record> allRecordsOfThisMasterForThisDate = new ArrayList<>();

        for (Record record : allRecordsOfThisMaster) {
            LocalDate date = record.getDate();
            if (date.equals(ChooseDate.getValue())) {
                allRecordsOfThisMasterForThisDate.add(record);
            }
        }

        ObservableList<String> allTimesAvailable = FXCollections.observableArrayList();

        for (int i = 8; i < 18; i++) {
            allTimesAvailable.add(i + ":00");
        }

        for (Record record : allRecordsOfThisMasterForThisDate) {
            String time = record.getTime();
            allTimesAvailable.remove(time);
        }

        VisitTime.setItems(allTimesAvailable);

    }

    @FXML
    public void saveBtnPressed() throws IOException {
        if (ChooseMaster.getValue() == null || VisitTime.getValue() == null || ChooseDate.getValue() == null) {
            throw new RuntimeException("Please, fill all parameters.");
        }

        Integer clientId = Session.getInstance().getPersonId();
        Client client = new Client();
        try {
            client = ServiceFactory.getServiceFactory().getClientService().loadById(clientId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Record record = new Record();
        record.setClientId(clientId);
        record.setClientFirstName(client.getFirstName());
        record.setClientLastName(client.getLastName());
        record.setMasterId(ChooseMaster.getValue().getId());
        record.setMasterLastName(ChooseMaster.getValue().getLastName());
        record.setMasterFirstName(ChooseMaster.getValue().getFirstName());
        record.setDate(ChooseDate.getValue());
        record.setTime(VisitTime.getValue().toString());
        record.setCabinet(ChooseMaster.getValue().getCabinet());
        record.setRating("");

        RecordService recordService = ServiceFactory.getServiceFactory().getRecordService();
        recordService.save(record);

        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.ClientGetRecordWindow);
        appWindows.reloadWindow(WindowConfigs.ClientMainWindow);
        appWindows.showWindow(WindowConfigs.ClientMainWindow);
    }

    @FXML
    public void cancelBtnPressed() throws IOException {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.ClientGetRecordWindow);
        appWindows.reloadWindow(WindowConfigs.ClientMainWindow);
    }

    @Override
    public void reloadWindow() throws IOException {
        PersonService<Master> masterService = ServiceFactory.getServiceFactory().getMasterService();
        List<Master> allPersons = masterService.loadAll();

        ObservableList<Master> gridItems = ChooseMaster.getItems();
        gridItems.clear();
        gridItems.addAll(allPersons);
    }

    @Override
    public void setFormObject(Object object) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChooseMaster.getSelectionModel().selectFirst();
        ChooseMaster.setCellFactory(new Callback<ListView<Master>, ListCell<Master>>() {
            @Override
            public ListCell<Master> call(ListView<Master> l) {
                return new ListCell<Master>() {
                    @Override
                    protected void updateItem(Master master, boolean empty) {
                        super.updateItem(master, empty);
                        if (master == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(master.getFirstName() + " " + master.getLastName());
                        }
                    }
                };
            }
        });

        ChooseMaster.setConverter(new StringConverter<Master>() {
            @Override
            public String toString(Master master) {
                if (master == null) {
                    return null;
                } else {
                    return master.getFirstName() + " " + master.getLastName();
                }
            }

            @Override
            public Master fromString(String id) {
                return null;
            }
        });

        ChooseDate.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.getDayOfWeek() == DayOfWeek.SUNDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY || date.isBefore(LocalDate.now()));
            }
        });
        ChooseDate.setEditable(false);

        PersonService<Master> masterService = ServiceFactory.getServiceFactory().getMasterService();
        List<Master> allPersons = new ArrayList<>();
        try {
            allPersons = masterService.loadAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObservableList<Master> all = FXCollections.observableArrayList(allPersons);
        ChooseMaster.setItems(all);
    }
}
