package com.gqy.server;

import com.gqy.server.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ice_water
 * @Date: 2022/03/06/11:40 AM
 * @Description: 要做耿沁园的男人
 */

public class AdminUtils {

    /**
     * 获取当前登陆操作员
     * @return
     */

    public static Admin getCurrentAdmin() {
        return (Admin) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
