package com.briup.apps.cms.service.extend.impl;

import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.dao.extend.ArticleExtendMapper;
import com.briup.apps.cms.service.extend.IArticleExtendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleExtendServiceImpl implements IArticleExtendService {

    @Resource
    private ArticleExtendMapper mapper;

    @Override
    public List<ArticleExtend> findArticleByAuthorId(Long id) {

        return mapper.findArticleByAuthorId(id);
    }

    @Override
    public List<ArticleExtend> findArticleByCategoryId(Long id) {

        return mapper.findArticleByCategoryId(id);
    }

    @Override
    public List<ArticleExtend> findCommentsByArticleId(Long id) {
        return mapper.findCommentsByArticleId(id);
    }

    @Override
    public List<ArticleExtend> findAll() {
        return mapper.findAll();
    }
}
