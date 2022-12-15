package com.attmanager.spring.att.payload.response;

import java.math.BigInteger;

public class ReportByDepAndYear {
    private String depyear;
    private BigInteger quantity;
    public ReportByDepAndYear(String depyear, BigInteger quantity) {
        this.depyear = depyear;
        this.quantity = quantity;
    }
    public String getDepyear() {
        return depyear;
    }
    public void setDepyear(String depyear) {
        this.depyear = depyear;
    }
    public BigInteger getQuantity() {
        return quantity;
    }
    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }
    
}
