package com.briup.apps.cms.service;

import com.briup.apps.cms.bean.Category;

import java.util.List;
import java.util.Map;

public interface ICategoryService {
    void insertCategory(Category category);
    void updateCategoryById(Category category);
    void deleteCategoryById(Long id);
    Category findCategoryById(Long id);
    List<Category> findAll();
    void batchDeleteById(Long[]ids);
    void saveOrUpdate(Category category);
}
