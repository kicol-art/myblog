package com.nm.blog.controller.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nm.blog.pojo.UserInfo;
import com.nm.blog.service.IUserService;
import com.nm.blog.utils.PageBean;

@Controller
@RequestMapping("/back/user")
public class UserController {
	@Autowired
	private IUserService userService;

	/**
	 * 根据分页查询用户信息
	 * 
	 * @param user
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(UserInfo user, Integer page, Model model) {
		PageBean<UserInfo> pageBean = userService.getList(user, page);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("user", user);
		return "back/userinfo/userinfo_list";
	}

	/**
	 * 跳转到添加用户页面
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping("/loadAdd")
	public String loadAdd() {
		return "/back/userinfo/userinfo_add";
	}

	/**
	 * 添加用户信息
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(UserInfo user, Model model) {
		Boolean boo = userService.addUser(user);
		if (boo) {
			model.addAttribute("tip", "用户添加成功");
		} else {
			model.addAttribute("tip", "用户添加失败");

		}
		return "back/userinfo/userinfo_add";
	}

	/**
	  * 根据userId查询用户并跳转到修改用户信息页面
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@GetMapping("/loadUpdate")
	public String loadUpdate(UserInfo user, Model model) {
		user=userService.getUser(user);
		model.addAttribute("user", user);
		return "back/userinfo/userinfo_update";
	}
	
	@PostMapping("/update")
	public String update(UserInfo user,Model model) {
		Boolean boo=userService.updateUser(user);
		if(boo) {
			model.addAttribute("tip", "修改成功");
		}else {
			model.addAttribute("tip", "修改失败");
		}
		model.addAttribute("user", user);
		return "back/userinfo/userinfo_update";
	}
	
	@RequestMapping("/delete")
	public String delete(UserInfo user) {
		Boolean boo=userService.deleteUser(user);
		return "redirect:/back/user/list?page=1";
	}
}
