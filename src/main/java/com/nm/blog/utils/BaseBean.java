package com.nm.blog.utils;

import java.io.Serializable;

import lombok.Data;
/**
 * 所有类的分类(分页情况下)
 * select * from table limit start,length
 * @author Administrator
 *
 */
@Data
public class BaseBean implements Serializable{
	
	private Integer start;//开始下标
	private Integer length;//查询个数
}
