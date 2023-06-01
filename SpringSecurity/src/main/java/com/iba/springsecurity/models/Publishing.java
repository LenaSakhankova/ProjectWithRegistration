package com.iba.springsecurity.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Publishing {
    private int Idpublishing;

    @NotEmpty(message = "Address should not be empty")
    private String adress;

    @Size(min = 7, message = "Number should be more than 7 symbols")
    private String phone;

    public Publishing(){};

    public Publishing(String adress, String phone) {
        this.adress = adress;
        this.phone = phone;
    }

    public int getIdpublishing() {
        return Idpublishing;
    }

    public void setIdpublishing(int idpublishing) {
        Idpublishing = idpublishing;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
