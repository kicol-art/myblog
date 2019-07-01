package com.nm.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nm.blog.pojo.Message;

@Mapper
public interface MessageMapper {
	int deleteByPrimaryKey(Integer messageId);

	int insert(Message record);

	int insertSelective(Message record);

	Message selectByPrimaryKey(Integer messageId);

	int updateByPrimaryKeySelective(Message record);

	int updateByPrimaryKey(Message record);

	/**
	 * 条件查询留言列表
	 * 
	 * @param message
	 * @return
	 */
	List<Message> messageList(Message message);

	/**
	 * 查询可以发表所有留言信息
	 * 
	 * @param message
	 * @return
	 */
	List<Message> allMessageList();

	/**
	 * 统计留言总数
	 * 
	 * @return
	 */
	Integer messageCount();
}