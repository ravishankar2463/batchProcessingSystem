package com.example.ravi.ProcessAndUserService.processes.repository;

import com.example.ravi.ProcessAndUserService.processes.models.ProcessStep;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessStepRepository extends CrudRepository<ProcessStep,String> {

}
