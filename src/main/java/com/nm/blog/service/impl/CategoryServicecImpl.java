package com.nm.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nm.blog.dao.ArticleMapper;
import com.nm.blog.dao.CategoryMapper;
import com.nm.blog.pojo.Category;
import com.nm.blog.service.CategoryService;

import freemarker.core.ReturnInstruction.Return;

@Service
public class CategoryServicecImpl implements CategoryService {
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private ArticleMapper articleMapper;
	
	@Override
	@Cacheable(cacheNames="categoryCache",key="#p0")
	public List<Category> categoryList(Category category) {
		List<Category> categoryList = categoryMapper.categoryList();
		return categoryList;
	}

	@Override
	@CacheEvict(cacheNames="categoryCache",allEntries=true)
	public Boolean addcategory(Category category) {
		try {
			int i=categoryMapper.insert(category);
			if(i>0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.getMessage();
		}
		return false;
	}

	@Override
	@Cacheable(cacheNames="categoryCache",key="#p0")
	public Category getCategory(Integer categoryId) {
	try {
		Category category=categoryMapper.selectByPrimaryKey(categoryId);
		return category;
	} catch (Exception e) {
		e.getStackTrace();
	}
	return null;
	}

	@Override
	@CacheEvict(cacheNames="categoryCache",allEntries=true)
	public Boolean updateCategory(Category category) {
		try {
		  int i=categoryMapper.updateByPrimaryKeySelective(category);
		  if(i>0) {
			  return true;
		  }
			return false;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
		}

	@Transactional
	@CacheEvict(cacheNames="categoryCache",allEntries=true)
	public void delete(Integer categoryId) throws Exception{
		//先删除文章信息再删除栏目信息(涉及外建)
		articleMapper.deleteByCategoryId(categoryId);
		categoryMapper.deleteByPrimaryKey(categoryId);
	}
}
