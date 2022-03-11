package com.gqy.server.controller;


import com.gqy.server.pojo.RespBean;
import com.gqy.server.pojo.Salary;
import com.gqy.server.service.ISalaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 耿沁园
 * @since 2022-02-22
 */
@RestController
@RequestMapping("/salary/sob")
public class SalaryController {

    @Autowired
    public ISalaryService salaryService;

    @ApiOperation(value = "获取所有工资帐套")
    @GetMapping("/")
    public List<Salary> getAllSalaries() {
        return salaryService.list();
    }

    @ApiOperation(value = "添加工资帐套")
    @PostMapping("/")
    public RespBean addSalary(Salary salary) {
        salary.setCreateDate(LocalDateTime.now());
        if (salaryService.save(salary)) {
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "删除工资套账")
    @DeleteMapping("/{id}")
    public RespBean deleteSalary(@PathVariable Integer id) {
        if (salaryService.removeById(id)) {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation("更新工资套账")
    @PutMapping("/")
    public RespBean updateSalary(@RequestBody Salary salary) {
        if (salaryService.updateById(salary)) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
}