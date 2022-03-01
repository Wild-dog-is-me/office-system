package com.gqy.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gqy.server.pojo.Admin;
import com.gqy.server.pojo.Menu;
import com.gqy.server.pojo.RespBean;
import com.gqy.server.pojo.Role;
import io.swagger.models.auth.In;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 耿沁园
 * @since 2022-02-22
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 登陆之后返回token
     * @param username
     * @param password
     * @param request
     * @return
     */
    RespBean login(String username, String password,String code, HttpServletRequest request);

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);

    /**
     * 根据用户id查询角色列表
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);
}
