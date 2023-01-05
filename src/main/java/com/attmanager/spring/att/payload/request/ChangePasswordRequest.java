package com.attmanager.spring.att.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ChangePasswordRequest {
    @NotBlank
    @Size(min = 6, max = 40)
    private String old_password;

    @NotBlank
    @Size(min = 6, max = 40)
    private String new_password;

    @NotBlank
    @Size(min = 6, max = 40)
    private String again_newpassword;
    

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getAgain_newpassword() {
        return again_newpassword;
    }

    public void setAgain_newpassword(String again_newpassword) {
        this.again_newpassword = again_newpassword;
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }    
}
