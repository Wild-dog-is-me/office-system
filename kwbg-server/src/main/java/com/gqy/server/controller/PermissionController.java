package com.gqy.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gqy.server.pojo.Menu;
import com.gqy.server.pojo.MenuRole;
import com.gqy.server.pojo.RespBean;
import com.gqy.server.pojo.Role;
import com.gqy.server.service.IMenuRoleService;
import com.gqy.server.service.IMenuService;
import com.gqy.server.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ice_water
 * @Date: 2022/03/02/7:03 PM
 * @Description: 要做耿沁园的男人
 */

@Api(value = "权限管理", tags = "权限管理")
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissionController {

    @Autowired
    IRoleService roleService;
    @Autowired
    IMenuService menuService;
    @Autowired
    IMenuRoleService menuRoleService;

    @ApiOperation("添加角色")
    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role) {
        //判断传入的权限字符是否以 ROLE_ 开头，Security 权限判断要求要 ROLE_ 开头
        if (!role.getName().startsWith("ROLE_")) {
            //如果不是以 ROLE_ 开头，在添加前拼接一个
            role.setName("ROLE_" + role.getName());
        }
        if (roleService.save(role)) {
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation("修改角色")
    @PutMapping("/update")
    public RespBean update(@RequestBody Role role) {
        if (roleService.updateById(role)) {
            return RespBean.success("修改成功！");
        }
        return RespBean.error("修改失败！");
    }

    @ApiOperation("获取所有角色")
    @GetMapping("/")
    public List<Role> getRoleList() {
        return roleService.list();
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRole(@PathVariable Integer rid) {
        if (roleService.removeById(rid)) {
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation("批量删除角色")
    @DeleteMapping("/delete/ids")
    public RespBean deleteRoles(@RequestParam("ids") List<Long> ids) {
        if (roleService.removeByIds(ids)) {
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation("获取所有菜单")
    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @ApiOperation("根据角色ID查找菜单ID")
    @GetMapping("/mid/{rid}")
    public List<Integer> getByIdMenus(@PathVariable Integer rid) {
        List<MenuRole> menuRoles = menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid", rid));
        Stream<Integer> integerStream = menuRoles.stream().map(MenuRole::getMid);
        List<Integer> collect = integerStream.collect(Collectors.toList());
        return collect;
    }

//    @ApiOperation("更新角色菜单")
//    @PutMapping("/updatemenus")
//    public RespBean updateMenuRole(@RequestParam("rid")Integer rid , @RequestParam("mids")List<Integer> mids){
//        return menuRoleService.updateMenuRole(rid,mids);
//    }

    @ApiOperation(value = "更新角色菜单")
    @PutMapping("/")
    public RespBean updateMenu(Integer rid, Integer[] mids) {
        return menuRoleService.updateMenuRole(rid, mids);
    }
}
