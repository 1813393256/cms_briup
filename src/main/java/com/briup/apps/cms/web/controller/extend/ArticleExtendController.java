package com.briup.apps.cms.web.controller.extend;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.Message;
import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.service.extend.IArticleExtendService;
import com.briup.apps.cms.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ArticleExtend")
public class ArticleExtendController {

    @Autowired
    private IArticleExtendService service;

    @GetMapping("findArticleByAuthorId")
    Message findArticleByAuthorId(Long id){

        return MessageUtil.success("查找成功!",service.findArticleByAuthorId(id));
    }
    @GetMapping("findArticleByCategoryId")
    Message findArticleByCategoryId(Long id){
        return MessageUtil.success("查找成功!",service.findArticleByCategoryId(id));
    }
    @GetMapping("findCommentsByArticleId")
    public Message findCommentsByArticleId(Long id) {
        return MessageUtil.success("查找成功!",service.findCommentsByArticleId(id));
    }

    @GetMapping("findAll")
    public Message findAll(){
        return MessageUtil.success("查询成功!",service.findAll());
    }

}
