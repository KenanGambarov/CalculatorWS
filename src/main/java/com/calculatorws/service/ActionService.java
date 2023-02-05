package com.calculatorws.service;

import com.calculatorws.entity.Action;
import org.springframework.stereotype.Service;

@Service
public interface ActionService {

    Action save(Action action);
}
