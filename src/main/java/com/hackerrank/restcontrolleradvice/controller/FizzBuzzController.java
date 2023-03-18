package com.hackerrank.restcontrolleradvice.controller;

import com.hackerrank.restcontrolleradvice.dto.BuzzException;
import com.hackerrank.restcontrolleradvice.dto.FizzBuzzException;
import com.hackerrank.restcontrolleradvice.dto.FizzBuzzResponse;
import com.hackerrank.restcontrolleradvice.dto.FizzException;
import com.hackerrank.restcontrolleradvice.dto.ResponseMessage;
import com.hackerrank.restcontrolleradvice.enums.FizzBuzzEnum;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FizzBuzzController {
  @RequestMapping(value = "/controller_advice/{code}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<FizzBuzzResponse> getFizzBuzzResponse(@PathVariable("code") String code)
          throws FizzException, BuzzException, FizzBuzzException {
    ResponseEntity<FizzBuzzResponse> result ;
    HttpHeaders responseHeaders = new HttpHeaders();
  
    if (FizzBuzzEnum.FIZZ.getValue().equals(code)) {

      
       FizzBuzzResponse e=new FizzBuzzResponse("Fizz Exception has been thrown",
               500,
               "Internal Server Error");

       result=new ResponseEntity< FizzBuzzResponse>(e, responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
       return result;

    } else if (FizzBuzzEnum.BUZZ.getValue().equals(code)) {

        FizzBuzzResponse e=new FizzBuzzResponse("Buzz Exception has been thrown",
                400,
                "Bad Request");

        result=new ResponseEntity< FizzBuzzResponse>(e, responseHeaders, HttpStatus.BAD_REQUEST);
        return result;
    } else if (FizzBuzzEnum.FIZZBUZZ.getValue().equals(code)) {

        FizzBuzzResponse e=new FizzBuzzResponse("FizzBuzz Exception has been thrown",
                507,
                "Insufficient Storage");

        result=new ResponseEntity< FizzBuzzResponse>(e, responseHeaders, HttpStatus.INSUFFICIENT_STORAGE);
        return result;
    }

    FizzBuzzResponse r=new FizzBuzzResponse("Successfully completed fizzbuzz test",200, null);

    result=new ResponseEntity<FizzBuzzResponse>(r, responseHeaders, HttpStatus.OK);
    return result;
  }
}
