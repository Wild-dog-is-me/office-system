package com.gqy.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gqy.server.pojo.AdminRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 耿沁园
 * @since 2022-02-22
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    Integer addAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}
