package com.service;

import java.util.List;


import com.pojo.Role;

public interface RoleService {

	//��ѯ��ɫ��ȫ����Ϣ
	public List<Role> getRole();
	
	//ͨ��id��ѯ��ɫ��Ϣ
	public Role getRoleThis_service(String id);
	
	//���ӽ�ɫ����Ϣ
	public int addRole_service(Role role);
	
	//�޸Ľ�ɫ����Ϣ
	public int updateRole_service(Role role);
	
	//ɾ����ɫ����Ϣ
	public int deleteRole_service(String id);
	
	//��֤��ɫΨһ����
	public int getRoleCode_service(String roleCode);
}
