package com.fullstack.romanNumerals.controller;


import com.fullstack.romanNumerals.dto.*;

import com.google.common.collect.Iterables;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@RestController
public class ServiceController {


    @RequestMapping(value = "/arr_tbl_lookup/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LookupTableResponse> getLookupTableResponse( ) throws IOException {
        LookupTableResponse res=new LookupTableResponse();
        res.createLookupArrTbl();
        res.setErrorReason("N/A");
        res.setStatusCode(200);
        HttpHeaders responseHeaders = new HttpHeaders();
        ResponseEntity<LookupTableResponse> result=new ResponseEntity<LookupTableResponse>(res,responseHeaders, HttpStatus.OK );
        return result;
    }


    @RequestMapping(value = "/hash_lookup/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LookupTableResponse> initLookup( ) throws IOException {
        LookupTableResponse res=new LookupTableResponse();
        res.initLookupMap();
        res.setErrorReason("N/A");
        res.setStatusCode(200);
        HttpHeaders responseHeaders = new HttpHeaders();
        ResponseEntity<LookupTableResponse> result=new ResponseEntity<LookupTableResponse>(res,responseHeaders, HttpStatus.OK );
        return result;
    }


    public String IterateDiv(int key, int inputInt, String[] digitArr)
    {
        String result="";
        result="X";
        return result;
    }

    @RequestMapping(value = "/to_roman/{integer}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> toRoman(@PathVariable int integer) throws IOException {
        ResponseMessage result=new ResponseMessage();
        LookupTableResponse res=new LookupTableResponse();
        res.initLookupMap();

        try {
            if (integer < 4000) {
                AtomicInteger index = new AtomicInteger();
                final String[] concatStr = {""};
                final int[] intItr = {integer};
                res.getLookupTable()
                        .forEach((key, value)
                                        -> {
                                    final int remainder = intItr[0] % 10;
                                    intItr[0] = intItr[0] / 10;
                                    concatStr[0] = value[remainder] + concatStr[0];
                                    index.getAndIncrement();
                                }
                        );
                result.setMessage(concatStr[0]);
            } else {
                result.setMessage("must be integer between 0 and 3999");
                result.setErrorReason("invalid input");
            }
        }
        catch (Exception e)
        {
            result.setMessage("error");
            result.setErrorReason(e.getMessage());
        }

        return ResponseEntity.ok(result);
    }



    public int convertRoman(String roman,LookupTableResponse lookup)
    {
        int res=0;
        char start=roman.charAt(0);
        if (start=='V'||start=='I')
        {
            String[] rowContent=lookup.getLookupEntrySet().get(0).getRowContent();
            int curIndex=0;
            for (String d:rowContent)
            {
                if(roman.equals(d))
                {
                    res=curIndex;
                    break;
                }
                curIndex++;
            }

        }

       else {
            for (int i = 0; i < 4; i++) {
                int segStartIndex = convertRomanSeg(i, roman, lookup.getIndicators());
                String segStr = roman.substring(segStartIndex);
                String[] rowContent = lookup.getLookupEntrySet().get(i).getRowContent();
                int digit = lookupDigit(segStr, rowContent);

                res += digit * Math.pow(10, i);
                roman = roman.substring(0, segStartIndex);
            }
        }
        return res;
    }

    public int convertRomanSeg(int seg, String roman, String[] indicators)
    {
        int result=0;
        int lastIndex=roman.length()-1;

       // int segCounter=0;

        char segIndicatorLeft=indicators[seg].charAt(0);
        char segIndicatorRight=indicators[seg].charAt(1);

        int segStart=0;
        for (int i=4;i>0;i--)
        {

            char curChar;
			char lastChar='@';
			if(lastIndex>=0)
			{
				lastChar=roman.charAt(lastIndex);
			}
			
			if(lastChar=='V')
			{
				segStart=lastIndex;
				break;
			}
			
			
            int curIndex=lastIndex-i+1;
            if (curIndex>0)
            {
                curChar=roman.charAt(curIndex);
                if(curChar=='M')
                {
                    segStart=curIndex;
                    curIndex--;
                   if(curIndex>=0)
                   {
                       if(curChar=='M')
                       {
                           segStart=curIndex;
                           curIndex--;
                           if(curIndex>=0)
                           {
                               segStart=curIndex;
                           }
                       }
                   }
                }
				 
                else {			
				    if (curChar == segIndicatorLeft || curChar == segIndicatorRight) {
                        segStart = curIndex;
                        break;
                    }
                    else if (curIndex == lastIndex - 1) {
                        segStart = curIndex;
                        break;
                    }
                    curIndex += 1;
                }
            }

        }

        result=segStart;
        return result;
    }


    public int lookupDigit(String segStr, String[] digits)
    {
        int result=0;

        /*AtomicInteger index = new AtomicInteger();
        Arrays.stream(digits)
                .peek((digit) -> index.incrementAndGet())
                .filter(digit -> digit.equals(segStr))
                .findFirst();

        int matchIndex=index.get()-1;

        result =matchIndex;*/

        int curIndex=0;
        for (String d:digits)
        {
            if(segStr.equals(d))
            {
                result=curIndex;
                break;
            }
            curIndex++;
        }




        return result;

    }

    @RequestMapping(value = "/from_roman/{roman}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> fromRoman(@PathVariable String roman) throws IOException {
        ResponseMessage result=new ResponseMessage();
        LookupTableResponse res=new LookupTableResponse();
        res.buildLookupEntrySet();

        int resultInt=0;

        try {
             resultInt=convertRoman(roman,res);

             result.setMessage(""+resultInt);
        }
        catch (Exception e)
        {
            result.setMessage("error");
            result.setErrorReason(e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

}
