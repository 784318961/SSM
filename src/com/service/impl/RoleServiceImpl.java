package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.role.RoleMapper;
import com.pojo.Role;
import com.service.RoleService;

@Transactional
@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleMapper roleMapper;

	public RoleMapper getRoleMapper() {
		return roleMapper;
	}

	public void setRoleMapper(RoleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}

	//��ѯ��ɫ��ȫ����Ϣ
	@Override
	public List<Role> getRole() {
		
		return roleMapper.getRole();
	}

	//ͨ��id��ѯ��ɫ��Ϣ
	@Override
	public Role getRoleThis_service(String id) {

		return roleMapper.getRoleThis_dao(id);
	}

	//���ӽ�ɫ����Ϣ
	@Override
	public int addRole_service(Role role) {
		
		return roleMapper.addRole_dao(role);
	}

	//�޸Ľ�ɫ����Ϣ
	@Override
	public int updateRole_service(Role role) {
		int count=roleMapper.updateRole_dao(role);
		return count;
	}

	//ɾ����ɫ����Ϣ
	@Override
	public int deleteRole_service(String id) {
		
		return roleMapper.deleteRole_dao(id);
	}

	//��֤��ɫΨһ����
	@Override
	public int getRoleCode_service(String roleCode) {
		
		return roleMapper.getRoleCode_dao(roleCode);
	}
	
	
	
	
}
