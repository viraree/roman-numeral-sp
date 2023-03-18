package com.fullstack.romanNumeral.dto;

import com.google.common.collect.ArrayTable;

import java.util.List;

public class LookupTableResponse {
    private ArrayTable<String, String, String> lookupTable;

    private String message;

    private int statusCode;
    private String errorReason;


    public ArrayTable<String, String, String> getLookupTable() {
        return lookupTable;
    }

    public void setLookupTable(ArrayTable<String, String, String> lookupTable) {
        this.lookupTable = lookupTable;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }

    public LookupTableResponse()
    {
        this.lookupTable= ArrayTable.create(
                List.of("Company A", "Company B"),
                List.of("Q1", "Q2")
        );

        this.message="at 0,0 : "+this.lookupTable.at(0,0);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
