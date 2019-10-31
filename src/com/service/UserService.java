package com.service;

import java.util.List;


import com.pojo.User;


public interface UserService {

	//定义登录验证密码的方法
	public User login(String userCode,String userPassword);
	
	//通过userName和userRole条件查询用户的总数
	public int getUserCount_service(String userName,Integer userRole);
			
	//通过userName和userRole条件查询用户的信息并分页显示
	public List<User> getUserList_service(String userName,Integer userRole,Integer currentPageNo,Integer pageSize);
	
	//通过userCode获取用户信息
	public User getUserThisByCode_service(String userCode);
	
	//通过id查询指定用户的信息
	public User getUserThis_service(String id);
	
	//增加用户
	public int addUser_service(User user);
	
	//修改用户
	public int updateUser_service(User user,String filePath);
	
	//修改密码
	public int updatePassword_service(String id,String userPassword);
	
	//删除用户
	public int deleteUser_service(String id,String filePath);
	
}
