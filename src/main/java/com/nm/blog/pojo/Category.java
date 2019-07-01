package com.nm.blog.pojo;

import java.io.Serializable;

public class Category implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5882436474412474798L;

	private Integer categoryId;

    private String categoryName;

    private String categoryAlias;

    private String categoryDesc;

    //统计该栏目id下文章数
    private Integer number=0;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getCategoryAlias() {
        return categoryAlias;
    }

    public void setCategoryAlias(String categoryAlias) {
        this.categoryAlias = categoryAlias == null ? null : categoryAlias.trim();
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc == null ? null : categoryDesc.trim();
    }

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryAlias="
				+ categoryAlias + ", categoryDesc=" + categoryDesc + ", number=" + number + "]";
	}
    
}