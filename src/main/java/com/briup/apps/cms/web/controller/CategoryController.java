package com.briup.apps.cms.web.controller;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.Message;
import com.briup.apps.cms.dao.extend.CategoryExtendMapper;
import com.briup.apps.cms.service.ICategoryService;
import com.briup.apps.cms.service.extend.ICategoryExtendService;
import com.briup.apps.cms.utils.MessageUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @ApiOperation("添加栏目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "主键",paramType = "query"),
       }
    )
    @PostMapping("insertCategory")
    public Message insertCategory(Category category) {
        service.insertCategory(category);
        return MessageUtil.success("添加成功!");
    }

    @Autowired
    private ICategoryService service;

    @PostMapping("updateCategoryById")
    public Message updateCategoryById(Category category){
        service.updateCategoryById(category);
        return MessageUtil.success("更新成功!");
    }

    @GetMapping("deleteCategoryById")
    public Message deleteCategoryById(Long id){
        service.deleteCategoryById(id);
        return MessageUtil.success("删除成功");
    }
    @GetMapping("findCategoryById")
    public Message findCategoryById(Long id){
        return MessageUtil.success("查询成功",service.findCategoryById(id));
    }

    @GetMapping("findAll")
    public Message findAll(){
        return MessageUtil.success("查询成功!",service.findAll());
    }

    @PostMapping("batchDeleteById")
    public Message batchDeleteById(Long[]ids){
        service.batchDeleteById(ids);
        return MessageUtil.success("删除成功!");
    }

    @PostMapping("saveOrUpdate")
    public Message saveOrUpdate(Category category){
        service.saveOrUpdate(category);
        return MessageUtil.success("操作成功");
    }
    @Autowired
    private ICategoryExtendService categoryExtendService;

    @GetMapping("cascadeFindAll")
    public Message cascadeFindAll(){
        return MessageUtil.success("查询成功!",categoryExtendService.findAll());
    }
}
