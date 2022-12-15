package com.attmanager.spring.att.payload.response;

import java.math.BigInteger;

public class ReportByDep {
    private String department;
    private BigInteger quantity;
    public ReportByDep(String department, BigInteger quantity) {
        this.department = department;
        this.quantity = quantity;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public BigInteger getQuantity() {
        return quantity;
    }
    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }
    
}
