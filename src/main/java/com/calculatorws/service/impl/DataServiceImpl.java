package com.calculatorws.service.impl;

import com.calculatorws.entity.Log;
import com.calculatorws.repository.DataRepository;
import com.calculatorws.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImpl implements DataService {

    private DataRepository dataRepository;

    @Autowired
    public DataServiceImpl(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public Log save(Log log) {
        return dataRepository.save(log);
    }

}
