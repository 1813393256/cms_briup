package com.briup.apps.cms.service.impl;

import com.briup.apps.cms.bean.*;
import com.briup.apps.cms.bean.extend.BaseRoleExtend;
import com.briup.apps.cms.dao.PrivilegeMapper;
import com.briup.apps.cms.dao.RoleMapper;
import com.briup.apps.cms.dao.RolePrivilegeMapper;
import com.briup.apps.cms.dao.extend.BaseRoleExtendMapper;
import com.briup.apps.cms.service.IRoleService;
import com.briup.apps.cms.utils.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Resource
    private RoleMapper mapper;

    @Resource
    private RolePrivilegeMapper rolePrivilegeMapper;

    @Resource
    private BaseRoleExtendMapper roleExtendMapper;

    @Override
    public List<Role> findAll() {
        return mapper.selectByExample(new RoleExample());
    }

    @Override
    public void insertRole(Role role) {
        if (role != null) {
            mapper.insert(role);
            return;
        }
        throw new CustomerException("添加失败!");
    }

    @Override
    public void updateRoleById(Role role) {
        if (role != null) {
            if (role.getId() != null) {
                Role role1 = mapper.selectByPrimaryKey(role.getId());
                if (role1 != null) {
                    mapper.updateByPrimaryKey(role);
                    return;
                }
            }
        }
        throw new CustomerException("更新失败!");
    }

    @Override
    public void deleteRoleById(Long id) {
        if (id != null) {
            mapper.deleteByPrimaryKey(id);
            return;
        }
        throw new CustomerException("删除失败!");
    }

    @Override
    public Role findRoleById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveOrUpdate(Role role) throws CustomerException {
        if (role != null) {
            if (role.getId() != null) {
                mapper.updateByPrimaryKey(role);
                return;
            }
            RoleExample example=new RoleExample();
            example.createCriteria().andNameEqualTo(role.getName());
            List<Role> roles = mapper.selectByExample(example);
            if (roles.size()>0){
                throw new CustomerException("用户名已存在!");
            }
        }
        mapper.insert(role);
    }

    @Override
    public List<BaseRoleExtend> selectAllRoleWithPrivilege() {
        return roleExtendMapper.selectAllRoleWithPrivilege();
    }


    @Transactional
    @Override
    public void deleteRoleByRoleId(Long id) {
        roleExtendMapper.deletePrivilegeByRoleId(id);
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void authorization(Long id, Long[] privileges) {
        List<Long>plist=Arrays.asList(privileges);
        RolePrivilegeExample example=new RolePrivilegeExample();
        example.createCriteria().andRoleIdEqualTo(id);
        if (privileges.length<=0){
            rolePrivilegeMapper.deleteByExample(example);
            return;
        }
        RolePrivilege rolePrivilege=new RolePrivilege();
        List<RolePrivilege> rolePrivileges = rolePrivilegeMapper.selectByExample(example);
        if (rolePrivileges.size()<=0){
            for (Long privilege:privileges){
                rolePrivilege.setRoleId(id);
                rolePrivilege.setPrivilegeId(privilege);
                rolePrivilegeMapper.insert(rolePrivilege);
            }
            return;
        }

        List<Long>list=new ArrayList<>();//存着旧权限
        rolePrivileges.forEach(x->list.add(x.getPrivilegeId()));
        for (Long pid:privileges){
            if (!list.contains(pid)){
                rolePrivilege.setRoleId(id);
                rolePrivilege.setPrivilegeId(pid);
                rolePrivilegeMapper.insert(rolePrivilege);
            }
        }
        list.forEach(oid->{
            if (!plist.contains(oid)){
                example.clear();
                example.createCriteria().andRoleIdEqualTo(id).andPrivilegeIdEqualTo(oid);
                rolePrivilegeMapper.deleteByExample(example);
            }
        });


    }
}
