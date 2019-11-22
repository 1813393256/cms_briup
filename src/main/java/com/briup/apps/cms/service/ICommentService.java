package com.briup.apps.cms.service;

import com.briup.apps.cms.bean.Comment;

import java.util.List;

public interface ICommentService {
    void insertComment(Comment comment);
    void deleteCommentById(Long id);
    void updateById(Comment comment);
    Comment findCommentById(Long id);
}
