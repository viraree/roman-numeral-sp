package com.fullstack.romanNumerals.controller;

import com.fullstack.romanNumerals.dto.LookupTableResponse;
import com.fullstack.romanNumerals.dto.BuzzException;
import com.fullstack.romanNumerals.dto.FizzBuzzException;
import com.fullstack.romanNumerals.dto.FizzBuzzResponse;
import com.fullstack.romanNumerals.dto.FizzException;
import com.fullstack.romanNumerals.enums.FizzBuzzEnum;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class BaseController {


    @RequestMapping(value = "/lookup_table/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LookupTableResponse> getLookupTableResponse( ) throws IOException {
        LookupTableResponse res=new LookupTableResponse();
        res.setErrorReason("N/A");
        res.setStatusCode(200);
        HttpHeaders responseHeaders = new HttpHeaders();
        ResponseEntity<LookupTableResponse> result=new ResponseEntity<LookupTableResponse>(res,responseHeaders, HttpStatus.OK );
        return result;
    }


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
