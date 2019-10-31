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
	
	
	
	//������ʾ��ɫ����ҳ����Ϣ�Ĵ�����
	@RequestMapping("/sys/roleList.html")
	public Object getRoleList(Model model){
		List<Role> roleList=roleService.getRole();
		model.addAttribute("roleList", roleList);
		return "roleList";
	}
	
	//�����ȡ��ɫ��Ϣ�Ĵ�����
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
	
	//��������ɫ�鿴�Ĵ�����
	@RequestMapping("/sys/view/{id}")
	public String getRoleThis(@PathVariable("id") String id,Model model){
		Role role=roleService.getRoleThis_service(id);
		model.addAttribute("role", role);
		return "thisRole";
	}
	
	//��������ɫ��ӵĴ�����
	@RequestMapping("/sys/addRole.html")
	public String addRole(){
		return "addRole";
	}
	
	//���崦���ɫ��ӵĴ�����
	@RequestMapping(value="/sys/addRoleSave.html",method=RequestMethod.POST)
	public String addRoleSave(Role role,HttpSession session){
		
		role.setCreatedBy(((User)session.getAttribute("user")).getId());
		role.setCreationDate(new Date());
		int result=roleService.addRole_service(role);
		if (result > 0) {
			System.out.println("��ӳɹ�");
			return "redirect:/role/sys/roleList.html";
		} else {
			System.out.println("���ʧ�ܣ������²���");
			return "addRole";
		}
	}
	
	//��������޸Ľ�ɫ�Ĵ�����
	@RequestMapping("/sys/update/{id}")
	public String updateRole(@PathVariable("id") String id,Model model){
		Role role=roleService.getRoleThis_service(id);
		model.addAttribute("role", role);
		return "updateRole";
	}
	
	//���崦���޸Ľ�ɫ�Ĵ�����
	@RequestMapping(value="/sys/updateRoleSave.html",method=RequestMethod.POST)
	public String updateRoleSave(Role role,HttpSession session){
		role.setModifyBy(((User)session.getAttribute("user")).getId());
		role.setModifyDate(new Date());
		int result=roleService.updateRole_service(role);
		if (result > 0) {
			System.out.println("�޸ĳɹ�");
			return "redirect:/role/sys/roleList.html";
		} else {
			System.out.println("�޸�ʧ�ܣ������²���");
			return "updateRole";
		}
	}
	
	//����ɾ����ɫ�Ĵ�����
	@RequestMapping("/sys/delete/{id}")
	public String deleteRole(@PathVariable("id") String id){
		int result=roleService.deleteRole_service(id);
		if (result > 0) {
			System.out.println("ɾ���ɹ�");
			return "redirect:/role/sys/roleList.html";
		} else {
			System.out.println("ɾ��ʧ�ܣ������²���");
			return "redirect:/role/sys/roleList.html";
		}
	}
	
	//������֤��ɫ���Ψһ�ԵĴ�����
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
