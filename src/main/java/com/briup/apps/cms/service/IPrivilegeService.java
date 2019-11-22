package com.briup.apps.cms.service;

import com.briup.apps.cms.bean.Privilege;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.vm.PrivilegeTree;

import java.util.List;

public interface IPrivilegeService {
    List<Privilege> findAll();
    void insertPrivilege(Privilege privilege);
    void updatePrivilegeById(Privilege privilege);
    void deletePrivilegeById(Long id);
    Privilege findPrivilegeById(Long id);

    List<PrivilegeTree> findPrivilegeTree();
    void saveOrUpdate(Privilege privilege) throws CustomerException;
    List<Privilege> findByParentId(Long parentId);
    List<Privilege> selectByUserId(long id);
}
