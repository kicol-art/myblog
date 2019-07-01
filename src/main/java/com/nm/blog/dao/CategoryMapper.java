package com.nm.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nm.blog.pojo.Category;
@Mapper
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    
    /**
           * 查询所有栏目信息
     * @return
     */
    List<Category> categoryList();
}