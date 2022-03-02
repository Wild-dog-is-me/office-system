package com.gqy.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gqy.server.pojo.Role;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 耿沁园
 * @since 2022-02-22
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id查询列表
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);
}
