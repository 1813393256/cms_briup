package com.briup.apps.cms.service;

import com.briup.apps.cms.bean.Article;

import java.util.List;

public interface IArticleService {
    List<Article> findArticleOrderByPublishTime() throws RuntimeException;
    List<Article> findArticleOrderByPublishtimeWithPage(Integer startRow) throws RuntimeException;
    void saveOrUpdate(Article article);

    List<Article> findAll();
}
