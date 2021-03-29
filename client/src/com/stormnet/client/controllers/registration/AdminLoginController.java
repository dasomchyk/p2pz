package com.stormnet.client.controllers.registration;

import com.stormnet.client.common.AppWindows;
import com.stormnet.client.common.Controller;
import com.stormnet.client.common.Session;
import com.stormnet.client.common.WindowConfigs;
import com.stormnet.ja.Admin;
import com.stormnet.clientservice.PersonService;
import com.stormnet.clientservice.factory.ServiceFactory;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminLoginController implements Controller {

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    public void SignInBtnPressed() throws IOException {

        AppWindows appWindows = AppWindows.getInstance();
        PersonService<Admin> adminService = ServiceFactory.getServiceFactory().getAdminService();

        if (adminService.loadPersonByLoginAndPassword(login.getText(), password.getText()) == null) {
            appWindows.createWindow(WindowConfigs.WrongLoginPasswordWindow);
            appWindows.showWindow(WindowConfigs.WrongLoginPasswordWindow);
        } else {
            Session newSession = Session.getInstance();
            int currentSessionId = adminService.loadPersonByLoginAndPassword(login.getText(), password.getText()).getId();
            newSession.setPersonId(currentSessionId);
            appWindows.hideWindow(WindowConfigs.AdminLoginWindow);
            appWindows.createWindow(WindowConfigs.AdminMainRecordsWindow);
            appWindows.showWindow(WindowConfigs.AdminMainRecordsWindow);
            appWindows.reloadWindow(WindowConfigs.AdminMainRecordsWindow);
        }
    }

    @FXML
    public void RegistrationBtnPressed() {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.AdminLoginWindow);
        appWindows.createWindow(WindowConfigs.AdminRegistrationWindow);
        appWindows.showWindow(WindowConfigs.AdminRegistrationWindow);
    }

    @FXML
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

