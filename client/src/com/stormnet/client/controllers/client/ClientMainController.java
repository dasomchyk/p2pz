package com.stormnet.client.controllers.client;

import com.stormnet.client.common.AppWindows;
import com.stormnet.client.common.Controller;
import com.stormnet.client.common.Session;
import com.stormnet.client.common.WindowConfigs;
import com.stormnet.ja.Record;
import com.stormnet.clientservice.RecordService;
import com.stormnet.clientservice.factory.ServiceFactory;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientMainController implements Controller<Record>, Initializable {

    @FXML
    private TableView<Record> allRecordsTable;


    public void getRecordBtnPressed() {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.createWindow(WindowConfigs.ClientGetRecordWindow);
        appWindows.hideWindow(WindowConfigs.ClientMainWindow);
        appWindows.showWindow(WindowConfigs.ClientGetRecordWindow);
    }

    public void personalCabinetBtnPressed() throws IOException {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.createWindow(WindowConfigs.ClientCabinetWindow);
        appWindows.showWindow(WindowConfigs.ClientCabinetWindow);
        appWindows.reloadWindow(WindowConfigs.ClientCabinetWindow);
    }

    public void exitBtnPressed() {
        System.exit(0);
    }

    @Override
    public void reloadWindow() throws IOException {
        RecordService recordService = ServiceFactory.getServiceFactory().getRecordService();
        List<Record> allRecords = recordService.loadAll();

        List<Record> myRecords = new ArrayList<>();

        for (Record record : allRecords) {
            int myId = record.getClientId();
            if (myId == Session.getInstance().getPersonId()) {
                myRecords.add(record);
            }
        }

        ObservableList<Record> gridItems = allRecordsTable.getItems();
        gridItems.clear();
        gridItems.addAll(myRecords);
    }

    @Override
    public void setFormObject(Record object) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<Record, String> masterColumn = new TableColumn<>("Master");
        masterColumn.setMinWidth(100);
        masterColumn.setCellValueFactory(new MasterStringFactory());

        allRecordsTable.getColumns().add(masterColumn);


        TableColumn<Record, String> activeColumn = new TableColumn<>("Status");
        activeColumn.setMinWidth(100);
        activeColumn.setCellValueFactory(new IsActiveStringFactory());

        allRecordsTable.getColumns().add(activeColumn);


        TableColumn<Record, GridPane> actionsColumn = new TableColumn<>("Actions");
        actionsColumn.setMinWidth(150);
        actionsColumn.setCellValueFactory(new ButtonsCellFactory());

        allRecordsTable.getColumns().add(actionsColumn);
    }

    private class MasterStringFactory implements Callback<TableColumn.CellDataFeatures<Record, String>, ObservableValue<String>> {

        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Record, String> param) {
            String master = param.getValue().getMasterFirstName() + " " + param.getValue().getMasterLastName();
            return new ReadOnlyStringWrapper(master);
        }
    }

    private class IsActiveStringFactory implements Callback<TableColumn.CellDataFeatures<Record, String>, ObservableValue<String>> {

        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Record, String> param) {
            boolean isActive = param.getValue().getFinished();
            String activeAsString;
            if (isActive) {
                activeAsString = "Finished";
            } else {
                activeAsString = "Waiting";
            }

            return new ReadOnlyStringWrapper(activeAsString);
        }
    }

    private class ButtonsCellFactory implements Callback<TableColumn.CellDataFeatures<Record, GridPane>, ObservableValue<GridPane>> {

        @Override
        public ObservableValue<GridPane> call(TableColumn.CellDataFeatures<Record, GridPane> param) {
            Record record = param.getValue();

            Button viewBtn = new Button("view");
            viewBtn.setOnAction(new ViewRecordEvent(record));

            Button editBtn = new Button("edit");
            editBtn.setOnAction(new EditRecordEvent(record));

            Button deleteBtn = new Button("delete");
            deleteBtn.setOnAction(new DeleteRecordEvent(record.getId()));

            GridPane panel = new GridPane();
            panel.setHgap(2);
            panel.setVgap(2);
            panel.setPadding(new Insets(2, 2, 2, 2));

            panel.add(viewBtn, 0, 0);
            panel.add(editBtn, 1, 0);
            panel.add(deleteBtn, 2, 0);

            if (!record.getFinished()) {
                viewBtn.setDisable(true);
                deleteBtn.setDisable(false);
                editBtn.setDisable(false);
            } else {
                viewBtn.setDisable(false);
                deleteBtn.setDisable(true);
                editBtn.setDisable(true);
            }


            return new SimpleObjectProperty<>(panel);
        }
    }

    private class EditRecordEvent implements EventHandler<ActionEvent> {

        private Record editedRecord;

        public EditRecordEvent(Record record) {
            this.editedRecord = record;
        }

        @Override
        public void handle(ActionEvent event) {
            AppWindows appWindows = AppWindows.getInstance();

            appWindows.createWindow(WindowConfigs.ClientEditRecordWindow);
            try {
                appWindows.setFormObject(WindowConfigs.ClientEditRecordWindow, editedRecord);
            } catch (IOException e) {
                e.printStackTrace();
            }
            appWindows.showWindow(WindowConfigs.ClientEditRecordWindow);
        }
    }

    private class DeleteRecordEvent implements EventHandler<ActionEvent> {

        private Integer recordId;

        public DeleteRecordEvent(Integer recordId) {
            this.recordId = recordId;
        }

        @Override
        public void handle(ActionEvent event) {
            RecordService recordService = ServiceFactory.getServiceFactory().getRecordService();
            try {
                recordService.delete(recordId);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                AppWindows.getInstance().reloadWindow(WindowConfigs.ClientMainWindow);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ViewRecordEvent implements EventHandler<ActionEvent> {

        private Record currentRecord;

        public ViewRecordEvent(Record record) {
            this.currentRecord = record;
        }

        @Override
        public void handle(ActionEvent event) {
            AppWindows appWindows = AppWindows.getInstance();

            appWindows.createWindow(WindowConfigs.ClientExploreRecordWindow);
            try {
                appWindows.setFormObject(WindowConfigs.ClientExploreRecordWindow, currentRecord);
            } catch (IOException e) {
                e.printStackTrace();
            }
            appWindows.showWindow(WindowConfigs.ClientExploreRecordWindow);
        }
    }
}