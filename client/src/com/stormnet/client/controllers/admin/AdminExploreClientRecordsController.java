package com.stormnet.client.controllers.admin;

import com.stormnet.client.common.AppWindows;
import com.stormnet.client.common.Controller;
import com.stormnet.client.common.WindowConfigs;
import com.stormnet.ja.Client;
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

public class AdminExploreClientRecordsController implements Controller<Client>, Initializable {

    private Integer thisClientId;

    @FXML
    private TableView<Record> allRecordsTable;

    @FXML
    public void closeBtnPressed() {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.AdminExploreClientRecordsWindow);
    }

    @Override
    public void reloadWindow() throws IOException {
        RecordService recordService = ServiceFactory.getServiceFactory().getRecordService();
        List<Record> allRecords = recordService.loadAll();

        List<Record> myRecords = new ArrayList<>();

        for (Record record : allRecords) {
            int clientId = record.getClientId();
            if (clientId == thisClientId) {
                myRecords.add(record);
            }
        }

        ObservableList<Record> gridItems = allRecordsTable.getItems();
        gridItems.clear();
        gridItems.addAll(myRecords);
    }

    @Override
    public void setFormObject(Client object) {

        thisClientId = object.getId();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<Record, String> masterColumn = new TableColumn<>("Master");
        masterColumn.setMinWidth(100);
        masterColumn.setCellValueFactory(new AdminExploreClientRecordsController.MasterStringFactory());

        allRecordsTable.getColumns().add(masterColumn);

        TableColumn<Record, String> clientColumn = new TableColumn<>("Client");
        clientColumn.setMinWidth(100);
        clientColumn.setCellValueFactory(new AdminExploreClientRecordsController.ClientStringFactory());

        allRecordsTable.getColumns().add(clientColumn);


        TableColumn<Record, String> activeColumn = new TableColumn<>("Status");
        activeColumn.setMinWidth(100);
        activeColumn.setCellValueFactory(new AdminExploreClientRecordsController.IsActiveStringFactory());


        allRecordsTable.getColumns().add(activeColumn);

        TableColumn<Record, GridPane> actionsColumn = new TableColumn<>("Actions");
        actionsColumn.setMinWidth(50);
        actionsColumn.setCellValueFactory(new AdminExploreClientRecordsController.ButtonsCellFactory());

        allRecordsTable.getColumns().add(actionsColumn);
    }

    private class MasterStringFactory implements Callback<TableColumn.CellDataFeatures<Record, String>, ObservableValue<String>> {

        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Record, String> param) {
            String master = param.getValue().getMasterFirstName() + " " + param.getValue().getMasterLastName();
            return new ReadOnlyStringWrapper(master);
        }
    }

    private class ClientStringFactory implements Callback<TableColumn.CellDataFeatures<Record, String>, ObservableValue<String>> {

        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Record, String> param) {
            String client = param.getValue().getClientFirstName() + " " + param.getValue().getClientLastName();
            return new ReadOnlyStringWrapper(client);
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
            viewBtn.setOnAction(new AdminExploreClientRecordsController.ViewRecordEvent(record));

            GridPane panel = new GridPane();
            panel.setHgap(2);
            panel.setVgap(2);
            panel.setPadding(new Insets(2, 2, 2, 2));

            panel.add(viewBtn, 0, 0);

            return new SimpleObjectProperty<>(panel);
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

            appWindows.createWindow(WindowConfigs.AdminExploreRecordWindow);
            try {
                appWindows.setFormObject(WindowConfigs.AdminExploreRecordWindow, currentRecord);
            } catch (IOException e) {
                e.printStackTrace();
            }
            appWindows.showWindow(WindowConfigs.AdminExploreRecordWindow);
        }
    }
}
