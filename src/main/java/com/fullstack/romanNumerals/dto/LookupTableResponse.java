package com.fullstack.romanNumerals.dto;

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
                List.of("1-Digit", "2-Digit"),
                List.of("1 Eq.", "2 Eq.", "3 Eq.")
        );

        this.lookupTable.put("1-Digit","1 Eq.","I");
        this.lookupTable.put("1-Digit","2 Eq.","II");
        this.lookupTable.put("1-Digit","3 Eq.","III");

        this.message="at 0,0 : "+this.lookupTable.at(0,0);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
