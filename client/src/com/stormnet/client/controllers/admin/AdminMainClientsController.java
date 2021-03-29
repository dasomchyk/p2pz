package com.stormnet.client.controllers.admin;

import com.stormnet.client.common.AppWindows;
import com.stormnet.client.common.Controller;
import com.stormnet.client.common.WindowConfigs;
import com.stormnet.ja.Client;
import com.stormnet.clientservice.PersonService;
import com.stormnet.clientservice.factory.ServiceFactory;
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
import java.util.List;
import java.util.ResourceBundle;

public class AdminMainClientsController implements Controller<Client>, Initializable {

    @FXML
    private TableView<Client> allClientsTable;

    @FXML
    public void allRecordsMenuSelected() throws IOException {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.AdminMainClientsWindow);
        appWindows.createWindow(WindowConfigs.AdminMainRecordsWindow);
        appWindows.reloadWindow(WindowConfigs.AdminMainRecordsWindow);
        appWindows.showWindow(WindowConfigs.AdminMainRecordsWindow);

    }

    @FXML
    public void allMastersMenuSelected() throws IOException {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.AdminMainClientsWindow);
        appWindows.createWindow(WindowConfigs.AdminMainMastersWindow);
        appWindows.reloadWindow(WindowConfigs.AdminMainMastersWindow);
        appWindows.showWindow(WindowConfigs.AdminMainMastersWindow);
    }

    @FXML
    public void allClientsMenuSelected() throws IOException {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.reloadWindow(WindowConfigs.AdminMainClientsWindow);
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
        PersonService<Client> clientService = ServiceFactory.getServiceFactory().getClientService();
        List<Client> allClients = clientService.loadAll();
        ObservableList<Client> gridItems = allClientsTable.getItems();
        gridItems.clear();
        gridItems.addAll(allClients);
    }

    @Override
    public void setFormObject(Client object) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<Client, GridPane> actionsColumn = new TableColumn<>("Actions");
        actionsColumn.setMinWidth(50);
        actionsColumn.setCellValueFactory(new ButtonsCellFactory());

        allClientsTable.getColumns().add(actionsColumn);
    }

    private class ButtonsCellFactory implements Callback<TableColumn.CellDataFeatures<Client, GridPane>, ObservableValue<GridPane>> {

        @Override
        public ObservableValue<GridPane> call(TableColumn.CellDataFeatures<Client, GridPane> param) {
            Client client = param.getValue();

            Button viewBtn = new Button("view records");
            viewBtn.setOnAction(new ViewRecordEvent(client));

            GridPane panel = new GridPane();
            panel.setHgap(2);
            panel.setVgap(2);
            panel.setPadding(new Insets(2, 2, 2, 2));

            panel.add(viewBtn, 0, 0);

            return new SimpleObjectProperty<>(panel);
        }
    }

    private class ViewRecordEvent implements EventHandler<ActionEvent> {

        private Client currentClient;

        public ViewRecordEvent(Client client) {
            this.currentClient = client;
        }

        @Override
        public void handle(ActionEvent event) {
            AppWindows appWindows = AppWindows.getInstance();

            appWindows.createWindow(WindowConfigs.AdminExploreClientRecordsWindow);
            try {
                appWindows.setFormObject(WindowConfigs.AdminExploreClientRecordsWindow, currentClient);
            } catch (IOException e) {
                e.printStackTrace();
            }
            appWindows.showWindow(WindowConfigs.AdminExploreClientRecordsWindow);
            try {
                appWindows.reloadWindow(WindowConfigs.AdminExploreClientRecordsWindow);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
