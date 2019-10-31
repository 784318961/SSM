package com.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.user.UserMapper;
import com.pojo.User;
import com.service.UserService;
@Transactional
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	//定义登录验证密码的方法
	@Override
	public User login(String userCode, String userPassword) {
		User user=null;
		user=userMapper.getUser(userCode);
		if(user!=null){
			if(!user.getUserPassword().equals(userPassword)){
				user=null;
			}
		}
		return user;
	}

	//通过userName和userRole条件查询用户的总数
	@Override
	public int getUserCount_service(String userName, Integer userRole) {
		
		return userMapper.getUserCount_dao(userName, userRole);
	}

	//通过userName和userRole条件查询用户的信息并分页显示
	@Override
	public List<User> getUserList_service(String userName, Integer userRole,
			Integer currentPageNo, Integer pageSize) {
		currentPageNo=(currentPageNo-1)*pageSize;
		return userMapper.getUserList_dao(userName, userRole, currentPageNo, pageSize);
	}

	//通过userCode获取用户信息
	@Override
	public User getUserThisByCode_service(String userCode) {
		
		return userMapper.getUserThisByCode_dao(userCode);
	}

	//通过id查询指定用户的信息
	@Override
	public User getUserThis_service(String id) {
		
		return userMapper.getUserThis_dao(id);
	}

	//增加用户
	@Override
	public int addUser_service(User user) {
		
		return userMapper.addUser_dao(user);
	}

	//修改用户
	@Override
	public int updateUser_service(User user,String filePath) {
		//先删除各种附件
		User oldUser=userMapper.getUserThis_dao(user.getId().toString());
		boolean flag1=true;
		boolean flag2=true;
		//删除服务器上的个人证件照
		if(oldUser.getIdPicPath()!=null && !oldUser.getIdPicPath().equals("")){
			File file=new File(filePath+oldUser.getIdPicPath());
			if(file.exists()){
				flag1=file.delete();
			}
		}
		//删除服务器上的工作照
		if(oldUser.getWorkPicPath()!=null && !oldUser.getWorkPicPath().equals("")){
			File file=new File(filePath+oldUser.getWorkPicPath());
			if(file.exists()){
				flag2=file.delete();
			}
		}
		
		if(flag1==true && flag2==true){
			return userMapper.updateUser_dao(user);
		}
		return 0;
		
	}

	//修改密码
	@Override
	public int updatePassword_service(String id, String userPassword) {
		
		return userMapper.updatePassword_dao(id, userPassword);
	}

	//删除用户
	@Override
	public int deleteUser_service(String id,String filePath) {
		//先删除各种附件
		User user=userMapper.getUserThis_dao(id);
		boolean flag1=true;
		boolean flag2=true;
		//删除服务器上的个人证件照
		if(user.getIdPicPath()!=null && !user.getIdPicPath().equals("")){
			File file=new File(filePath+user.getIdPicPath());
			if(file.exists()){
				flag1=file.delete();
			}
		}
		//删除服务器上的工作照
		if(user.getWorkPicPath()!=null && !user.getWorkPicPath().equals("")){
			File file=new File(filePath+user.getWorkPicPath());
			if(file.exists()){
				flag2=file.delete();
			}
		}
		
		if(flag1==true && flag2==true){
			return userMapper.deleteUser_dao(id);
		}
		return 0;
	}
	
	
	
}
