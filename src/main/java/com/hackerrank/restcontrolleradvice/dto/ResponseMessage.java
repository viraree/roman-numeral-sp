package com.hackerrank.restcontrolleradvice.dto;

public class ResponseMessage {
    private String     message;
    private String     errorReason;


    public ResponseMessage()
    {

    }

    public ResponseMessage(String msg, String sts)
    {
        
    }

    public String getMessage() {
      return message;
    }
  
    public String getErrorReason() {
      return errorReason;
    }
}
