package com.fullstack.romanNumerals.dto;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.*;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

import java.io.IOException;
import java.util.*;


public class LookupTableResponse {
    private ArrayTable<String, String, String> lookupArrTbl;
    private Hashtable<Integer, String[]> lookupTable;

    private ArrayList<lookupEntry> lookupEntrySet;

    private String[] indicators= {"VI", "XL", "CD","M\\"};

    private String message;

    private int statusCode;
    private String errorReason;


    public ArrayTable<String, String, String> getLookupArrTbl() {
        return lookupArrTbl;
    }

    public void setLookupArrTbl(ArrayTable<String, String, String> lookupArrTbl) {
        this.lookupArrTbl = lookupArrTbl;
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

    public LookupTableResponse() throws IOException {

        this.message="ok";
        this.errorReason="N/A";
        this.lookupTable=new Hashtable<Integer, String[]>();
        this.setLookupEntrySet(new ArrayList<lookupEntry>());
    }

    public void createLookupArrTbl() {
        this.lookupArrTbl = ArrayTable.create(
                Arrays.asList("1-Digit", "2-Digit"),
                Arrays.asList("1 Eq.", "2 Eq.", "3 Eq.")
        );

        this.lookupArrTbl.put("1-Digit","1 Eq.","I");
        this.lookupArrTbl.put("1-Digit","2 Eq.","II");
        this.lookupArrTbl.put("1-Digit","3 Eq.","III");

        this.message="at 0,0 : "+this.lookupArrTbl.at(0,0);
    }


    public Hashtable<Integer, String[]> initLookupMap() throws IOException {

        Hashtable<Integer, String[]>  result=null;


        try {
            Hashtable<Integer, String[]> map = new Hashtable<Integer, String[]>() {
            };
            String[] eqArray={"I","II"};
            map.put(1, eqArray);

            String jsonString = "{\"1\": [\"\", \"I\", \"II\", \"III\", \"IV\", \"V\", \"VI\", \"VII\", \"VIII\", \"IX\"]," +
                                "\"2\": [\"\", \"X\", \"XX\", \"XXX\", \"XL\", \"L\", \"LX\", \"LXX\", \"LXXX\", \"XC\"]," +
                                "\"3\": [\"\", \"C\", \"CC\", \"CCC\", \"CD\", \"D\", \"DC\", \"DCC\", \"DCCC\", \"CM\"]," +
                                " \"4\": [\"\", \"M\", \"MM\", \"MMM\"]"+
                                "}";
            result=buildLookupTable(jsonString);
            this.lookupTable=result;
        }

        catch (IOException e)
        {
           this.message=e.getMessage();
        }
        //Gson gson = new Gson();
        return result;
    }

    private Hashtable<Integer, String[]> buildLookupTable(String jsonString) throws JsonProcessingException {
        Hashtable<Integer, String[]> result=new Hashtable<Integer, String[]>();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new GuavaModule());

        result = objectMapper.readValue(jsonString,
                objectMapper.getTypeFactory().constructMapLikeType(
                        Hashtable.class, Integer.class, String[].class)
                );


        return result;
    }

    public ArrayList<lookupEntry> buildLookupEntrySet()  {
        ArrayList<lookupEntry> result=new ArrayList<lookupEntry>();
        lookupEntry entry1=new lookupEntry();
        String [] rowContent1={"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        entry1.setRowContent(rowContent1);
        entry1.setRowIndex(1);
        result.add(entry1);

        lookupEntry entry2=new lookupEntry();
        String [] rowContent2={"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        entry2.setRowContent(rowContent2);
        entry2.setRowIndex(2);
        result.add(entry2);

        lookupEntry entry3=new lookupEntry();
        String [] rowContent3={"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        entry3.setRowContent(rowContent3);
        entry3.setRowIndex(3);
        result.add(entry3);

        lookupEntry entry4=new lookupEntry();
        String [] rowContent4={"", "M", "MM", "MMM"};
        entry4.setRowContent(rowContent4);
        entry4.setRowIndex(4);
        result.add(entry4);

        this.setLookupEntrySet(result);
        return result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Hashtable<Integer, String[]> getLookupTable() {
        return lookupTable;
    }

    public void setLookupTable(Hashtable<Integer, String[]> lookupTable) {
        this.lookupTable = lookupTable;
    }

    public String[] getIndicators() {
        return indicators;
    }

    public void setIndicators(String[] indicators) {
        this.indicators = indicators;
    }

    public ArrayList<lookupEntry> getLookupEntrySet() {
        return lookupEntrySet;
    }

    public void setLookupEntrySet(ArrayList<lookupEntry> lookupEntrySet) {
        this.lookupEntrySet = lookupEntrySet;
    }
}
