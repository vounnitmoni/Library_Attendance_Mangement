package com.attmanager.spring.att.payload.response;

import java.util.List;

public class InfoByYearResponse {
    private String year;
    private Integer quantity;
    private List<StudentJoinLibraryByYear> objects;
    
    public InfoByYearResponse(List<StudentJoinLibraryByYear> objects) {
        this.objects = objects;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public List<StudentJoinLibraryByYear> getObjects() {
        return objects;
    }
    public void setObjects(List<StudentJoinLibraryByYear> objects) {
        this.objects = objects;
    }
    
    
}
