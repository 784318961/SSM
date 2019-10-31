package com.service;

import java.util.List;


import com.pojo.Role;

public interface RoleService {

	//查询角色表全部信息
	public List<Role> getRole();
	
	//通过id查询角色信息
	public Role getRoleThis_service(String id);
	
	//增加角色表信息
	public int addRole_service(Role role);
	
	//修改角色表信息
	public int updateRole_service(Role role);
	
	//删除角色表信息
	public int deleteRole_service(String id);
	
	//验证角色唯一编码
	public int getRoleCode_service(String roleCode);
}
