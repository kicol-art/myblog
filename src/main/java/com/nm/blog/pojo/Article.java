package com.nm.blog.pojo;

import java.io.Serializable;
import java.util.Date;

import com.nm.blog.utils.BaseBean;

public class Article extends BaseBean implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7238247107852265280L;

	private Integer articleId;

    private Integer userId;

    private Integer categoryId;

    private String articleTitle;

    private String articleContent;

    private String articleImg;

    private String articleRecom;

    private Date articleTime;

    private String articleMark;
    //查询列表返回字段
    private String categoryName;
    
    public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }

    public String getArticleImg() {
        return articleImg;
    }

    public void setArticleImg(String articleImg) {
        this.articleImg = articleImg == null ? null : articleImg.trim();
    }

    public String getArticleRecom() {
        return articleRecom;
    }

    public void setArticleRecom(String articleRecom) {
        this.articleRecom = articleRecom == null ? null : articleRecom.trim();
    }

    public Date getArticleTime() {
        return articleTime;
    }

    public void setArticleTime(Date articleTime) {
        this.articleTime = articleTime;
    }

    public String getArticleMark() {
        return articleMark;
    }

    public void setArticleMark(String articleMark) {
        this.articleMark = articleMark == null ? null : articleMark.trim();
    }
}