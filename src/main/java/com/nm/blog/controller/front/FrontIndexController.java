package com.nm.blog.controller.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nm.blog.dao.ArticleBeanRepository;
import com.nm.blog.pojo.Article;
import com.nm.blog.pojo.ArticleBean;
import com.nm.blog.pojo.Category;
import com.nm.blog.pojo.Message;
import com.nm.blog.service.ArticleService;
import com.nm.blog.service.CategoryService;
import com.nm.blog.service.MessageService;
import com.nm.blog.utils.PageBean;

/**
 * 前台展示控制器
 * 
 * @author Administrator
 *
 */
@Controller
public class FrontIndexController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private ArticleBeanRepository er;
	
	/**
	 * 全文检索
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping("/es")
	public String getList(String keywords,Model model) {
		System.out.println("查询条件:"+keywords);
		//检索5条记录
		Page<ArticleBean> pages=er.findDistinctArticleBeanByArticleTitleOrArticleContent(keywords, keywords, PageRequest.of(0, 5));
		List<ArticleBean> esList= pages.getContent();
		for (ArticleBean articleBean : esList) {
			System.out.println("es:"+articleBean.toString());
		}
		//初始化
		init(model);
		//存model 没分页啊老铁
		model.addAttribute("esList", esList);
		return "es";
	}
	
	/**
	 * 提取公共方法
	 * 查询所有栏目信息|查询站长推荐的5篇文章
	 * @param model
	 */
	public void init(Model model) {
		// 查询所有栏目信息
		Category category = new Category();
		List<Category> categoryList = categoryService.categoryList(category);

		// 查询站长推荐的5篇文章
		List<Article> articlesterList = articleService.articlesterList();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("articlesterList", articlesterList);
	}

	/**
	 * 首页展示信息
	 * 把该controller及前台html页面中的/index改成了/
	 * @return
	 */
	@RequestMapping("/")
	public String index(Model model) {
		init(model);
		// 查询最新发布的15篇文章信息
		List<Article> newArticles = articleService.articleNewList();
		model.addAttribute("newArticles", newArticles);
		return "index";
	}

	/**
	 * 根据文章编号查看文章详情
	 * 查询所有栏目信息|站长推荐的5篇文章
	 * @param id
	 * @return
	 */
	@RequestMapping("/listView/{id}")
	public String ListView(@PathVariable("id") Integer id, Model model) {
		init(model);
		Article article=new Article();
		article.setArticleId(id);
		article=articleService.getArticle(article);
		model.addAttribute("article", article);
		return "listview";
	}

	/**
	 * 根据栏目查询文章列表
	 * @param article
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String listByCategoryId(Article article,Model model,Integer page) {
		init(model);
		System.out.println(article.getCategoryName());
		PageBean<Article> pageBean=articleService.articleList(article, page);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("article", article);
		
		return "list";
	}
	
	/**
	 * 查询可以发布的留言信息
	 * @return
	 */
	@RequestMapping("/message")
	public String message(Model model) {
		init(model);
		
		List<Message> allmessages=messageService.allMessageList();
		model.addAttribute("melist", allmessages);
		return "message";
	}
	
	@RequestMapping("/addMessage")
	public String addMessage(Message message,Model model) {
		
		boolean boo=messageService.add(message);
		if(boo) {
			model.addAttribute("tip", "留言添加成功,博主正在玩命审核");
		}else {
			model.addAttribute("tip", "留言添加失败,博主说你长的挫");
		}
		//不管添加成功与否都要查询所有留言
		List<Message> allmessages=messageService.allMessageList();
		model.addAttribute("melist", allmessages);
	
		return "/message_info";
	}
	
}
