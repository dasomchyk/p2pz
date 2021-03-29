package com.stormnet.client.controllers.registration;

import com.stormnet.client.common.AppWindows;
import com.stormnet.client.common.Controller;
import com.stormnet.client.common.Session;
import com.stormnet.client.common.WindowConfigs;
import com.stormnet.ja.Client;
import com.stormnet.clientservice.PersonService;
import com.stormnet.clientservice.factory.ServiceFactory;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ClientLoginController implements Controller {

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;


    public void SignInBtnPressed() throws IOException {

        AppWindows appWindows = AppWindows.getInstance();
        PersonService<Client> clientService = ServiceFactory.getServiceFactory().getClientService();

        if (clientService.loadPersonByLoginAndPassword(login.getText(), password.getText()) == null) {
            appWindows.createWindow(WindowConfigs.WrongLoginPasswordWindow);
            appWindows.showWindow(WindowConfigs.WrongLoginPasswordWindow);
        } else {
            Session newSession = Session.getInstance();
            int currentSessionId = clientService.loadPersonByLoginAndPassword(login.getText(), password.getText()).getId();
            newSession.setPersonId(currentSessionId);
            appWindows.hideWindow(WindowConfigs.ClientLoginWindow);
            appWindows.createWindow(WindowConfigs.ClientMainWindow);
            appWindows.reloadWindow(WindowConfigs.ClientMainWindow);
            appWindows.showWindow(WindowConfigs.ClientMainWindow);
        }
    }

    public void RegistrationBtnPressed() {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.ClientLoginWindow);
        appWindows.createWindow(WindowConfigs.ClientRegistrationWindow);
        appWindows.showWindow(WindowConfigs.ClientRegistrationWindow);
    }

    public void ExitBtnPressed() {
        System.exit(0);
    }


    @Override
    public void reloadWindow() {

    }

    @Override
    public void setFormObject(Object object) {

    }
}

