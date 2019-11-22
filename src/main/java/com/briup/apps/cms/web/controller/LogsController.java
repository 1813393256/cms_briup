package com.briup.apps.cms.web.controller;

import com.briup.apps.cms.bean.Logs;
import com.briup.apps.cms.bean.Message;
import com.briup.apps.cms.service.ILogsService;
import com.briup.apps.cms.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogsController {

    @Autowired
    private ILogsService service;

    @GetMapping("findAll")
    Message findAll(){
        return MessageUtil.success("查找成功!",service.findAll());
    }
    @GetMapping("insertLogs")
    Message insertLogs(Logs logs){
        service.insertLogs(logs);
        return MessageUtil.success("添加成功!");
    }
    @GetMapping("updateLogsById")
    Message updateLogsById(Logs logs){
        service.updateLogsById(logs);
        return MessageUtil.success("更新成功!");
    }
    @GetMapping("deleteLogsById")
    Message deleteLogsById(Long id){
        service.deleteLogsById(id);
        return MessageUtil.success("删除成功!");
    }
    @GetMapping("findLogsById")
    Message findLogsById(Long id){
        return MessageUtil.success("查找成功!",service.findLogsById(id));
    }
}
