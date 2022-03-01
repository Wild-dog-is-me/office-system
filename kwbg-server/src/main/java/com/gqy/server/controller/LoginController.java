package com.gqy.server.controller;

import com.gqy.server.pojo.Admin;
import com.gqy.server.pojo.AdminLoginParam;
import com.gqy.server.pojo.RespBean;
import com.gqy.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ice_water
 * @Date: 2022/02/24/11:27 PM
 * @Description: 要做耿沁园的男人
 */

@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "登陆之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request) {
        return adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword(),adminLoginParam.getCode(), request);
    }

    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal) {
        if (null == principal) {
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        admin.setPassword(null);
        return admin;
    }

    @ApiOperation(value = "退出登陆")
    @PostMapping("/logout")
    public RespBean logout() {
        return RespBean.success("退出成功");
    }
}
