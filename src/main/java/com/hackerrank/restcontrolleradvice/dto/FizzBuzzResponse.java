package com.hackerrank.restcontrolleradvice.dto;

public class FizzBuzzResponse {
  private String message;
  private int statusCode;

  private String errorReason;

  public FizzBuzzResponse(String message, int statusCode, String TheErrorReason) {
    this.message = message;
    this.statusCode = statusCode;
    this.errorReason=TheErrorReason;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
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
}
