package com.briup.apps.cms.service.extend.impl;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.extend.CategoryExtend;
import com.briup.apps.cms.dao.extend.CategoryExtendMapper;
import com.briup.apps.cms.service.ICategoryService;
import com.briup.apps.cms.service.extend.ICategoryExtendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryExtendServiceImpl implements ICategoryExtendService {
    @Resource
    private CategoryExtendMapper mapper;
    @Override
    public List<CategoryExtend> findAll() {
        return mapper.findAll();
    }
}
