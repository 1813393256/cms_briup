package com.briup.apps.cms.service.impl;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.CategoryExample;
import com.briup.apps.cms.dao.CategoryMapper;
import com.briup.apps.cms.service.ICategoryService;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.utils.MessageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Resource
    private CategoryMapper mapper;

    @Transactional
    @Override
    public void insertCategory(Category category) {
        if (category != null) {
            if (category.getName()!=null){
                mapper.insert(category);
                return;
            }
        }
       throw new CustomerException("添加失败");

    }
    @Transactional
    @Override
    public void updateCategoryById(Category category) {
        if (category!=null){
            if (category.getId()!=null){
                Category categoryById = mapper.selectByPrimaryKey(category.getId());
                if (categoryById!=null){
                    mapper.updateByPrimaryKey(category);
                    return;
                }
            }
        }
        throw new CustomerException("更新失败");

    }

    @Transactional
    @Override
    public void deleteCategoryById(Long id) {
        if (id!=null){
            Category category = mapper.selectByPrimaryKey(id);
            if (category!=null){
                mapper.deleteByPrimaryKey(id);
                return;
            }
        }
        throw new CustomerException("删除失败!");
    }

    @Override
    public Category findCategoryById(Long id) {
        if (id!=null){
          return  mapper.selectByPrimaryKey(id);
        }
        throw new CustomerException("查找失败!");
    }

    @Override
    public List<Category> findAll() {
        CategoryExample example = new CategoryExample();
        return mapper.selectByExample(example);
    }

    @Transactional
    @Override
    public void batchDeleteById(Long[] ids) {
        for (Long id:ids){
            this.deleteCategoryById(id);
        }
    }

    @Override
    public void saveOrUpdate(Category category) {
        if (category != null) {
            if (category.getId()!=null){
                Category categoryById = mapper.selectByPrimaryKey(category.getId());
                if (categoryById!=null){
                    mapper.updateByPrimaryKey(category);
                    return;
                }
            }
            if (category.getName()!=null) {
                mapper.insert(category);
                return;
            }
        }
        throw new CustomerException("添加失败");
    }

}
