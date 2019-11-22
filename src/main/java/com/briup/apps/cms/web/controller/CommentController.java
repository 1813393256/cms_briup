package com.briup.apps.cms.web.controller;

import com.briup.apps.cms.bean.Comment;
import com.briup.apps.cms.bean.Message;
import com.briup.apps.cms.service.ICommentService;
import com.briup.apps.cms.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService service;

    @GetMapping("updateById")
    public Message updateById(Comment comment){
        service.updateById(comment);
        return MessageUtil.success("更新成功!");
    }
    @GetMapping("insertComment")
    public Message insertComment(Comment comment){
        service.insertComment(comment);
        return MessageUtil.success("插入成功!");
    }
    @GetMapping("deleteCommentById")
    public Message deleteCommentById(Long id){
        service.deleteCommentById(id);
        return MessageUtil.success("删除成功!");
    }
    @GetMapping("findCommentById")
    public Message findCommentById(Long id){
       return MessageUtil.success("查找成功!",service.findCommentById(id));
    }
}
