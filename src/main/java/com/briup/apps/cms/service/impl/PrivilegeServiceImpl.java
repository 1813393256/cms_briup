package com.briup.apps.cms.service.impl;

import com.briup.apps.cms.bean.Privilege;
import com.briup.apps.cms.bean.PrivilegeExample;
import com.briup.apps.cms.dao.PrivilegeMapper;
import com.briup.apps.cms.dao.extend.BasePrivilegeExtendMapper;
import com.briup.apps.cms.service.IPrivilegeService;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.vm.PrivilegeTree;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PrivilegeServiceImpl implements IPrivilegeService {

    @Resource
    private PrivilegeMapper mapper;

    @Resource
    private BasePrivilegeExtendMapper basePrivilegeExtendMapper;

    @Override
    public List<Privilege> findAll() {
        return mapper.selectByExample(new PrivilegeExample());
    }

    @Override
    public void insertPrivilege(Privilege privilege) {
        if (privilege != null) {
            mapper.insert(privilege);
            return;
        }
        throw new CustomerException("添加失败!");
    }

    @Override
    public void updatePrivilegeById(Privilege privilege) {
        if (privilege != null) {
            mapper.updateByPrimaryKey(privilege);
            return;
        }
        throw new CustomerException("更新失败!");
    }

    @Override
    public void deletePrivilegeById(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public Privilege findPrivilegeById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PrivilegeTree> findPrivilegeTree() {
        return basePrivilegeExtendMapper.selectAll();
    }

    @Override
    public void saveOrUpdate(Privilege privilege) throws CustomerException {
        if (privilege.getName()!=null){
            if (privilege.getId() != null) {
                if (privilege.getId()==privilege.getParentId()){
                    throw new CustomerException("不能给自己授权!");
                }
                mapper.updateByPrimaryKey(privilege);
            } else {
                mapper.insert(privilege);
            }
            return;
        }
        throw new CustomerException("权限名不能为空!");

    }

    @Override
    public List<Privilege> findByParentId(Long parentId) {
        PrivilegeExample example = new PrivilegeExample();
        if (parentId == null) {
            example.createCriteria().andParentIdIsNull();
        } else {
            example.createCriteria().andParentIdEqualTo(parentId);
        }
        return mapper.selectByExample(example);
    }

    @Override
    public List<Privilege> selectByUserId(long id) {
        return basePrivilegeExtendMapper.selectByUserId(id);
    }

}
