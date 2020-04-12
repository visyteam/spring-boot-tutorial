package com.visy.springboot.demo.service;


import javax.validation.Valid;

import com.visy.springboot.demo.model.Employee;
import com.visy.springboot.demo.repository.EmployeeDao;
import com.visy.springboot.demo.repository.entity.EmployeeEntity;
import com.visy.springboot.demo.util.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class EmployeeService {

    @Autowired
    private EmployeeMapper mapper;

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    @Validated
    public Long create(@Valid Employee employee) {
        EmployeeEntity employeeEntity = mapper.toEmployeeEntity(employee);
        employeeDao.save(employeeEntity);

        return employeeEntity.getId();
    }

}