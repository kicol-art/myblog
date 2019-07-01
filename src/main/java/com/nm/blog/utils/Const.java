package com.nm.blog.utils;

public class Const {
	// 每页显示3条记录
	public static final int PAGE_SIZE = 3;
	
	//标识符有效和无效(文章发布和不发布)
	public static final String MARK_YES = "1";
	public static final String MARK_NO = "-1";
	
	//站长推荐和不推荐
	public static final String RECOMMON_YES = "1";
	public static final String RECOMMON_NO = "-1";
	//图片url tomcat存放地址 用户访问地址
	public static final String FILE_URL="http://47.107.164.212:8080/file-server/upload/";
	//开发环境
//	public static final String FILE_URL="http://localhost:8080/file-server/upload/";
	
	//留言可以显示
	public static final String MESSAGE_MARK_YES="1";
	//留言不可以显示
	public static final String MESSAGE_MARK_NO="-1";
}
