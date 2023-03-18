package com.hackerrank.restcontrolleradvice;

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


import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class HelloController {


    @GetMapping("/")
    public String index() {
        System.out.println("INDEX request:  /");

        // creating a logger
        Logger logger
                = LoggerFactory.getLogger(HelloController.class);
        logger.trace("INDEX request:  /");
        logger.trace("Log level: TRACE");
        logger.info("Log level: INFO");
        logger.debug("Log level: DEBUG");
        logger.error("Log level: ERROR");
        logger.warn("Log level: WARN");
        return "Greetings from q3-demo!";

    }

    /*
    //@RequestMapping(value = "/controller_advice/{code}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/controller_advice/{code}")
    public ResponseEntity<FizzBuzzResponse> getFizzBuzzResponse(@PathVariable("code") String code)
            throws FizzException, BuzzException, FizzBuzzException {
        System.out.println("/controller_advice/{code}");
        if (FizzBuzzEnum.FIZZ.getValue().equals(code)) {
            //throw new FizzException
            System.out.println("FIZZ");
            throw new FizzException("Fizz Exception has been thrown","Internal Server Error");
        } else if (FizzBuzzEnum.BUZZ.getValue().equals(code)) {
            //throw new BuzzException
            System.out.println("BUZZ");
            throw new BuzzException("Buzz Exception has been thrown","Bad Request");
        } else if (FizzBuzzEnum.FIZZBUZZ.getValue().equals(code)) {
            //throw new FizzBuzzException
            System.out.println("FIZZBUZZ");
            throw new FizzBuzzException("FizzBuzz Exception has been thrown","Insufficient Storage");
        }
        //return FizzBuzzResponse
        ResponseMessage res=new ResponseMessage("Successfully completed fizzbuzz test","200");
        FizzBuzzResponse r=new FizzBuzzResponse("Successfully completed fizzbuzz test",200);

        ResponseEntity<FizzBuzzResponse> entity ;
        //entity.status(HttpStatus.ACCEPTED);

        //  entity.

        HttpHeaders responseHeaders = new HttpHeaders();
        entity=new ResponseEntity<FizzBuzzResponse>(r, responseHeaders, HttpStatus.OK);
        return new ResponseEntity<FizzBuzzResponse>(r, responseHeaders, HttpStatus.OK);
    }
*/
}