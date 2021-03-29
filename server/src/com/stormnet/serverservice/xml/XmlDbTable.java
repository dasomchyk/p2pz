package com.stormnet.serverservice.xml;

public enum XmlDbTable {

    Records("db/records.xml"),
    Clients("db/clients.xml"),
    Admins("db/admins.xml"),
    Masters("db/masters.xml"),
    Settings("settings/settings.xml");

    private final String xmlFilePath;


    XmlDbTable(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    public String getXmlFilePath() {
        return this.xmlFilePath;
    }
}
