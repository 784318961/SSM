package com.dao.role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pojo.Role;

public interface RoleMapper {

	//查询角色表全部信息
	public List<Role> getRole();
	
	//通过id查询角色信息
	public Role getRoleThis_dao(@Param("id") String id);
	
	//增加角色表信息
	public int addRole_dao(Role role);
	
	//修改角色表信息
	public int updateRole_dao(Role role);
	
	//删除角色表信息
	public int deleteRole_dao(@Param("id") String id);
	
	//验证角色唯一编码
	public int getRoleCode_dao(@Param("roleCode") String roleCode);
}
