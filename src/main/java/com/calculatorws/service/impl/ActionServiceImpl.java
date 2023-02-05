package com.calculatorws.service.impl;

import com.calculatorws.entity.Action;
import com.calculatorws.repository.ActionRepository;
import com.calculatorws.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionServiceImpl implements ActionService {

    @Autowired
    private ActionRepository actionRepository;


    @Override
    public Action save(Action action) {
        return actionRepository.save(action);
    }

}
