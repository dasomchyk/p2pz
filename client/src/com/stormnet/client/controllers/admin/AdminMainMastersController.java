package com.stormnet.client.controllers.admin;

import com.stormnet.client.common.AppWindows;
import com.stormnet.client.common.Controller;
import com.stormnet.client.common.WindowConfigs;
import com.stormnet.ja.Master;
import com.stormnet.ja.Record;
import com.stormnet.clientservice.PersonService;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminMainMastersController implements Controller<Master>, Initializable {

    @FXML
    private TableView<Master> allMastersTable;

    @FXML
    public void allRecordsMenuSelected() throws IOException {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.AdminMainMastersWindow);
        appWindows.createWindow(WindowConfigs.AdminMainRecordsWindow);
        appWindows.reloadWindow(WindowConfigs.AdminMainRecordsWindow);
        appWindows.showWindow(WindowConfigs.AdminMainRecordsWindow);

    }

    @FXML
    public void allMastersMenuSelected() throws IOException {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.reloadWindow(WindowConfigs.AdminMainMastersWindow);
    }

    @FXML
    public void allClientsMenuSelected() throws IOException {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.AdminMainMastersWindow);
        appWindows.createWindow(WindowConfigs.AdminMainClientsWindow);
        appWindows.reloadWindow(WindowConfigs.AdminMainClientsWindow);
        appWindows.showWindow(WindowConfigs.AdminMainClientsWindow);
    }

    @FXML
    public void personalCabinetBtnPressed() throws IOException {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.createWindow(WindowConfigs.AdminCabinetWindow);
        appWindows.showWindow(WindowConfigs.AdminCabinetWindow);
        appWindows.reloadWindow(WindowConfigs.AdminCabinetWindow);
    }

    @FXML
    public void exitBtnPressed() {
        System.exit(0);
    }


    @Override
    public void reloadWindow() throws IOException {
        PersonService<Master> masterService = ServiceFactory.getServiceFactory().getMasterService();
        List<Master> allMasters = masterService.loadAll();
        ObservableList<Master> gridItems = allMastersTable.getItems();
        gridItems.clear();
        gridItems.addAll(allMasters);
    }

    @Override
    public void setFormObject(Master object) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<Master, String> ratingColumn = new TableColumn<>("Rating");
        ratingColumn.setMinWidth(100);
        ratingColumn.setCellValueFactory(new RatingStringFactory());

        allMastersTable.getColumns().add(ratingColumn);

        TableColumn<Master, GridPane> actionsColumn = new TableColumn<>("Actions");
        actionsColumn.setMinWidth(50);
        actionsColumn.setCellValueFactory(new ButtonsCellFactory());

        allMastersTable.getColumns().add(actionsColumn);
    }

    private class RatingStringFactory implements Callback<TableColumn.CellDataFeatures<Master, String>, ObservableValue<String>> {

        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Master, String> param) {
            RecordService recordService = ServiceFactory.getServiceFactory().getRecordService();
            List<Record> allRecords = new ArrayList<>();
            try {
                allRecords = recordService.loadAll();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int totalRateSum = 0;
            int marksCount = 0;
            double myRate = 0;
            for (Record record : allRecords) {
                if (!record.getRating().equals("") && record.getMasterId().equals(param.getValue().getId())) {
                    totalRateSum += Integer.valueOf(record.getRating());
                    marksCount++;
                }
            }

            if (marksCount != 0) {
                myRate = (double) totalRateSum / marksCount;
            }

            DecimalFormat ratingAccuracy = new DecimalFormat("#.00");
            String rating = String.valueOf(ratingAccuracy.format(myRate));

            return new ReadOnlyStringWrapper(rating);
        }
    }

    private class ButtonsCellFactory implements Callback<TableColumn.CellDataFeatures<Master, GridPane>, ObservableValue<GridPane>> {

        @Override
        public ObservableValue<GridPane> call(TableColumn.CellDataFeatures<Master, GridPane> param) {
            Master master = param.getValue();

            Button viewBtn = new Button("view records");
            viewBtn.setOnAction(new ViewRecordEvent(master));

            GridPane panel = new GridPane();
            panel.setHgap(2);
            panel.setVgap(2);
            panel.setPadding(new Insets(2, 2, 2, 2));

            panel.add(viewBtn, 0, 0);

            return new SimpleObjectProperty<>(panel);
        }
    }

    private class ViewRecordEvent implements EventHandler<ActionEvent> {

        private Master currentMaster;

        public ViewRecordEvent(Master master) {
            this.currentMaster = master;
        }

        @Override
        public void handle(ActionEvent event) {
            AppWindows appWindows = AppWindows.getInstance();

            appWindows.createWindow(WindowConfigs.AdminExploreMasterRecordsWindow);
            try {
                appWindows.setFormObject(WindowConfigs.AdminExploreMasterRecordsWindow, currentMaster);
            } catch (IOException e) {
                e.printStackTrace();
            }
            appWindows.showWindow(WindowConfigs.AdminExploreMasterRecordsWindow);
        }
    }
}
