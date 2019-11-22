package com.briup.apps.cms.service;

import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.bean.extend.BaseUserExtend;
import com.briup.apps.cms.vm.UserVM;

import java.util.List;

public interface IUserService {
    void insertUser(User user);
    void updateUserById(User user);
    void deleteUserById(Long id);
    List<User>findAll();
    User findUserById(Long id);
    List<BaseUserExtend> cascadeRoleFindAll();
    BaseUserExtend findById(long id);
    void updateOrSaveUser(User user);
    BaseUserExtend selectById(Long id);
    void setRoles(Long id, List<Long> roles);

    User selectUser(UserVM userVM);
}
