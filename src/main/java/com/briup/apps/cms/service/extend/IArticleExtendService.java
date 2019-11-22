package com.briup.apps.cms.service.extend;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.bean.extend.ArticleExtend;

import java.util.List;

public interface IArticleExtendService {
    List<ArticleExtend>findArticleByAuthorId(Long id);
    List<ArticleExtend>findArticleByCategoryId(Long id);
    List<ArticleExtend> findCommentsByArticleId(Long id);
    List<ArticleExtend>findAll();
}
