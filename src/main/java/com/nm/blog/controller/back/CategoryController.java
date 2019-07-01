package com.nm.blog.controller.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nm.blog.pojo.Category;
import com.nm.blog.service.CategoryService;

/**
 * 栏目管理的控制器
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	/**
	 * 查询所有栏目信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		//配置缓存要传个对象参数
		Category category=new Category();
		List<Category> categoryList = categoryService.categoryList(category);
		model.addAttribute("categoryList", categoryList);
		return "back/category/category";
	}

	@RequestMapping("/add")
	public String add(Category category, Model model) {
		Boolean boo = categoryService.addcategory(category);
		if (boo) {
			model.addAttribute("tip", "栏目添加成功");
		} else {
			model.addAttribute("tip", "栏目添加失败");
		}
		model.addAttribute("category", category);
		return "back/category/category_return";
	}

	/**
	 * 根据编号查询栏目信息
	 * @param categoryId
	 * @param model
	 * @return
	 */
	@GetMapping("/get/{id}")
	public String loadUpdate(@PathVariable("id")Integer categoryId,Model model) {
		Category category=categoryService.getCategory(categoryId);
		model.addAttribute("category", category);
		return "back/category/category_update";
	}
	
	@RequestMapping("/update")
	public String update(Category category,Model model) {
		Boolean boo=categoryService.updateCategory(category);
		if(boo) {
			model.addAttribute("tip", "栏目修改成功");
		}else {
			model.addAttribute("tip", "栏目修改失败");
		}
		model.addAttribute("category", category);
		return "back/category/category_update";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id")Integer categoryId,Model model) {
		try {
			categoryService.delete(categoryId);
			model.addAttribute("tip", "栏目删除成功");
		} catch (Exception e) {
			model.addAttribute("tip", "栏目删除失败");
		}
		return "back/category/category_return";
	}
	

}
