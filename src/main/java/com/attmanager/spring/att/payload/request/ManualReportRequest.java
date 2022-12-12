package com.attmanager.spring.att.payload.request;

import java.time.LocalDate;

public class ManualReportRequest {
    private LocalDate fromDate;
    
    private LocalDate toDate;

    public LocalDate getFromDate() {
        return fromDate;
    }
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }
    public LocalDate getToDate() {
        return toDate;
    }
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    
}
