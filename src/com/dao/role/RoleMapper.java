package com.dao.role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pojo.Role;

public interface RoleMapper {

	//��ѯ��ɫ��ȫ����Ϣ
	public List<Role> getRole();
	
	//ͨ��id��ѯ��ɫ��Ϣ
	public Role getRoleThis_dao(@Param("id") String id);
	
	//���ӽ�ɫ����Ϣ
	public int addRole_dao(Role role);
	
	//�޸Ľ�ɫ����Ϣ
	public int updateRole_dao(Role role);
	
	//ɾ����ɫ����Ϣ
	public int deleteRole_dao(@Param("id") String id);
	
	//��֤��ɫΨһ����
	public int getRoleCode_dao(@Param("roleCode") String roleCode);
}
