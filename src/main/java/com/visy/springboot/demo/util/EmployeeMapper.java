package com.visy.springboot.demo.util;


import com.visy.springboot.demo.model.Employee;
import com.visy.springboot.demo.model.Phone;
import com.visy.springboot.demo.repository.entity.EmployeeEntity;
import com.visy.springboot.demo.repository.entity.PhoneEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeEntity toEmployeeEntity(Employee employee);

    Employee toEmployee(EmployeeEntity employeeEntity);

    PhoneEntity toPhoneEntity(Phone phone);

    Phone toPhone(PhoneEntity phoneEntity);
}