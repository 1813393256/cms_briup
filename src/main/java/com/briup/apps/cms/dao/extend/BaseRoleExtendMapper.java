package com.briup.apps.cms.dao.extend;

import com.briup.apps.cms.bean.Role;
import com.briup.apps.cms.bean.extend.BaseRoleExtend;

import java.util.List;

public interface BaseRoleExtendMapper {
    List<Role> selectByUserId(long id);

    List<BaseRoleExtend> selectAll();
    List<BaseRoleExtend> selectAllRoleWithPrivilege();

    void deletePrivilegeByRoleId(Long id);

}
