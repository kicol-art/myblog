package com.nm.blog.service;

import com.nm.blog.pojo.UserInfo;
import com.nm.blog.utils.PageBean;

public interface IUserService {
	/**
	 * 根据根据分页条件查询用户信息
	 * @param user 查询条件
	 * @param page 页数
	 * @return     用户列表
	 */
	PageBean<UserInfo> getList(UserInfo user,Integer page);
	
	/**
	 * 添加用户信息
	 * @param user
	 * @return
	 */
	Boolean addUser(UserInfo user);
	
	/**
	 * 根据条件查询用户信息
	 * @param user
	 * @return
	 */
	UserInfo getUser(UserInfo user);
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	Boolean updateUser(UserInfo user);
	
	/**
	  * 假删除
	 * @param user
	 * @return
	 */
	Boolean deleteUser(UserInfo user);
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	UserInfo login(UserInfo user);
	
	/**
	 * 统计人数
	 * @return
	 */
	Integer getUserCount();
}
