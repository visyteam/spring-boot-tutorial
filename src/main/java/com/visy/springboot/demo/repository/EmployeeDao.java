package com.visy.springboot.demo.repository;


import com.visy.springboot.demo.repository.entity.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends CrudRepository<EmployeeEntity, Long> {}