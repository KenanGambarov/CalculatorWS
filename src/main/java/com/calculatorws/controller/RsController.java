package com.calculatorws.controller;

import com.calculatorws.soapClient.CalculatorSoapClient;
import com.calculatorws.wsdl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/CalculatorWS")
public class RsController {

    @Autowired
    private CalculatorSoapClient soapClient;

    @PostMapping(value = "/add", produces = "application/json")
    public AddResponse add(@Valid @RequestBody Add requestBean) {
        AddResponse response = soapClient.getAddResult(requestBean);
        return response;
    }

    @PostMapping(value ="/subtract", produces = "application/json")
    public SubtractResponse subtract(@Valid @RequestBody Subtract requestBean) {
        SubtractResponse response = soapClient.getSubtractResult(requestBean);
        return response;
    }

    @PostMapping(value ="/multiply", produces = "application/json")
    public MultiplyResponse multiply(@Valid @RequestBody Multiply requestBean) {
        MultiplyResponse response = soapClient.getMultiplyResult(requestBean);
        return response;
    }

    @PostMapping(value ="/divide", produces = "application/json")
    public DivideResponse divide(@Valid @RequestBody Divide requestBean) {
        DivideResponse response = soapClient.getDivideResult(requestBean);
        return response;
    }


}
