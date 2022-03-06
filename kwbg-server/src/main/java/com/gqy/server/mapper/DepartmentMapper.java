package com.gqy.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gqy.server.pojo.Department;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 耿沁园
 * @since 2022-02-22
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> getAllDepartments(Integer parentId);

    void addDep(Department dep);

    void deleteDep(Department dep);
}
