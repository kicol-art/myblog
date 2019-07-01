package com.nm.blog.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nm.blog.dao.ArticleBeanRepository;
import com.nm.blog.dao.ArticleMapper;
import com.nm.blog.pojo.Article;
import com.nm.blog.pojo.ArticleBean;
import com.nm.blog.service.ArticleService;
import com.nm.blog.utils.Const;
import com.nm.blog.utils.PageBean;
import com.nm.blog.utils.PageUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private ArticleBeanRepository er;

	@Override
	public Boolean add(Article article) {
		try {
			
			int i = articleMapper.insertSelective(article);
			
			//elasticsearch添加操作
			//将属性信息赋值到自定义er对象中并保存到dao层接口中
			ArticleBean aib=new ArticleBean();
			aib.setArticleId(article.getArticleId());
			aib.setArticleTitle(article.getArticleTitle());
			aib.setArticleContent(article.getArticleContent());
			aib.setArticleImg(article.getArticleImg());
			aib.setArticleTime(article.getArticleTime());
			er.save(aib);
			
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
	public String doPutFile(MultipartFile file) {
		try {
			// 获取图片名称
			String filename = file.getOriginalFilename();
			// 获取当前时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyMMddhhmmss");
			String time = sdf.format(new Date());
			// 拼接文件URL
			String url = Const.FILE_URL + time + filename;
			// 使用jersey客户端
			Client client = new Client();
			WebResource resource = client.resource(url);
			byte[] buf = file.getBytes();
			// put发送
			resource.put(String.class, buf);
			// 返回
			System.out.println("service层上传URL:" + url);
			return url;
		} catch (Exception e) {
			log.error("service层文件上传异常");
			return("你猜");
		}
	}

	@Override
	public PageBean<Article> articleList(Article article, Integer page) {
		// 获取总记录数
		int rows = articleMapper.count(article).intValue();
		// 总页数
		int totalPage = PageUtil.countTotalPage(rows, Const.PAGE_SIZE);
		// 当前第几页
		int currentPage = PageUtil.countCurrentPage(page);
		// 起始记录数
		int start = PageUtil.countStart(Const.PAGE_SIZE, currentPage);

		if (page >= 0) {
			article.setStart(start);
			article.setLength(Const.PAGE_SIZE);
		} else {
			article.setStart(-1);
			article.setLength(-1);
		}

		List<Article> articleList = articleMapper.articleList(article);

		PageBean<Article> pageBean = new PageBean<>();
		pageBean.setAllRow(rows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(articleList);
		return pageBean;
	}

	@Override
	public boolean delete(Integer articleId) {
		try {
			int count = articleMapper.deleteByPrimaryKey(articleId);
			
			//elasticsearch删除操作
			//将属性信息赋值到自定义er对象中并保存到dao层接口中
			ArticleBean aib=new ArticleBean();
			aib.setArticleId(articleId);
			er.delete(aib);
			
			if (count > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Article getArticle(Article article) {
		try {
			return articleMapper.selectByPrimaryKey(article.getArticleId());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean update(Article article) {
		try {
			int i = articleMapper.updateByPrimaryKeySelective(article);
			
			//elasticsearch修改操作
			//将属性信息赋值到自定义er对象中并保存到dao层接口中
			ArticleBean aib=new ArticleBean();
			aib.setArticleId(article.getArticleId());
			aib.setArticleTitle(article.getArticleTitle());
			aib.setArticleContent(article.getArticleContent());
			aib.setArticleImg(article.getArticleImg());
			aib.setArticleTime(article.getArticleTime());
			er.save(aib);
			
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
	public List<Article> articleNewList() {
		// 设置查询文章相关参数
		Article article = new Article();
		article.setArticleMark(Const.MARK_YES);// 发布
		article.setStart(0);
		article.setLength(15);
		List<Article> newArticleList = articleMapper.articleList(article);
		return newArticleList;
	}

	@Override
	public List<Article> articlesterList() {
		// 设置查询文章相关参数
		Article article = new Article();
		article.setArticleMark(Const.MARK_YES);// 可以发布
		article.setArticleRecom(Const.RECOMMON_YES);// 站长推荐
		article.setStart(0);
		article.setLength(5);// 推荐五篇文章
		List<Article> articleList = articleMapper.articleList(article);
		return articleList;
	}

	@Override
	public Integer articleCount() {
		//无条件统计文章总数
		Integer articleCount=articleMapper.articleCount();
		return articleCount;
	}
}
