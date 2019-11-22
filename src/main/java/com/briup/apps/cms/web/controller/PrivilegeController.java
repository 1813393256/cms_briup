package com.briup.apps.cms.web.controller;

import com.briup.apps.cms.bean.Message;
import com.briup.apps.cms.bean.Privilege;
import com.briup.apps.cms.service.IPrivilegeService;
import com.briup.apps.cms.utils.MessageUtil;
import com.briup.apps.cms.vm.PrivilegeTree;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("privilege")
public class PrivilegeController {
    @Autowired
    private IPrivilegeService service;

    @GetMapping("findAll")
    public Message findAll(){
        return MessageUtil.success("查找成功!",service.findAll());
    }
    @GetMapping("insertPrivilege")
    public Message insertPrivilege(Privilege privilege){
        service.insertPrivilege(privilege);
        return MessageUtil.success("添加成功!");
    }
    @GetMapping("updatePrivilegeById")
    public Message updatePrivilegeById(Privilege privilege){
        service.updatePrivilegeById(privilege);
        return MessageUtil.success("更新成功!");
    }
    @GetMapping("deletePrivilegeById")
    public Message deletePrivilegeById(Long id){
        service.deletePrivilegeById(id);
        return MessageUtil.success("删除成功!");
    }
    @GetMapping("findPrivilegeById")
    public Message findPrivilegeById(Long id){

        return MessageUtil.success("查询成功!",service.findPrivilegeById(id));
    }

    @ApiOperation(value = "查询树")
    @GetMapping(value = "findPrivilegeTree")
    public Message findPrivilegeTree(){
        List<PrivilegeTree> list = service.findPrivilegeTree();
        return MessageUtil.success(list);
    }

    @ApiOperation(value = "通过parentId查询")
    @GetMapping(value = "findByParentId")
    public Message findByParentId(Long id){
        List<Privilege> list = service.findByParentId(id);
        return MessageUtil.success(list);
    }
    @ApiOperation(value ="保存或更新")
    @PostMapping(value = "saveOrUpdate")
    public Message saveOrUpdate(Privilege privilege){
        service.saveOrUpdate(privilege);
        return MessageUtil.success("更新成功");
    }
    @ApiOperation(value ="通过用户id查权限")
    @GetMapping("selectByUserId")
    public Message selectByUserId(Long id){
        List<Privilege> privileges=service.selectByUserId(id);
        return MessageUtil.success("查询成功!",privileges);
    }
}
