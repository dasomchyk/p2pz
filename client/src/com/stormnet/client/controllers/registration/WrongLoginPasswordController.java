package com.stormnet.client.controllers.registration;

import com.stormnet.client.common.AppWindows;
import com.stormnet.client.common.Controller;
import com.stormnet.client.common.WindowConfigs;

public class WrongLoginPasswordController implements Controller {


    public void canselBtnPressed() {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.WrongLoginPasswordWindow);
    }

    @Override
    public void reloadWindow() {

    }

    @Override
    public void setFormObject(Object object) {

    }
}
