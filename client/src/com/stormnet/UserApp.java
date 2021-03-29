package com.stormnet;

import com.stormnet.client.common.AppWindows;
import com.stormnet.client.common.WindowConfigs;
import javafx.application.Application;
import javafx.stage.Stage;

public class UserApp extends Application {

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

