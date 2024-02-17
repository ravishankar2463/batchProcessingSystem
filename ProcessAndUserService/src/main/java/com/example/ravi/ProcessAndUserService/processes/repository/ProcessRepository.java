package com.example.ravi.ProcessAndUserService.processes.repository;

import com.example.ravi.ProcessAndUserService.processes.models.Process;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProcessRepository extends CrudRepository<Process, String> {
    Optional<Process> findByName(String name);
}
