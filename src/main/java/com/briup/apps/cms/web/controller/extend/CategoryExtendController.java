package com.briup.apps.cms.web.controller.extend;

import com.briup.apps.cms.bean.Message;
import com.briup.apps.cms.bean.extend.CategoryExtend;
import com.briup.apps.cms.dao.extend.CategoryExtendMapper;
import com.briup.apps.cms.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categoryExtend")
public class CategoryExtendController {
    @Autowired
    private CategoryExtendMapper mapper;

    @GetMapping("findAll")
    public Message findAll(){
        return MessageUtil.success("查询成功!",mapper.findAll());
    }

}
