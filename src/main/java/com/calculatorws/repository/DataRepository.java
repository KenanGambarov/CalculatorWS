package com.calculatorws.repository;

import com.calculatorws.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Log, Integer> {
}
