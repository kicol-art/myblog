package com.nm.blog.pojo;


import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
//indexName索引名称 可以理解为数据库名 必须为小写 不然会报异常
//type类型 shards分片数量
@Document(indexName = "myblog", type = "ArticleBean", shards = 1, replicas = 0, refreshInterval = "-1")
public class ArticleBean implements Serializable{
	private static final long serialVersionUID = -7511307240420030928L;
	
	@Id
	private Integer articleId;
	
	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String articleTitle;
    
	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String articleContent;
	
	@Field(type=FieldType.Keyword, index=false)
	private String articleImg;//搜索需要展示图片
	
	@Field(type = FieldType.Date, index=false)
	private Date articleTime;
	
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	public String getArticleImg() {
		return articleImg;
	}
	public void setArticleImg(String articleImg) {
		this.articleImg = articleImg;
	}
	public Date getArticleTime() {
		return articleTime;
	}
	public void setArticleTime(Date articleTime) {
		this.articleTime = articleTime;
	}
	public ArticleBean() {
		
	}
	public ArticleBean(Integer articleId, String articleTitle, String articleContent, String articleImg,
			Date articleTime) {
		super();
		this.articleId = articleId;
		this.articleTitle = articleTitle;
		this.articleContent = articleContent;
		this.articleImg = articleImg;
		this.articleTime = articleTime;
	}
	@Override
	public String toString() {
		return "ArticleBean [articleId=" + articleId + ", articleTitle=" + articleTitle + ", articleContent="
				+ articleContent + ", articleImg=" + articleImg + ", articleTime=" + articleTime + "]";
	}
		
}