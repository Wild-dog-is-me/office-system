package com.gqy.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gqy.server.mapper.EmployeeMapper;
import com.gqy.server.pojo.Employee;
import com.gqy.server.pojo.RespBean;
import com.gqy.server.pojo.RespPageBean;
import com.gqy.server.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 耿沁园
 * @since 2022-02-22
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDataScope) {
        /**
         * 开启分页
         */
        Page<Employee> page = new Page<>(currentPage, size);
        /**
         * 只要我们在参数中加入了page，mybatis就会自动挡的帮我们分类
         */
        IPage<Employee> employeeIPage = employeeMapper.getEmployeeByPage(page,employee,beginDataScope);
        return new RespPageBean(employeeIPage.getTotal(),employeeIPage.getRecords());
    }


    @Override
    public RespBean maxWorkID() {
        List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(workID)"));
        return RespBean.success(null, String.format("%08d", Integer.parseInt(maps.get(0).get("max(workID)").toString()) + 1));
    }

    @Override
    public RespBean addEmp(Employee employee) {
        // 合同期限保留两位小数
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days / 365.00)));
        if (employeeMapper.insert(employee) == 1) {
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @Override
    public List<Employee> getEmployee(Integer id) {
        return employeeMapper.getEmployee(id);
    }

    @Override
    public RespPageBean  getEmployeeWithSalary(Integer currentPage, Integer size) {
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeeIPage = employeeMapper.getEmployeeWithSalary(page);
        RespPageBean respPageBean = new RespPageBean(employeeIPage.getTotal(), employeeIPage.getRecords());
        return respPageBean;
    }
}
