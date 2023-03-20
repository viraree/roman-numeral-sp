package com.fullstack.romanNumerals.dto;

public class ResponseMessage {
    private String     message;
    private String     errorReason;


    public ResponseMessage()
    {
       this.errorReason="N/A";
       this.message="ok";
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

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }
}
