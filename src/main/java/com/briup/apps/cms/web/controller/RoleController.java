package com.briup.apps.cms.web.controller;

import com.briup.apps.cms.bean.Message;
import com.briup.apps.cms.bean.Privilege;
import com.briup.apps.cms.bean.Role;
import com.briup.apps.cms.bean.extend.BaseRoleExtend;
import com.briup.apps.cms.service.IRoleService;
import com.briup.apps.cms.utils.MessageUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private IRoleService service;

    @GetMapping("findAll")
    public Message findAll(){
        return MessageUtil.success("查找成功!",service.findAll());
    }
    @GetMapping("insertRole")
    public Message insertRole(Role role){
        service.insertRole(role);
        return MessageUtil.success("添加成功!");
    }
    @GetMapping("updateRoleById")
    public Message updateRoleById(Role role){
        service.updateRoleById(role);
        return MessageUtil.success("更新成功!");
    }
    @GetMapping("deleteRoleById")
    public Message deleteRoleById(Long id){
        service.deleteRoleById(id);
        return MessageUtil.success("删除成功!");
    }
    @GetMapping("findRoleById")
    public Message findRoleById(Long id){
        return MessageUtil.success("查询成功!",service.findRoleById(id));
    }

    @GetMapping("selectAllRoleWithPrivilege")
    public Message selectAllRoleWithPrivilege(){
        return MessageUtil.success("查询成功!",service.selectAllRoleWithPrivilege());
    }
    @GetMapping("deleteRoleByRoleId")
    public Message deleteRoleByRoleId(Long id){
        service.deleteRoleByRoleId(id);
        return MessageUtil.success("删除成功!");
    }


    @ApiOperation(value = "增加或者更新角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "编号",paramType = "query"),
            @ApiImplicitParam(name = "name",value = "角色名称",paramType = "query",required = true)
    })
    @PostMapping("saveOrUpdate")
    public Message saveOrUpdate(Long id,@NotNull String name){
        Role role=new Role();
        role.setId(id);
        role.setName(name);
        service.saveOrUpdate(role);
        return MessageUtil.success("操作成功!");
    }
    @PostMapping("authorization")
    public Message authorization(Long id, Long[] privileges){
        service.authorization(id,privileges);
        return MessageUtil.success("授权成功!");
    }
}
