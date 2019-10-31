package com.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pojo.User;

public interface UserMapper {

	//通过userCode查询指定用户的信息
	public User getUser(@Param("userCode") String userCode);
	
	//通过userName和userRole条件查询用户的总数
	public int getUserCount_dao(@Param("userName") String userName,@Param("userRole") Integer userRole);
		
	//通过userName和userRole条件查询用户的信息并分页显示
	public List<User> getUserList_dao(@Param("userName") String userName,@Param("userRole") Integer userRole,@Param("currentPageNo") Integer currentPageNo,@Param("pageSize") Integer pageSize);
	
	//通过userCode获取用户信息
	public User getUserThisByCode_dao(@Param("userCode") String userCode);
	
	//通过id查询指定用户的信息
	public User getUserThis_dao(@Param("id") String id);
	
	//增加用户
	public int addUser_dao(User user);
	
	//修改用户
	public int updateUser_dao(User user);
	
	//修改密码
	public int updatePassword_dao(@Param("id") String id,@Param("userPassword") String userPassword);
	
	//删除用户
	public int deleteUser_dao(@Param("id") String id);
	
	
}
