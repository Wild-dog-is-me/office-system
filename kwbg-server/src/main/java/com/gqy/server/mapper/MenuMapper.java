package com.gqy.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gqy.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 耿沁园
 * @since 2022-02-22
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户id查询菜单
     * @param id
     * @return
     */
    List<Menu> getMenuByAdminId(Integer id);

    /**
     * 根据角色获取菜单列表
     */
    List<Menu> getMenusWithRole();
}
