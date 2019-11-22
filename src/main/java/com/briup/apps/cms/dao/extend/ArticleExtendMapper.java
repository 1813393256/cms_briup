package com.briup.apps.cms.dao.extend;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleExtendMapper {
    List<ArticleExtend>findArticleByAuthorId(Long id);
    List<ArticleExtend>findArticleByCategoryId(Long id);
    List<ArticleExtend> findCommentsByArticleId(Long id);
    List<ArticleExtend>findAll();
}
