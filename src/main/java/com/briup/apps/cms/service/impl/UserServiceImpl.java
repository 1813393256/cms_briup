package com.briup.apps.cms.service.impl;

import com.briup.apps.cms.bean.*;
import com.briup.apps.cms.bean.extend.BaseUserExtend;
import com.briup.apps.cms.dao.UserMapper;
import com.briup.apps.cms.dao.UserRoleMapper;
import com.briup.apps.cms.dao.extend.BaseUserExtendMapper;
import com.briup.apps.cms.service.IUserService;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.vm.UserVM;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper mapper;

    @Resource
    private BaseUserExtendMapper baseUserExtendMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public void insertUser(User user) {
        if (user.getUsername() != null) {
            UserExample example = new UserExample();
            example.createCriteria().andUsernameEqualTo(user.getUsername());
            List<User> users = mapper.selectByExample(example);
            if (users.size() <= 0) {
                mapper.insert(user);
                return;
            }
        }
        throw new CustomerException("添加用户失败!");
    }

    @Override
    public void updateUserById(User user) {
        if (user != null) {
            if (user.getId() != null) {
                User user1 = mapper.selectByPrimaryKey(user.getId());
                if (user1 != null) {
                    mapper.updateByPrimaryKey(user);
                    return;
                }
            }
        }
        throw new CustomerException("更新失败!");
    }

    @Override
    public void deleteUserById(Long id) {
        if (id != null) {
            User user1 = mapper.selectByPrimaryKey(id);
            if (user1 != null) {
                mapper.deleteByPrimaryKey(id);
                return;
            }
        }
        throw new CustomerException("删除用户失败!");
    }

    @Override
    public List<User> findAll() {
        UserExample example = new UserExample();
        return mapper.selectByExample(example);
    }

    @Override
    public User findUserById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BaseUserExtend> cascadeRoleFindAll() {
        return baseUserExtendMapper.selectAll();
    }

    @Override
    public BaseUserExtend findById(long id) {
        return baseUserExtendMapper.selectById(id);
    }

    @Override
    public void updateOrSaveUser(User user) {
        if (user.getId() != null) {
            mapper.updateByPrimaryKey(user);
            return;
        }
        mapper.insert(user);
    }

    @Override
    public BaseUserExtend selectById(Long id) {
        return baseUserExtendMapper.selectById(id);
    }

    @Override
    public void setRoles(Long id, List<Long> roles) {
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUserIdEqualTo(id);
        if (roles==null){
            userRoleMapper.deleteByExample(example);
            return;
        }
        List<UserRole> list = userRoleMapper.selectByExample(example);
        List<Long>oldRoleId=new ArrayList<>();
        list.forEach(x->oldRoleId.add(x.getRoleId()));

        for (Long roleId:roles){
            if (!oldRoleId.contains(roleId)){
                UserRole userRole=new UserRole();
                userRole.setUserId(id);
                userRole.setRoleId(roleId);
                userRoleMapper.insert(userRole);
            }
        }

        for (UserRole userRole:list){
            if (!roles.contains(userRole.getRoleId())){
                userRoleMapper.deleteByPrimaryKey(userRole.getId());
            }
        }
    }

    @Override
    public User selectUser(UserVM userVM) {
        UserExample example=new UserExample();
        example.createCriteria().andUsernameEqualTo(userVM.getUsername()).andPasswordEqualTo(userVM.getPassword());
        List<User> users = mapper.selectByExample(example);
        if (users.size()<=0){
            throw new CustomerException("用户名或密码错误!");
        }
        return users.get(0);
    }
}
