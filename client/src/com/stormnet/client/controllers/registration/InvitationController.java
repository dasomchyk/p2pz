package com.stormnet.client.controllers.registration;

import com.stormnet.client.common.AppWindows;
import com.stormnet.client.common.Controller;
import com.stormnet.client.common.WindowConfigs;

public class InvitationController implements Controller {


    public void clientBtnPressed() {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.InvitationWindow);
        appWindows.createWindow(WindowConfigs.ClientLoginWindow);
        appWindows.showWindow(WindowConfigs.ClientLoginWindow);
    }

    public void masterBtnPressed() {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.InvitationWindow);
        appWindows.createWindow(WindowConfigs.MasterLoginWindow);
        appWindows.showWindow(WindowConfigs.MasterLoginWindow);
    }

    public void adminBtnPressed() {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.InvitationWindow);
        appWindows.createWindow(WindowConfigs.AdminLoginWindow);
        appWindows.showWindow(WindowConfigs.AdminLoginWindow);
    }

    @Override
    public void reloadWindow() {

    }

    @Override
    public void setFormObject(Object object) {

    }
}

