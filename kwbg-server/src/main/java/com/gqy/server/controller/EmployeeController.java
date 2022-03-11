package com.gqy.server.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.gqy.server.pojo.*;
import com.gqy.server.service.*;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 耿沁园
 * @since 2022-02-22
 */
@RestController
@RequestMapping("/employee/basic")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    IPoliticsStatusService politicsStatusService;

    @Autowired
    IJoblevelService joblevelService;

    @Autowired
    INationService nationService;

    @Autowired
    IPositionService positionService;

    @Autowired
    IDepartmentService departmentService;

    @ApiOperation(value = "获取所有员工（分页）")
    @GetMapping("/")
    public RespPageBean getEmployee(@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    Employee employee,
                                    LocalDate[] beginDateScope) {
        return employeeService.getEmployeeByPage(currentPage, size, employee, beginDateScope);
    }

    @ApiOperation(value = "获取所有政治面貌")
    @GetMapping("/politicsstatues")
    public List<PoliticsStatus> getAllPoliticsStatues() {
        return politicsStatusService.list();
    }

    @ApiOperation(value = "获取所有职称")
    @GetMapping("joblevels")
    public List<Joblevel> getAllJoblevels() {
        return joblevelService.list();
    }

    @ApiOperation(value = "获取民族")
    @GetMapping("/nations")
    public List<Nation> getAllNations() {
        return nationService.list();
    }

    @ApiOperation(value = "获取所有职位")
    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        return positionService.list();
    }

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/deps")
    public List<Department> getAllDeps() {
        return departmentService.list();
    }

    @ApiOperation(value = "获取工号")
    @GetMapping("/maxWorkID")
    public RespBean maxWorkID() {
        return employeeService.maxWorkID();
    }

    @ApiOperation(value = "添加员工")
    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee) {
        return employeeService.addEmp(employee);
    }

    @ApiOperation(value = "更新员工")
    @PutMapping("/")
    public RespBean updateEmployee(@RequestBody Employee employee) {
        if (employeeService.updateById(employee)) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除员工")
    @DeleteMapping("/{id}")
    public RespBean deleteEmp(@PathVariable Integer id) {
        if (employeeService.removeById(id)) {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "导出员工数据")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    public void exportEmployee(HttpServletResponse response) {
        List<Employee> list = employeeService.getEmployee(null);
        ExportParams exportParams = new ExportParams("员工表", "员工表", ExcelType.HSSF);
        Workbook sheets = ExcelExportUtil.exportExcel(exportParams, Employee.class, list);
        ServletOutputStream outputStream = null;
        try {
            //以流形式导出
            response.setHeader("content-type", "application/octet-stream");
            //防止中文乱码
            response.setHeader("content-disposition", "attachment;filename=" +
                    URLEncoder.encode("员工表.xls", "UTF-8"));
            outputStream = response.getOutputStream();
            //写出数据
            sheets.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @ApiOperation("员工数据导入")
    @PostMapping("/import")
    public RespBean employeeImport(MultipartFile file) {
        ImportParams importParams = new ImportParams();
        //去掉标题行防止报错
        importParams.setTitleRows(1);
        //获取所有民族
        List<Nation> nations = nationService.list();
        //部门
        List<Department> departments = departmentService.list();
        //职称
        List<Joblevel> joblevels = joblevelService.list();
        //职位
        List<Position> positions = positionService.list();
        //政治面貌
        List<PoliticsStatus> politicsStatuses = politicsStatusService.list();
        try {
            List<Employee> employees = ExcelImportUtil.importExcel(file.getInputStream(), Employee.class, importParams);
            employees.forEach(employee -> {
                //设置员工对象的民族id
                employee.setNationId(nations.get(
                        nations.indexOf(new Nation(employee.getNation().getName()))
                ).getId());
                //设置员工对象的部门id
                employee.setDepartmentId(departments.get(
                        departments.indexOf(new Department(employee.getDepartment().getName()))
                ).getId());
                //设置员工对象的职称id
                employee.setJobLevelId(joblevels.get(
                        joblevels.indexOf(new Joblevel(employee.getJoblevel().getName()))
                ).getId());
                //设置员工对象的政治面貌id
                employee.setPoliticId(politicsStatuses.get(
                        politicsStatuses.indexOf(new PoliticsStatus(employee.getPoliticsStatus().getName()))
                ).getId());
                //设置员工对象的职位id
                employee.setPosId(positions.get(
                        positions.indexOf(new Position(employee.getPosition().getName()))
                ).getId());
            });
            if (employeeService.saveBatch(employees)) {
                return RespBean.success("导入成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("导入失败！");
    }
}
