package com.gqy.server.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gqy.server.pojo.Employee;
import com.gqy.server.pojo.RespBean;
import com.gqy.server.pojo.RespPageBean;
import com.gqy.server.pojo.Salary;
import com.gqy.server.service.IEmployeeService;
import com.gqy.server.service.ISalaryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ice_water
 * @Date: 2022/03/11/8:03 PM
 * @Description: 要做耿沁园的男人
 */

@RestController
@RequestMapping("/salary/sobcfg")
public class SalarySobCfgController {

    @Autowired
    private ISalaryService salaryService;
    @Autowired
    private IEmployeeService employeeService;


    @ApiOperation(value = "获取所有员工套账")
    @GetMapping("/salaries")
    public List<Salary> getAllSalaries() {
        return salaryService.list();
    }

    @ApiOperation(value = "获取员工所有帐套")
    @GetMapping("/")
    public RespPageBean getEmployeeWithSalary(@RequestParam(defaultValue = "1") Integer currentPage,
                                              @RequestParam(defaultValue = "10") Integer size) {
        return employeeService.getEmployeeWithSalary(currentPage, size);
    }

    @ApiOperation(value = "更新员工帐套")
    @PutMapping("/")
    public RespBean updateEmployeeSalary(Integer eid, Integer sid) {
        if (employeeService.update(new UpdateWrapper<Employee>().set("salaryId", sid).eq("id", eid))) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
