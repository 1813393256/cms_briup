package com.briup.apps.cms.service;

import com.briup.apps.cms.bean.Privilege;
import com.briup.apps.cms.bean.Role;
import com.briup.apps.cms.bean.extend.BaseRoleExtend;
import com.briup.apps.cms.utils.CustomerException;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface IRoleService {
    List<Role> findAll();
    void insertRole(Role role);
    void updateRoleById(Role role);
    void deleteRoleById(Long id);
    Role findRoleById(Long id);
    void saveOrUpdate(Role role) throws CustomerException;
    List<BaseRoleExtend> selectAllRoleWithPrivilege();
    //删除角色及其权限
    void deleteRoleByRoleId(Long id);

    void authorization(@NotNull Long id, Long[] privileges);
}
