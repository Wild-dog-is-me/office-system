package com.gqy.server.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gqy.server.pojo.RespBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * 当未登陆或者token失效访问接口时，自定义的返回结果
 * @Author: ice_water
 * @Date: 2022/02/25/2:37 PM
 * @Description: 要做耿沁园的男人
 */

/**
 * 当未登录或者token失效时访问接口是，自定义返回结果
 */
@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        //设置数据格式为json格式
        response.setContentType("application/json");
        //拿到输出流
        PrintWriter out = response.getWriter();
        //未登录或失效
        RespBean bean = RespBean.error("未登录或用户信息过期，请重新登录！");
        bean.setCode(401);
        out.write(new ObjectMapper().writeValueAsString(bean));
        out.flush();
        out.close();
    }
}
