package com.nm.blog.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import com.nm.blog.pojo.ArticleBean;

@Component
public interface ArticleBeanRepository extends ElasticsearchRepository<ArticleBean, Integer> {

	Page<ArticleBean> findDistinctArticleBeanByArticleTitleOrArticleContent(String articleTitle,
			String articleContent, Pageable page);

}