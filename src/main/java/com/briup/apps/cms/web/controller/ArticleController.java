package com.briup.apps.cms.web.controller;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.Message;
import com.briup.apps.cms.service.IArticleService;
import com.briup.apps.cms.service.extend.IArticleExtendService;
import com.briup.apps.cms.utils.ExcelUtils;
import com.briup.apps.cms.utils.MessageUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {


    @Autowired
    private IArticleService service;
    @ApiOperation(value = "通过发布时间查询文章")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "主键",paramType ="query")
    )
    @GetMapping("findArticleOrderByPublishTime")
    Message findArticleOrderByPublishTime(){

        return MessageUtil.success("查找成功!",service.findArticleOrderByPublishTime());
    }

    @GetMapping("findArticleOrderByPublishtimeWithPage")
    Message findArticleOrderByPublishtimeWithPage(Integer startRow)  {
        return MessageUtil.success("查找成功!",service.findArticleOrderByPublishtimeWithPage(startRow));
    }
    @PostMapping("saveOrUpdate")
    Message saveOrUpdate(Article article){
        service.saveOrUpdate(article);
        return MessageUtil.success("添加成功!");
    }

    @Autowired
    private IArticleExtendService articleExtendService;

    @GetMapping("findArticleByAuthorId")
    Message findArticleByAuthorId(Long id){

        return MessageUtil.success("查找成功!",articleExtendService.findArticleByAuthorId(id));
    }
    @GetMapping("findArticleByCategoryId")
    Message findArticleByCategoryId(Long id){
        return MessageUtil.success("查找成功!",articleExtendService.findArticleByCategoryId(id));
    }
    @GetMapping("findCommentsByArticleId")
    public Message findCommentsByArticleId(Long id) {
        return MessageUtil.success("查找成功!",articleExtendService.findCommentsByArticleId(id));
    }

    @GetMapping("findAll")
    public Message findAll(){
        return MessageUtil.success("查询成功!",articleExtendService.findAll());
    }

    @ApiOperation(value="将文章导入到Excel中",notes="注意！测试的时候请将地址粘贴到浏览器地址栏测试",produces="application/octet-stream")
    @GetMapping("download")
    public void download(HttpServletResponse response) throws Exception{
        // 查询出所有文章信息
        String excelName = "article_list";
        String[] headList = new String[]{"编号","标题","内容"};
        String[] fieldList = new String[]{"id","title","content"};
        List<Map<String, Object>> dataList = new ArrayList<>();
        List<Article> list =service.findAll();
        for(Article a : list){
            Map<String, Object> map = new HashMap<>();
            map.put("id",a.getId());
            map.put("title",a.getTitle());
            map.put("content",a.getContent());
            dataList.add(map);
        }

        //调用工具类导出excel
        ExcelUtils.createExcel(response,excelName,headList,fieldList,dataList);

    }
}
