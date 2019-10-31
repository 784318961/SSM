package com.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.pojo.Role;
import com.pojo.User;
import com.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Resource
	private RoleService roleService;

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	
	
	//定义显示角色管理页面信息的处理器
	@RequestMapping("/sys/roleList.html")
	public Object getRoleList(Model model){
		List<Role> roleList=roleService.getRole();
		model.addAttribute("roleList", roleList);
		return "roleList";
	}
	
	//定义获取角色信息的处理器
	@RequestMapping(value="/roles.json")
	@ResponseBody
	public Object getThis(@RequestParam String id){
		Role role=roleService.getRoleThis_service(id);
		HashMap<String,Object> map=new HashMap<String, Object>();
		map.put("role", role);
		String result=JSONArray.toJSONString(map);
		System.out.println(result);
		return result;
	}
	
	//定义进入角色查看的处理器
	@RequestMapping("/sys/view/{id}")
	public String getRoleThis(@PathVariable("id") String id,Model model){
		Role role=roleService.getRoleThis_service(id);
		model.addAttribute("role", role);
		return "thisRole";
	}
	
	//定义进入角色添加的处理器
	@RequestMapping("/sys/addRole.html")
	public String addRole(){
		return "addRole";
	}
	
	//定义处理角色添加的处理器
	@RequestMapping(value="/sys/addRoleSave.html",method=RequestMethod.POST)
	public String addRoleSave(Role role,HttpSession session){
		
		role.setCreatedBy(((User)session.getAttribute("user")).getId());
		role.setCreationDate(new Date());
		int result=roleService.addRole_service(role);
		if (result > 0) {
			System.out.println("添加成功");
			return "redirect:/role/sys/roleList.html";
		} else {
			System.out.println("添加失败，请重新操作");
			return "addRole";
		}
	}
	
	//定义进入修改角色的处理器
	@RequestMapping("/sys/update/{id}")
	public String updateRole(@PathVariable("id") String id,Model model){
		Role role=roleService.getRoleThis_service(id);
		model.addAttribute("role", role);
		return "updateRole";
	}
	
	//定义处理修改角色的处理器
	@RequestMapping(value="/sys/updateRoleSave.html",method=RequestMethod.POST)
	public String updateRoleSave(Role role,HttpSession session){
		role.setModifyBy(((User)session.getAttribute("user")).getId());
		role.setModifyDate(new Date());
		int result=roleService.updateRole_service(role);
		if (result > 0) {
			System.out.println("修改成功");
			return "redirect:/role/sys/roleList.html";
		} else {
			System.out.println("修改失败，请重新操作");
			return "updateRole";
		}
	}
	
	//定义删除角色的处理器
	@RequestMapping("/sys/delete/{id}")
	public String deleteRole(@PathVariable("id") String id){
		int result=roleService.deleteRole_service(id);
		if (result > 0) {
			System.out.println("删除成功");
			return "redirect:/role/sys/roleList.html";
		} else {
			System.out.println("删除失败，请重新操作");
			return "redirect:/role/sys/roleList.html";
		}
	}
	
	//定义验证角色编号唯一性的处理器
	@RequestMapping(value="roleCode.json")
	@ResponseBody
	public Object roleCode(@RequestParam("roleCode") String roleCode){
		int count=roleService.getRoleCode_service(roleCode);
		HashMap<String,String> map=new HashMap<String, String>();
		if(count>0){
			map.put("roleCode", "exist");
		}else{
			map.put("roleCode", "noexist");
		}
		return JSONArray.toJSONString(map);
	}
}
