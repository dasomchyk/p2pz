package com.stormnet.client.controllers.registration;

import com.stormnet.client.common.AppWindows;
import com.stormnet.client.common.Controller;
import com.stormnet.client.common.Session;
import com.stormnet.client.common.WindowConfigs;
import com.stormnet.ja.Master;
import com.stormnet.clientservice.PersonService;
import com.stormnet.clientservice.factory.ServiceFactory;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MasterLoginController implements Controller {

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    public void SignInBtnPressed() throws IOException {

        AppWindows appWindows = AppWindows.getInstance();
        PersonService<Master> masterService = ServiceFactory.getServiceFactory().getMasterService();

        if (masterService.loadPersonByLoginAndPassword(login.getText(), password.getText()) == null) {
            appWindows.createWindow(WindowConfigs.WrongLoginPasswordWindow);
            appWindows.showWindow(WindowConfigs.WrongLoginPasswordWindow);
        } else {
            Session newSession = Session.getInstance();
            int currentSessionId = masterService.loadPersonByLoginAndPassword(login.getText(), password.getText()).getId();
            newSession.setPersonId(currentSessionId);
            appWindows.hideWindow(WindowConfigs.MasterLoginWindow);
            appWindows.createWindow(WindowConfigs.MasterMainWindow);
            appWindows.reloadWindow(WindowConfigs.MasterMainWindow);
            appWindows.showWindow(WindowConfigs.MasterMainWindow);
        }
    }

    public void RegistrationBtnPressed() {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.MasterLoginWindow);
        appWindows.createWindow(WindowConfigs.MasterRegistrationWindow);
        appWindows.showWindow(WindowConfigs.MasterRegistrationWindow);
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
