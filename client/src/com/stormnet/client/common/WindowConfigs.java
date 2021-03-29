package com.stormnet.client.common;

public enum WindowConfigs {

    AdminCabinetWindow("/com/stormnet/client/xmlui/admin/admin-cabinet.fxml",
            "Admin Cabinet",
            true,
            700,
            275),

    AdminExploreClientRecordsWindow("/com/stormnet/client/xmlui/admin/admin-explore-client-records-window.fxml",
            "Records Of This Client Window",
            true,
            700,
            500),

    AdminExploreMasterRecordsWindow("/com/stormnet/client/xmlui/admin/admin-explore-master-records-window.fxml",
            "Records Of This Master Window",
            true,
            600,
            500),

    AdminExploreRecordWindow("/com/stormnet/client/xmlui/admin/admin-explore-record-window.fxml",
            "Record Window",
            true,
            630,
            500),

    AdminMainRecordsWindow("/com/stormnet/client/xmlui/admin/admin-main-records.fxml",
            "Admin Main Window",
            true,
            700,
            300),

    AdminMainClientsWindow("/com/stormnet/client/xmlui/admin/admin-main-clients.fxml",
            "Admin Main Window",
            true,
            430,
            300),

    AdminMainMastersWindow("/com/stormnet/client/xmlui/admin/admin-main-masters.fxml",
            "Admin Main Window",
            true,
            580,
            275),

    ClientCabinetWindow("/com/stormnet/client/xmlui/client/client-cabinet.fxml",
            "Client Cabinet",
            true,
            300,
            275),

    ClientEditRecordWindow("/com/stormnet/client/xmlui/client/client-edit-record.fxml",
            "Edit Record",
            true,
            270,
            275),

    ClientExploreRecordWindow("/com/stormnet/client/xmlui/client/client-explore-record-window.fxml",
            "Record Window",
            true,
            630,
            500),

    ClientGetRecordWindow("/com/stormnet/client/xmlui/client/client-get-record.fxml",
            "Get Record",
            true,
            270,
            275),

    ClientMainWindow("/com/stormnet/client/xmlui/client/client-main.fxml",
            "Client Main Window",
            true,
            700,
            300),

    MasterCabinetWindow("/com/stormnet/client/xmlui/master/master-cabinet.fxml",
            "Master Cabinet",
            true,
            260,
            300),

    MasterExploreRecordWindow("/com/stormnet/client/xmlui/master/master-explore-record-window.fxml",
            "Record Window",
            true,
            630,
            500),

    MasterMainWindow("/com/stormnet/client/xmlui/master/master-main.fxml",
            "Master Main Window",
            true,
            600,
            275),

    AdminLoginWindow("/com/stormnet/client/xmlui/registration/admin-login-window.fxml",
            "Sign in",
            true,
            300,
            275),

    AdminRegistrationWindow("/com/stormnet/client/xmlui/registration/admin-registration-window.fxml",
            "Registration",
            true,
            300,
            275),

    ClientLoginWindow("/com/stormnet/client/xmlui/registration/client-login-window.fxml",
            "Sign in",
            true,
            300,
            275),

    ClientRegistrationWindow("/com/stormnet/client/xmlui/registration/client-registration-window.fxml",
            "Registration",
            true,
            300,
            275),

    MasterLoginWindow("/com/stormnet/client/xmlui/registration/master-login-window.fxml",
            "Sign in",
            true,
            300,
            275),

    MasterRegistrationWindow("/com/stormnet/client/xmlui/registration/master-registration-window.fxml",
            "Registration",
            true,
            300,
            275),

    InvitationWindow("/com/stormnet/client/xmlui/registration/invitation-window.fxml",
            "Invitation Window",
            false,
            300,
            100),

    WrongLoginPasswordWindow("/com/stormnet/client/xmlui/registration/wrong-login-password.fxml",
            "Authorization Failed!",
            true,
            300,
            100);

    private String xmlUi;

    private String title;

    private boolean modalWindow;

    private int width;

    private int height;

    WindowConfigs(String xmlUi, String title, boolean modalWindow, int width, int height) {
        this.xmlUi = xmlUi;
        this.title = title;
        this.modalWindow = modalWindow;
        this.width = width;
        this.height = height;
    }

    public String getXmlUi() {
        return xmlUi;
    }

    public String getTitle() {
        return title;
    }

    public boolean isModalWindow() {
        return modalWindow;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}


