package com.nm.blog.controller.back;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nm.blog.pojo.UserInfo;
import com.nm.blog.service.ArticleService;
import com.nm.blog.service.IUserService;
import com.nm.blog.service.MessageService;
import com.nm.blog.service.impl.UserServiceImpl;

/**
 * 用户管理的业务逻辑
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back")
public class BackIndexController {
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ArticleService ArticleService;
	
	@Autowired
	private MessageService MessageService;
	/**
	 * 跳转登录页面
	 * 
	 * @param session
	 * @return
	 */
	@GetMapping("/login")
	public String login(HttpSession session) {
		// 登录前清空session
		session.invalidate();
		return "back/login";
	}

	/**
	 * 用户登录验证
	 * 
	 * @param user
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/login")
	public String login(UserInfo user, Model model, HttpSession session) {
		user = userService.login(user);

		// 获取登录时间
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String loginTime=sdf.format(date);
		if (user != null) {
			// 查到用户将用户信息存入session并跳转到首页
			session.setAttribute("userinfo", user);
			session.setAttribute("time", loginTime);
			// 重定向
			return "redirect:index";
		} else {
			model.addAttribute("tip", "账号或密码错误");
			return "back/login";
		}
	}

	@RequestMapping("/index")
	public String index(HttpSession session) {
		return "back/index";
	}

	@RequestMapping("/main")
	public String main(Model model, HttpServletRequest req) {
		// 通过HttpServletRequest获取用户ip
		String ip = req.getRemoteAddr();

		//文章总数 留言总数 用户总数
		Integer articleCount=ArticleService.articleCount();
		Integer messageCount=MessageService.messageCount();
		Integer userCount = userService.getUserCount();
		
		model.addAttribute("ip", ip);
		model.addAttribute("articleCount", articleCount);
		model.addAttribute("messageCount", messageCount);
		model.addAttribute("userCount", userCount);
		return "back/main";
	}
}
