package com.nm.blog.es;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import com.nm.blog.dao.ArticleBeanRepository;
import com.nm.blog.pojo.ArticleBean;

/**
 * 创建索引库(查询需要索引) 对应数据库
 * @author Administrator
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsBlog {
 @Autowired
 private ElasticsearchTemplate template;
 
 @Autowired
 private ArticleBeanRepository abRep;
 
 /**
  * 创建索引需要先在bean通过jpa定义字段索引类型
  */
// @Test
// public void createIndex() {
//	 //创建索引库
//	 template.createIndex(ArticleBean.class);
//	 //创建映射关系
//	 template.putMapping(ArticleBean.class);
// }
/**
 * 向索引库添内容
 */
//@Test
//public void addContent() {
//    ArticleBean articleBean = new ArticleBean(46,"毛神","看庭前花开花落，云潮云涌","http://localhost:8080/file-server/upload/20181124100906timg.jpg",null);
//    abRep.save(articleBean);
//}
 @Test
 public void testFind(){
     // 查询
     Iterable<ArticleBean> articleBean = this.abRep.findDistinctArticleBeanByArticleTitleOrArticleContent("毛", "毛", PageRequest.of(0, 5));
     articleBean.forEach(article-> System.out.println(article.getArticleTitle()+"\t"+article.getArticleContent()));
 }
 
 /**
  * 分页查询
  */
// @Test
// public void queryEs() {
//	 String name="鸡巴";
//	 Page<ArticleBean> pages=abRep.findDistinctArticleBeanByArticleTitleContainingOrArticleContentContaining(name,name,PageRequest.of(0, 5));
//	 List<ArticleBean> esList= pages.getContent();
//		for (ArticleBean articleBean : esList) {
//			System.out.println("搜索结果："+articleBean.toString());
//		}
// }
 
//@Test
//public void test1(String keywords,int page,int size,String startTime,String endTime) {
//	 //1.构建查询条件
//	 NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
//	 //2.1 添加基本的分词查询(应该可以简写)
//	 queryBuilder.withQuery(QueryBuilders.matchQuery("fileName", keywords));
//	 queryBuilder.withQuery(QueryBuilders.matchQuery("fileContent", keywords));
//	 queryBuilder.withQuery(QueryBuilders.termQuery("personnel", keywords));
//	 queryBuilder.withQuery(QueryBuilders.matchQuery("address", keywords));
//	 queryBuilder.withQuery(QueryBuilders.matchQuery("details", keywords));
//	 //2.2对日期进行范围查询
//	 RangeQueryBuilder rangequerybuilder = QueryBuilders .rangeQuery("日期字段") .from("startTime").to("endtTime");
//	 //2.3根据日期排序 
//	 queryBuilder.withSort(SortBuilders.fieldSort("date").order(SortOrder.DESC));	 
//	 //2.4准备分页(0为第一页)
//     queryBuilder.withPageable(PageRequest.of(page - 1, size));
//     //3.执行搜索，获取结果
//     Page<实体类> dang = this.danganRepository.search(queryBuilder.build());
//     
//     // 打印总条数
//     System.out.println(dang.getTotalElements());
//     // 打印总页数
//     System.out.println(dang.getTotalPages());
//     // 每页大小
//     System.out.println(dang.getSize());
//     // 当前页
//     System.out.println(dang.getNumber());
//     dang.forEach(System.out::println);
//}
}
