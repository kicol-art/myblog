package com.nm.blog.controller.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nm.blog.pojo.Message;
import com.nm.blog.service.MessageService;

/**
 * 留言管理的控制器
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/message")
public class MessageController {
	@Autowired
	private MessageService messageService;

	@RequestMapping("/list")
	public String list(Message message,Model model) {
		List<Message> messageList=messageService.messageList(message);
		model.addAttribute("messageList", messageList);
		model.addAttribute("message", message);
		return "back/message/message_list";
	}

	@RequestMapping("/update")
	public String add(Message message,Model model) {
		//修改显示状态
		messageService.update(message);
		List<Message> messageList=messageService.messageList(message);
		model.addAttribute("messageList", messageList);
		return "back/message/message_list";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id,Model model) {
		boolean boo=messageService.delete(id);
		if(boo) {
			model.addAttribute("tip", "留言删除成功");
		}else {
			model.addAttribute("tip", "留言删除失败");
		}
		//弹窗中转页面
		return "back/message/message_info";
	}
}