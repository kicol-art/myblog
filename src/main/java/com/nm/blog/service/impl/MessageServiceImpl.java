package com.nm.blog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nm.blog.dao.MessageMapper;
import com.nm.blog.pojo.Message;
import com.nm.blog.service.MessageService;
import com.nm.blog.utils.Const;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageMapper messageMapper;

	@Override
	public List<Message> messageList(Message message) {
		return messageMapper.messageList(message);
	}

	@Override
	public boolean update(Message message) {
		try {
			int i = messageMapper.updateByPrimaryKeySelective(message);
			if (i > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Integer messageId) {
		try {
			int count = messageMapper.deleteByPrimaryKey(messageId);
			if (count > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Message> allMessageList() {
		return messageMapper.allMessageList();	
	}

	@Override
	public boolean add(Message message) {
		try {
			if(message!=null) {
				message.setMessageMark(Const.MESSAGE_MARK_NO);//默认不显示(等我审批)
				message.setMessageTime(new Date());
			}
			int count = messageMapper.insert(message);
			if (count > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Integer messageCount() {
		//无条件统计留言总数
		Integer messageCount=messageMapper.messageCount();
		return messageCount;
	}
}
