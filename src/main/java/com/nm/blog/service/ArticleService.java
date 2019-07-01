package com.nm.blog.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nm.blog.pojo.Article;
import com.nm.blog.utils.PageBean;

public interface ArticleService {
	
	/**
	 * 文章添加
	 * @param article
	 * @return
	 */
	Boolean add(Article article);
	/**
	  * 文件上传
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	String doPutFile(MultipartFile file);
	
	/**
	 * 条件查询文章列表要分页
	 * @param article
	 * @return
	 */
	PageBean<Article> articleList(Article article,Integer page);
	
	boolean delete(Integer articleId);
	
	/**
	 * 根据文章id查询文章信息
	 */
	Article getArticle(Article article);
	
	/**
	 * 文章修改
	 * @param article
	 * @return
	 */
	Boolean update(Article article);
	
	/**
     * 查询最新发布的15篇文章列表
     * @param article
     * @return
     */
    List<Article> articleNewList();
    
    /**
     * 站长推荐的10条文章记录
     * @return
     */
    List<Article> articlesterList();
    
    /**
           *统计文章总数
     * @return
     */
    Integer articleCount();
    
}
