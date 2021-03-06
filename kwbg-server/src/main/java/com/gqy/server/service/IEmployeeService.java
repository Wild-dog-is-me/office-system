package com.gqy.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gqy.server.pojo.Employee;
import com.gqy.server.pojo.RespBean;
import com.gqy.server.pojo.RespPageBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 耿沁园
 * @since 2022-02-22
 */
public interface IEmployeeService extends IService<Employee> {

    RespPageBean  getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    RespBean maxWorkID();

    RespBean addEmp(Employee employee);

    List<Employee> getEmployee(Integer id);

    Employee getIdSelectNationByName(Map<String, String> employeeMap);
}
