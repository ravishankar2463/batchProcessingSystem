package com.example.ravi.ProcessAndUserService.processes.repository;

import com.example.ravi.ProcessAndUserService.processes.models.ProcessStep;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessStepRepository extends CrudRepository<ProcessStep,String> {
    @Query(value = "select * from process_steps ps where ps.id = :id and ps.process_id = :processId",nativeQuery = true)
    Optional<ProcessStep> findByIdAndProcessId(@Param("id") String id,@Param("processId") String processId);
    @Query(value = "select * from process_steps ps where ps.name = :name and ps.process_id = :processId",nativeQuery = true)
    Optional<ProcessStep> findByNameAndProcessId(@Param("name") String name,@Param("processId") String processId);
}
