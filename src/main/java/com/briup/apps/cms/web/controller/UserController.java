package com.briup.apps.cms.web.controller;

import com.briup.apps.cms.bean.Message;
import com.briup.apps.cms.bean.Role;
import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.bean.extend.BaseUserExtend;
import com.briup.apps.cms.service.IUserService;
import com.briup.apps.cms.utils.JwtTokenUtil;
import com.briup.apps.cms.utils.MessageUtil;
import com.briup.apps.cms.vm.UserRoleVM;
import com.briup.apps.cms.vm.UserVM;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService service;

    @GetMapping(value = "/findUser")
    public Message findUser(){
        return MessageUtil.success("查找用户成功!",service.findAll());
    }

    @PostMapping(value = "/insertUser")
    public Message insertUser(User user){
        service.insertUser(user);
        return MessageUtil.success("添加用户成功!");
    }

    @GetMapping("deleteUserById")
    public Message deleteUserById(Long id){
        service.deleteUserById(id);
        return MessageUtil.success("删除用户成功!");
    }

    @PostMapping("findUserById")
    public Message findUserById(User user){
        return MessageUtil.success("查找用户成功!",service.findUserById(user.getId()));
    }

    @PostMapping("updateUserById")
    public Message updateUserById(User user){
        service.updateUserById(user);
        return MessageUtil.success("更新用户成功!");
    }
    @PostMapping("login")
    public Message login(@RequestBody UserVM userVM){
//        System.out.println(userVM);
        Map<String,String>map=new HashMap<>();
        // 1. 认证用户的用户名和密码
        User user=service.selectUser(userVM);
        // 2. 如果登录成功产生token,将token缓存起来，返回
        String token = JwtTokenUtil.createJWT(user.getId(), user.getUsername());
        map.put("token",token);
        return MessageUtil.success(map);
    }

    @ApiOperation(value = "通过token获取用户的基本信息")
    @GetMapping("info")
    public Message info(String token){
        // 1. 通过token获取用户信息  {id,use,gender,roles:[]}
        long id = Long.parseLong(JwtTokenUtil.getUserId(token,JwtTokenUtil.base64Secret));
        BaseUserExtend userExtend=service.findById(id);

        List<Role>roles=new ArrayList<>();
        if (userExtend.getRoles().size()<=0){
            Role role=new Role();
            role.setName("ordinary");
            roles.add(role);
            userExtend.setRoles(roles);
        }
        return MessageUtil.success(userExtend);
    }
    @PostMapping("logout")
    public Message logout(){
        // 1. 退出， token从缓存中移除掉
        return MessageUtil.success("退出成功!");
    }

    @PostMapping("updateOrSaveUser")
    public Message updateOrSaveUser(User user){
        service.updateOrSaveUser(user);
        return MessageUtil.success("操作成功!");
    }
    @GetMapping("cascadeRoleFindAll")
    public Message cascadeRoleFindAll(){
        return MessageUtil.success("查询成功!",service.cascadeRoleFindAll());
    }

    @GetMapping("selectById")
    public Message selectById(Long id){
        return MessageUtil.success("查询成功!",service.selectById(id));
    }

    @PostMapping("setRoles")
    public Message setRoles(UserRoleVM userRoleVM){
        service.setRoles(userRoleVM.getId(),userRoleVM.getRoles());
        return MessageUtil.success("设置成功!");
    }
}
