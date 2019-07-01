package com.nm.blog.service;

import java.util.List;

import com.nm.blog.pojo.Category;
public interface CategoryService {
	List<Category> categoryList(Category category);
	Boolean addcategory(Category category);
	Category getCategory(Integer categoryId);
	Boolean updateCategory(Category category);
	/**
	 * 根据栏目编号假删除栏目及该栏目下的所有文章
	 * @param categoryId
	 */
	void delete(Integer categoryId) throws Exception;
}
