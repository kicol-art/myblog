package com.nm.blog.controller.back;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.nm.blog.pojo.Article;
import com.nm.blog.pojo.Category;
import com.nm.blog.pojo.UserInfo;
import com.nm.blog.service.ArticleService;
import com.nm.blog.service.CategoryService;
import com.nm.blog.utils.PageBean;

@Controller
@RequestMapping("/back/article")
public class ArticleController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ArticleService articleService;

	/**
	 * 查询文章信息列表和栏目列表
	 * 
	 * @param article
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Article article, Integer page, Model model) {
		PageBean<Article> pageBean = articleService.articleList(article, page);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("article", article);
		// 条件查询需要栏目
		// 栏目设置了缓存必须添加对象参数
		Category category = new Category();
		List<Category> categoryList = categoryService.categoryList(category);
		model.addAttribute("categoryList", categoryList);
		return "back/article/article_list";
	}

	/**
	 * 查询所有栏目并加载添加文章页面(在哪个栏目下添加文章)
	 * 
	 * @return
	 */
	@RequestMapping("/loadAdd")
	public String loadAdd(Model model) {
		Category category = new Category();
		List<Category> categoryList = categoryService.categoryList(category);
		model.addAttribute("categoryList", categoryList);
		return "back/article/article_add";
	}

	/**
	 * 添加文章信息
	 * 
	 * @param model
	 * @param article
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model, Article article, HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute("userinfo");
		if (user != null) {
			article.setUserId(user.getUserId());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = new Date();
			//format(date)将Date转换成指定格式的String
			//parse(string)将string转data
			//Date date=sdf.format(date);
			article.setArticleTime(date);
		}
		Boolean boo = articleService.add(article);
		if (boo) {
			model.addAttribute("tip", "文章添加成功");
		} else {
			model.addAttribute("tip", "文章添加失败");
		}
		// 添加后再次回显栏目信息
		Category category = new Category();
		List<Category> categoryList = categoryService.categoryList(category);
		model.addAttribute("categoryList", categoryList);
		return "back/article/article_add";
	}

	/**
	 * 图片上传
	 * 
	 * @param file
	 * @return
	 */
	@PostMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam MultipartFile upload) {
		String url = articleService.doPutFile(upload);
		System.out.println("图片地址：" + url);
		return url;
	}

	/**
	 * 在线文本编辑器上传图片
	 * 
	 * @param upload
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/uploadfile")
	public void uploadfile(@RequestParam MultipartFile upload, HttpServletRequest req, HttpServletResponse resp) {
		try {
			String url = articleService.doPutFile(upload);
			PrintWriter out = resp.getWriter();
			String callBack = req.getParameter("CKEditorFuncNum");
			System.out.println("callBack:" + callBack);
			out.println("<script>window.parent.CKEDITOR.tools.callFunction(" + callBack + ",'" + url + "')</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据文章编号删除文章信息
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model) {
		boolean boo = articleService.delete(id);
		if (boo) {
			model.addAttribute("tip", "文章删除成功");
		} else {
			model.addAttribute("tip", "文章删除失败");
		}
		// 中转页面
		return "back/article/article_info";
	}

	@GetMapping("/update")
	public String loadupdate(Article article, Model model) {
		// 查询所有栏目
		Category category = new Category();
		List<Category> categoryList = categoryService.categoryList(category);
		// 根据文章id查询文章信息
		article = articleService.getArticle(article);
		// 查询信息加入model
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("article", article);
		// 跳转修改页面
		return "back/article/article_update";
	}

	@PostMapping("/update")
	public String update(Model model, Article article, HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute("userinfo");
		if (user != null) {
			article.setUserId(user.getUserId());
			article.setArticleTime(new Date());
		}
		Boolean boo = articleService.update(article);
		if (boo) {
			model.addAttribute("tip", "修改成功");
		} else {
			model.addAttribute("tip", "添加失败");
		}
		// 添加后再次回显栏目信息
		Category category = new Category();
		List<Category> categoryList = categoryService.categoryList(category);
		model.addAttribute("article", article);
		model.addAttribute("categoryList", categoryList);
		return "back/article/article_update";
	}
}
