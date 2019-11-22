package com.briup.apps.cms.service.impl;

import com.briup.apps.cms.bean.Comment;
import com.briup.apps.cms.dao.CommentMapper;
import com.briup.apps.cms.service.ICommentService;
import com.briup.apps.cms.utils.CustomerException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommentServiceImpl implements ICommentService {
    @Resource
    private CommentMapper mapper;

    @Override
    public void insertComment(Comment comment) {
        if (comment != null) {
            if (comment.getContent()!=null){
                mapper.insert(comment);
                return;
            }
        }
        throw new CustomerException("插入失败!");
    }

    @Override
    public void deleteCommentById(Long id) {
        mapper.deleteByPrimaryKey(id);
        return;
    }

    @Override
    public void updateById(Comment comment) {
        mapper.updateByPrimaryKey(comment);
        return;
    }

    @Override
    public Comment findCommentById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

}
