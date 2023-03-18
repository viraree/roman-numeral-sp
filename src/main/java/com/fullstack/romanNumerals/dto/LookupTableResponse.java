package com.fullstack.romanNumerals.dto;


import com.google.common.collect.*;



import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.IOException;
import java.util.List;




class TableWrapper{
    private ArrayTable<String, String, String> table;

    public TableWrapper() {
        this.setTable(ArrayTable.create(
                List.of("1-Digit", "2-Digit"),
                List.of("1 Eq.", "2 Eq.", "3 Eq.")
        ));

        this.getTable().put("1-Digit","1 Eq.","I");
        this.getTable().put("1-Digit","2 Eq.","II");
        this.getTable().put("1-Digit","3 Eq.","III");
    }

    public ArrayTable<String, String, String> getTable() {
        return table;
    }

    public void setTable(ArrayTable<String, String, String> table) {
        this.table = table;
    }
}


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




    static class NavItem {

        public NavItem() {
        }

        private String key;
        private String url;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }


    public LookupTableResponse() throws IOException {
        this.lookupTable= ArrayTable.create(
                List.of("1-Digit", "2-Digit"),
                List.of("1 Eq.", "2 Eq.", "3 Eq.")
        );

        this.lookupTable.put("1-Digit","1 Eq.","I");
        this.lookupTable.put("1-Digit","2 Eq.","II");
        this.lookupTable.put("1-Digit","3 Eq.","III");


        this.initLookupMap();
       // this.generateFromJson();

        this.message="at 0,0 : "+this.lookupTable.at(0,0);
    }

    public  Multimap<String, NavItem> generateFromJson() throws JsonParseException, JsonMappingException,
            JsonProcessingException, IOException {
        Multimap<String, NavItem> navs = null;
        try {
            String jsonString = "{\n \"123455\":[\n {\n \"key\":\"Java Exercises\",\n \"url\":\"www.leveluplunch.com/java/exercises/\"\n },\n {\n \"key\":\"Java Examples\",\n \"url\":\"www.leveluplunch.com/java/examples/\"\n }\n ],\n \"999999\":[\n {\n \"key\":\"Java Tutorials\",\n \"url\":\"www.leveluplunch.com/java/tutorials/\"\n },\n {\n \"key\":\"Java Examples\",\n \"url\":\"www.leveluplunch.com/java/examples/\"\n }\n ]\n}";


            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new GuavaModule());

            navs = objectMapper.readValue(
                    objectMapper.treeAsTokens(objectMapper.readTree(jsonString)),
                    objectMapper.getTypeFactory().constructMapLikeType(
                            Multimap.class, String.class, NavItem.class));
        }
        catch (IOException e)
        {
            this.message=e.getMessage();
        }
        return navs;
    }


    public Multimap<Integer, String[]>  initLookupMap() throws IOException {

        Multimap<Integer, String[]>  result=null;


        try {

            Multimap<Integer, String[]> map = ArrayListMultimap.create();
            String[] eqArray={"I","II"};
            map.put(1, eqArray);



            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new GuavaModule());
            //String jsonString = "{\"table\": {\"1-Digit\":{\"1 Eq.\":\"I\",\"2 Eq.\":\"II\",\"3 Eq.\":\"III\"},\"2-Digit\":{\"1 Eq.\":null,\"2 Eq.\":null,\"3 Eq.\":null}}}";
            String jsonString =objectMapper.writeValueAsString(map);

            result = objectMapper.readValue(jsonString,
                    objectMapper.getTypeFactory().constructMapLikeType(
                            Multimap.class, Integer.class, String[].class)
                    );
        }

        catch (IOException e)
        {
           this.message=e.getMessage();
        }
        //Gson gson = new Gson();





        return result;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
