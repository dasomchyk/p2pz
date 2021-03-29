package com.stormnet.ja;

public abstract class Person extends Id {
    private String firstName;
    private String lastName;
    private String password;
    private String login;

    public Person() {
    }

    public String getFirstName() {

        return this.firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return this.lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getPassword() {

        return this.password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getLogin() {

        return this.login;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    public int hashCode() {

        return this.getId();
    }
}
