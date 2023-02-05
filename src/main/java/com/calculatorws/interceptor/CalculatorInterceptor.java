package com.calculatorws.interceptor;

import com.calculatorws.entity.Action;
import com.calculatorws.entity.Log;
import com.calculatorws.service.ActionService;
import com.calculatorws.service.DataService;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

@Component
public class CalculatorInterceptor implements HandlerInterceptor, ClientInterceptor {

    private final static Logger LOG = LogManager.getLogger();
    private static final String REQUEST_TO_JSON = "Request to JSON";
    private static final String REQUEST_TO_SOAP = "Request to SOAP";
    private static final String REQUEST_FROM_SOAP = "Request from SOAP";
    private static int counter = 1;
    private final ActionService actionService;
    private final DataService dataService;

    public CalculatorInterceptor(ActionService actionService, DataService dataService) {
        this.actionService = actionService;
        this.dataService = dataService;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        postData(REQUEST_TO_JSON + " [" + request.getMethod() + " '" + request.getServletPath() + "'] ");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        counter++;
    }

    @Override
    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
        String payload="";
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            messageContext.getRequest().writeTo(buffer);
            payload = buffer.toString(java.nio.charset.StandardCharsets.UTF_8.name());

        } catch (IOException e) {
            throw new WebServiceClientException("Can not write the SOAP request into the out stream", e) {
                private static final long serialVersionUID = -7118480620416458069L;
            };
        }
        postData(REQUEST_TO_SOAP +
                " [" + messageContext.getRequest().toString() + " " + payload + "]");
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
        String payload="";
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            messageContext.getResponse().writeTo(buffer);
            payload = buffer.toString(java.nio.charset.StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            throw new WebServiceClientException("Can not write the SOAP response into the out stream", e) {
                private static final long serialVersionUID = -7118480620416458069L;
            };
        }
        postData(REQUEST_FROM_SOAP + " [" + payload + "]");
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Exception e) throws WebServiceClientException {

    }

    private void postData(String requestStaticString) {
        LOG.log(Level.forName("DIAG", 350), "Call {}. " + requestStaticString, counter);
        Action action = new Action(new Date());
        Log log = new Log(new Date(),  "Call " + counter + ". " + requestStaticString);
        log.setAction(action);
        actionService.save(action);
        dataService.save(log);

    }
}
