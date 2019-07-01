package com.nm.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nm.blog.pojo.UserInfo;
/**
 * 用户数据访问接口
 * @author Administrator
 *
 */
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    //根据用户信息查询用户列表
    List<UserInfo> getUserList(UserInfo user);
    //根据用户信息查询用户数量
    Long getUserCount(UserInfo user);
    
    //根据用户账号和密码查询用户信息(登录验证)
    UserInfo login(UserInfo user);
}