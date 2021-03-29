package com.stormnet.ja;

public abstract class Id {

    private Integer id;

    public Id() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public abstract void setId();

    public abstract void setClientId();

    public abstract void setMasterId();

    public abstract void setClientFirstName();

    public abstract void setClientLastName();

    public abstract void setMasterFirstName();

    public abstract void setMasterLastName();
}
