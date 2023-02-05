package com.calculatorws.service;

import com.calculatorws.entity.Log;
import org.springframework.stereotype.Service;

@Service
public interface DataService {

    Log save(Log log);
}
