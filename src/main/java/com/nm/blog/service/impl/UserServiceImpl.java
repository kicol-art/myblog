package com.nm.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nm.blog.dao.UserMapper;
import com.nm.blog.pojo.UserInfo;
import com.nm.blog.service.IUserService;
import com.nm.blog.utils.Const;
import com.nm.blog.utils.PageBean;
import com.nm.blog.utils.PageUtil;
@Service
public class UserServiceImpl implements IUserService{
 @Autowired
 private UserMapper userMapper;
 
	@Override
	public PageBean<UserInfo> getList(UserInfo user, Integer page) {
		//获取总记录数
		int rows=userMapper.getUserCount(user).intValue();
		//总页数
		int totalPage=PageUtil.countTotalPage(rows, Const.PAGE_SIZE);
		//当前第几页
		int currentPage = PageUtil.countCurrentPage(page);
		//起始记录数
		int start = PageUtil.countStart(Const.PAGE_SIZE, currentPage);
		
		if(page>=0) {
			user.setStart(start);
			user.setLength(Const.PAGE_SIZE);
		}else {
			user.setStart(-1);
			user.setLength(-1);
		}
		
		List<UserInfo> users = userMapper.getUserList(user);
		
		PageBean<UserInfo> pageBean = new PageBean<>();
		pageBean.setAllRow(rows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(users);
		return pageBean;
	}

	@Override
	public Boolean addUser(UserInfo user) {
		if(user!=null) {
			user.setUserMark(Const.MARK_YES);
		}
		int i=userMapper.insertSelective(user);
		if(i>0) {
			return true;
		}
		return false;
	}

	@Override
	public UserInfo getUser(UserInfo user) {
		if(user.getUserId()!=null) {
			return userMapper.selectByPrimaryKey(user.getUserId());
		}
		return null;
	}

	@Override
	public Boolean updateUser(UserInfo user) {
		if(user!=null) {
			user.setUserMark(Const.MARK_YES);
		}
		int i=userMapper.updateByPrimaryKeySelective(user);
		if(i>0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteUser(UserInfo user) {
		if(user!=null) {
			user.setUserMark(Const.MARK_NO);
		}
		int i=userMapper.updateByPrimaryKeySelective(user);
		if(i>0) {
			return true;
		}
		return false;
	}

	@Override
	public UserInfo login(UserInfo user) {
		try {
			return userMapper.login(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer getUserCount() {
		Integer userCount=userMapper.getUserCount(null).intValue();
		return userCount;
	}
}

