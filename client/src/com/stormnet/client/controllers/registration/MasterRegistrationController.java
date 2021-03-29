package com.stormnet.client.controllers.registration;

import com.stormnet.client.common.AppWindows;
import com.stormnet.client.common.Controller;
import com.stormnet.client.common.WindowConfigs;
import com.stormnet.client.exceptions.LoginIsAlreadySetException;
import com.stormnet.ja.Master;
import com.stormnet.clientservice.PersonService;
import com.stormnet.clientservice.factory.ServiceFactory;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class MasterRegistrationController implements Controller {

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField cabinet;

    @FXML
    private PasswordField keypass;


    public void saveBtnPressed() throws IOException {
        if (firstName.getText().equals("") || lastName.getText().equals("") || login.getText().equals("") || password.getText().equals("") || cabinet.getText().equals("")) {
            throw new RuntimeException("Please, fill all fields.");
        }

        if (!keypass.getText().equals(Master.getMasterPass())) {
            throw new RuntimeException("Invalid keypass!");
        }

        PersonService<Master> masterService = ServiceFactory.getServiceFactory().getMasterService();
        List<Master> all = masterService.loadAll();
        for (Master master : all) {
            String masterLogin = master.getLogin();
            if (login.getText().equals(masterLogin)) {
                throw new LoginIsAlreadySetException();
            }
        }

        Master master = new Master();
        master.setFirstName(firstName.getText());
        master.setLastName(lastName.getText());
        master.setLogin(login.getText());
        master.setPassword(password.getText());
        master.setCabinet(cabinet.getText());

        masterService.save(master);
        AppWindows.getInstance().hideWindow(WindowConfigs.MasterRegistrationWindow);
        AppWindows.getInstance().showWindow(WindowConfigs.MasterLoginWindow);
    }

    public void cancelBtnPressed() {
        AppWindows.getInstance().hideWindow(WindowConfigs.MasterRegistrationWindow);
        AppWindows.getInstance().showWindow(WindowConfigs.MasterLoginWindow);


    }

    @Override
    public void reloadWindow() {

    }

    @Override
    public void setFormObject(Object object) {

    }
}

