package com.nm.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nm.blog.pojo.Article;
@Mapper
public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);
    
    /**
     * 根据栏目编号删除该栏目下所有文章
     * @param categoryId
     * @return
     */
    int deleteByCategoryId(Integer categoryId);
    
    /**
     * 条件查询文章列表
     * @param article
     * @return
     */
    List<Article> articleList(Article article);
    
    /**
     * 条件统计文章总数
     * @param article
     * @return
     */
    Long count(Article article);
    
    /**
     * 根据文章编号删除文章
     * @param articleId
     * @return
     */
    boolean delete(Integer articleId);
    
	/**
	 * 统计文章总数
	 * 
	 * @return
	 */
	Integer articleCount();
}