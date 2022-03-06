package com.gqy.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gqy.server.pojo.Admin;
import com.gqy.server.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 耿沁园
 * @since 2022-02-22
 */
public interface AdminMapper extends BaseMapper<Admin> {

    List<Admin> getAllAdmins(@Param("id") Integer id, @Param("keywords") String keywords);
}
