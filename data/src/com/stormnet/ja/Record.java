package com.stormnet.ja;

import java.time.LocalDate;

public class Record extends Id {
    private LocalDate date;
    private String time;
    private Integer clientId;
    private Integer masterId;
    private Boolean isFinished;
    private String clientFirstName;
    private String clientLastName;
    private String masterFirstName;
    private String masterLastName;
    private String cabinet;;
    private String rating;

    public Record() {
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

    public Integer getClientId() {
        return this.clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getMasterId() {
        return this.masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public Boolean getFinished() {
        return this.isFinished;
    }

    public void setFinished(Boolean finished) {
        this.isFinished = finished;
    }

    public String getClientFirstName() {
        return this.clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return this.clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getMasterFirstName() {
        return this.masterFirstName;
    }

    public void setMasterFirstName(String doctorFirstName) {
        this.masterFirstName = masterFirstName;
    }

    public String getMasterLastName() {
        return this.masterLastName;
    }

    public void setMasterLastName(String masterLastName) {
        this.masterLastName = masterLastName;
    }

    public String getCabinet() {
        return this.cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
