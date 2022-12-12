package com.attmanager.spring.att.payload.request;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotBlank;

public class JoinLibraryRequest {
    @NotBlank
    private String sid;
    private LocalDate joinDate;
    private LocalTime joinTime;
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public LocalTime getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(LocalTime joinTime) {
        this.joinTime = joinTime;
    }
    
}
