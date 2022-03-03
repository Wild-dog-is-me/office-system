package com.gqy.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gqy.server.pojo.MenuRole;
import com.gqy.server.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 耿沁园
 * @since 2022-02-22
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
