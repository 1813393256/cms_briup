package com.briup.apps.cms.bean.extend;

import com.briup.apps.cms.bean.Category;

import java.util.List;

public class CategoryExtend extends Category{
    private Long id;
    private String name;
    private String description;
    private Long no;
    private Long parentId;
    private List<Category> categorys;

    public List<Category> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<Category> categorys) {
        this.categorys = categorys;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Long getNo() {
        return no;
    }
    public void setNo(Long no) {
        this.no = no;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}
