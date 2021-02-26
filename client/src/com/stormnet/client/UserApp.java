package com.stormnet.client;

import com.stormnet.client.common.AppWindows;
import com.stormnet.client.common.WindowConfigs;
import javafx.stage.Stage;

public class UserApp extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.addMainStage(primaryStage);
        appWindows.showWindow(WindowConfigs.InvitationWindow);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

