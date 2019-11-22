package com.briup.apps.cms.web.controller;

import com.briup.apps.cms.bean.Test;
import com.briup.apps.cms.service.ITService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private ITService service;

    @GetMapping(value = "/findAll")
    public List test(){
        List<Test>list=service.findAll();
        return list;
    }
}
