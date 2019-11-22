package com.briup.apps.cms.service.impl;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.ArticleExample;
import com.briup.apps.cms.dao.ArticleMapper;
import com.briup.apps.cms.service.IArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {
    @Resource
    private ArticleMapper mapper;

    @Override
    public List<Article> findArticleOrderByPublishTime() {
        ArticleExample example = new ArticleExample();
        example.setOrderByClause("publish_time desc");
        return mapper.selectByExample(example);
    }

    @Override
    public List<Article> findArticleOrderByPublishtimeWithPage(Integer startRow) {
        if (startRow == null) {
            startRow = 0;
        }
        ArticleExample example = new ArticleExample();
        example.setPageSize(5);
        example.setStartRow(startRow);
        return mapper.selectByExample(example);
    }

    @Override
    public void saveOrUpdate(Article article) {
        Article article1 = mapper.selectByPrimaryKey(article.getId());
        if (article1 == null) {
            mapper.insert(article);
        }
        mapper.updateByPrimaryKey(article);
    }

    @Override
    public List<Article> findAll() {
        ArticleExample example=new ArticleExample();
        return mapper.selectByExample(example);
    }

}
