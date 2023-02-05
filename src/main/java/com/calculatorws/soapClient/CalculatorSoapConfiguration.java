package com.calculatorws.soapClient;

import com.calculatorws.interceptor.CalculatorInterceptor;
import com.calculatorws.service.ActionService;
import com.calculatorws.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;

@Configuration
public class CalculatorSoapConfiguration {

    @Autowired
    private ActionService actionService;
    @Autowired
    private DataService dataService;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.calculatorws.wsdl");
        return marshaller;
    }

    @Bean
    public CalculatorSoapClient countryClient(Jaxb2Marshaller marshaller) {
        CalculatorSoapClient client = new CalculatorSoapClient();
        client.setDefaultUri("http://www.dneonline.com/");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);

        ClientInterceptor[] interceptors =
                new ClientInterceptor[] {new CalculatorInterceptor(actionService,dataService)};
        client.setInterceptors(interceptors);
        return client;
    }

}
