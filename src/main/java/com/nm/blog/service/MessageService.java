package com.nm.blog.service;
/**
 * 留言
 * @author Administrator
 *
*/

import java.util.List;

import com.nm.blog.pojo.Message;

public interface MessageService {
	
	/**
     * 查询可以发表所有留言信息
     * @param message
     * @return
     */
    List<Message> allMessageList();
    
	/**
	 * 条件查询留言信息
	 * 
	 * @param message
	 * @return
	 */
	List<Message> messageList(Message message);
	/**
	 * 修改留言显示状态
	 * @param message
	 * @return
	 */
	boolean update(Message message);
	/**
	 * 真删除留言信息
	 * @param articleId
	 * @return
	 */
	boolean delete(Integer messageId);
	
	/**
	 * 添加留言信息
	 * @param message
	 * @return
	 */
	boolean add(Message message);
	
	/**
	 * 统计留言总数
	 * @return
	 */
	Integer messageCount();
}
