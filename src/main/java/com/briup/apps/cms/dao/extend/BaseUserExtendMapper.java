package com.briup.apps.cms.dao.extend;

import com.briup.apps.cms.bean.extend.BaseUserExtend;

import java.util.List;

public interface BaseUserExtendMapper {
    BaseUserExtend selectById(Long id);
    List<BaseUserExtend> selectAll();
}
