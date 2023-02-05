package com.calculatorws;

import com.calculatorws.wsdl.Add;
import com.calculatorws.wsdl.AddResponse;
import com.calculatorws.wsdl.Divide;
import com.calculatorws.wsdl.DivideResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;

@SpringBootApplication
public class CalculatorWsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculatorWsApplication.class, args);
        RestTemplate restTemplate = new RestTemplate();
        Divide add = new Divide();
        add.setIntA(0);
        add.setIntB(3);
        HttpEntity<Divide> request = new HttpEntity<>(add);
        ResponseEntity<DivideResponse> addResponse = restTemplate.postForEntity("http://localhost:8080/CalculatorWS/add", request, DivideResponse.class);
        System.out.println("ResponseAble " + addResponse.getBody().getDivideResult() + " " + addResponse.getStatusCode().getReasonPhrase());
    }

}
