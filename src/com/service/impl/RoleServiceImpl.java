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

	//查询角色表全部信息
	@Override
	public List<Role> getRole() {
		
		return roleMapper.getRole();
	}

	//通过id查询角色信息
	@Override
	public Role getRoleThis_service(String id) {

		return roleMapper.getRoleThis_dao(id);
	}

	//增加角色表信息
	@Override
	public int addRole_service(Role role) {
		
		return roleMapper.addRole_dao(role);
	}

	//修改角色表信息
	@Override
	public int updateRole_service(Role role) {
		int count=roleMapper.updateRole_dao(role);
		return count;
	}

	//删除角色表信息
	@Override
	public int deleteRole_service(String id) {
		
		return roleMapper.deleteRole_dao(id);
	}

	//验证角色唯一编码
	@Override
	public int getRoleCode_service(String roleCode) {
		
		return roleMapper.getRoleCode_dao(roleCode);
	}
	
	
	
	
}
