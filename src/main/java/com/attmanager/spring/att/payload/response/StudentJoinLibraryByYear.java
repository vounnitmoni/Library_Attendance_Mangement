package com.attmanager.spring.att.payload.response;

import java.math.BigInteger;

public class StudentJoinLibraryByYear {
    private String year;
    private BigInteger quantity;
    public StudentJoinLibraryByYear(String year, BigInteger quantity) {
        this.year = year;
        this.quantity = quantity;
    }
    
    public StudentJoinLibraryByYear() {
    }

    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public BigInteger getQuantity() {
        return quantity;
    }
    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }
}
