package com.service;

import java.util.List;


import com.pojo.User;


public interface UserService {

	//�����¼��֤����ķ���
	public User login(String userCode,String userPassword);
	
	//ͨ��userName��userRole������ѯ�û�������
	public int getUserCount_service(String userName,Integer userRole);
			
	//ͨ��userName��userRole������ѯ�û�����Ϣ����ҳ��ʾ
	public List<User> getUserList_service(String userName,Integer userRole,Integer currentPageNo,Integer pageSize);
	
	//ͨ��userCode��ȡ�û���Ϣ
	public User getUserThisByCode_service(String userCode);
	
	//ͨ��id��ѯָ���û�����Ϣ
	public User getUserThis_service(String id);
	
	//�����û�
	public int addUser_service(User user);
	
	//�޸��û�
	public int updateUser_service(User user,String filePath);
	
	//�޸�����
	public int updatePassword_service(String id,String userPassword);
	
	//ɾ���û�
	public int deleteUser_service(String id,String filePath);
	
}
