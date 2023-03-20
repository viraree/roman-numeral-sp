package com.fullstack.romanNumerals.dto;

public class lookupEntry {
    private int rowIndex;
    private String[] rowContent;

    public lookupEntry()
    {
        // setRowContent(new String[10]);
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String[] getRowContent() {
        return rowContent;
    }

    public void setRowContent(String[] rowContent) {
        this.rowContent = rowContent;
    }
}
