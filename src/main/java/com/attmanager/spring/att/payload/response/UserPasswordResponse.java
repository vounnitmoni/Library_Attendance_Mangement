package com.attmanager.spring.att.payload.response;

public class UserPasswordResponse {
    private String passString;

    public UserPasswordResponse(String passString) {
        this.passString = passString;
    }

    public String getPassString() {
        return passString;
    }

    public void setPassString(String passString) {
        this.passString = passString;
    }
    
}
