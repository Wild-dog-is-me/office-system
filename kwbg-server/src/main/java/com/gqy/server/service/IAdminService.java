package com.gqy.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gqy.server.pojo.Admin;
import com.gqy.server.pojo.RespBean;

import javax.servlet.http.HttpServletRequest;

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
    RespBean login(String username, String password, HttpServletRequest request);

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);
}
