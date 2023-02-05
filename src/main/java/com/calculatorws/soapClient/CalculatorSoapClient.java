package com.calculatorws.soapClient;

import com.calculatorws.wsdl.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class CalculatorSoapClient extends WebServiceGatewaySupport {


    public AddResponse getAddResult(Add request) {
        AddResponse response = (AddResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://www.dneonline.com/calculator.asmx", request,
                        new SoapActionCallback(
                                "http://tempuri.org/Add"));

        return response;
    }

    public SubtractResponse getSubtractResult(Subtract request) {
        SubtractResponse response = (SubtractResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://www.dneonline.com/calculator.asmx", request,
                        new SoapActionCallback(
                                "http://tempuri.org/Subtract"));

        return response;
    }

    public MultiplyResponse getMultiplyResult(Multiply request) {
        MultiplyResponse response = (MultiplyResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://www.dneonline.com/calculator.asmx", request,
                        new SoapActionCallback(
                                "http://tempuri.org/Multiply"));

        return response;
    }

    public DivideResponse getDivideResult(Divide request) {
        DivideResponse response = (DivideResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://www.dneonline.com/calculator.asmx", request,
                        new SoapActionCallback(
                                "http://tempuri.org/Divide"));

        return response;
    }
}
