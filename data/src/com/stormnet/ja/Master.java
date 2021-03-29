package com.stormnet.ja;

public class Master extends Person {
    private String cabinet;

    public Master() {
    }

    @Override
    public void setId() {

    }

    @Override
    public void setClientId() {

    }

    @Override
    public void setMasterId() {

    }

    @Override
    public void setClientFirstName() {

    }

    @Override
    public void setClientLastName() {

    }

    @Override
    public void setMasterFirstName() {

    }

    @Override
    public void setMasterLastName() {

    }


    public static String getMasterPass() {

        return "master";
    }

    public String getCabinet() {

        return this.cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }
}
