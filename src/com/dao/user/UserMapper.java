package com.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pojo.User;

public interface UserMapper {

	//ͨ��userCode��ѯָ���û�����Ϣ
	public User getUser(@Param("userCode") String userCode);
	
	//ͨ��userName��userRole������ѯ�û�������
	public int getUserCount_dao(@Param("userName") String userName,@Param("userRole") Integer userRole);
		
	//ͨ��userName��userRole������ѯ�û�����Ϣ����ҳ��ʾ
	public List<User> getUserList_dao(@Param("userName") String userName,@Param("userRole") Integer userRole,@Param("currentPageNo") Integer currentPageNo,@Param("pageSize") Integer pageSize);
	
	//ͨ��userCode��ȡ�û���Ϣ
	public User getUserThisByCode_dao(@Param("userCode") String userCode);
	
	//ͨ��id��ѯָ���û�����Ϣ
	public User getUserThis_dao(@Param("id") String id);
	
	//�����û�
	public int addUser_dao(User user);
	
	//�޸��û�
	public int updateUser_dao(User user);
	
	//�޸�����
	public int updatePassword_dao(@Param("id") String id,@Param("userPassword") String userPassword);
	
	//ɾ���û�
	public int deleteUser_dao(@Param("id") String id);
	
	
}
