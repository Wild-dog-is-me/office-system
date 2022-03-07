package com.gqy.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gqy.server.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 耿沁园
 * @since 2022-02-22
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    IPage<Employee> getEmployeeByPage(Page<Employee> page, @Param("employee") Employee employee,
                                      @Param("beginDateScope") LocalDate[] beginDateScope);

    List<Employee> getEmployee(Integer id);
}
