package com.fullstack.romanNumeral.controller;

import com.fullstack.romanNumeral.dto.LookupTableResponse;
import com.google.common.collect.ArrayTable;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RomanNumeralController {

    @RequestMapping(value = "/lookup_table/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LookupTableResponse> getLookupTableResponse( )
    {
        LookupTableResponse res=new LookupTableResponse();
        res.setErrorReason("N/A");
        res.setStatusCode(200);
        HttpHeaders responseHeaders = new HttpHeaders();
        ResponseEntity<LookupTableResponse> result=new ResponseEntity<LookupTableResponse>(res,responseHeaders, HttpStatus.OK );
        return result;
    }
}
