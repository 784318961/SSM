package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;
import com.pojo.Role;
import com.pojo.User;
import com.service.RoleService;
import com.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Resource
	private RoleService roleService;

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// �����¼ҳ���Ӧ�Ĵ�����
	@RequestMapping("/login.html")
	public String login() {
		return "login";
	}

	// ���崦��ͻ���¼����Ĵ�����
	@RequestMapping(value = "/dologin.html", method = RequestMethod.POST)
	public String doLogin(@RequestParam("userCode") String userCode,
			@RequestParam("userPassword") String userPassword,
			HttpSession session, HttpServletRequest req) {
		User user = userService.login(userCode, userPassword);
		if (user != null) {
			session.setAttribute("user", user);
			return "redirect:/user/sys/frame.html";
		} else {
			req.setAttribute("error", "�û�������������������룡����");
			return "login";
		}
	}

	// ������ҳ���Ӧ�Ĵ�����
	@RequestMapping(value = "/sys/frame.html")
	public String main() {
		return "frame";
	}

	// ����ϵͳ�˳����ܵĴ�����
	@RequestMapping("/out.html")
	public String exit(HttpSession session) {
		session.removeAttribute("user");
		session.invalidate();
		return "login";
	}

	// ��ҳ��ѯ��ʾ�û���Ϣ�Ĵ�����
	@RequestMapping("/sys/userList.html")
	public String getUserList(
			Model model,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "userRole", required = false) Integer userRole,
			@RequestParam(value = "currentPageNo", required = false) Integer currentPageNo,
			HttpSession session) {

		Integer userRoleThis = null;

		// �����ʼҳ
		Integer currentPageNoThis = 1;

		// ����ÿҳ��ʾ��¼��
		int pageSize = 5;

		if (userName == null) {
			userName = "";
		}
		if (userRole != null && !userRole.equals("")) {
			userRoleThis = userRole;
		}

		if (currentPageNo != null) {
			currentPageNoThis = currentPageNo;
			userName = session.getAttribute("userName").toString();
		}

		// ��ȡ�ܼ�¼��
		int totalCount = userService.getUserCount_service(userName, userRole);

		// ������ҳ��
		int totalPageCount = totalCount % pageSize == 0 ? totalCount / pageSize
				: totalCount / pageSize + 1;

		

		// ��ҳ����ת�����޶�
		if (currentPageNoThis < 1) {
			currentPageNoThis = 1;
		} else if (currentPageNoThis > totalPageCount) {
			currentPageNoThis = totalPageCount;
		}
		// ��ѯָ�����û��б���Ϣ
		List<User> userList = userService.getUserList_service(userName,
				userRole, currentPageNoThis, pageSize);
		List<Role> roleList = roleService.getRole();

		// ������Ҫ��Ϣ��ģ����
		// model.addAttribute("userName", userName);
		session.setAttribute("userName", userName);
		model.addAttribute("userRole", userRoleThis);
		model.addAttribute("currentPageNo", currentPageNoThis);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("userList", userList);
		model.addAttribute("roleList", roleList);

		return "userList";
	}

	// �鿴ָ���û��Ĵ�����
	@RequestMapping("/sys/view/{id}")
	public String getThisUser(Model model, @PathVariable String id) {
		User user = userService.getUserThis_service(id);
		model.addAttribute("u", user);
		return "thisUser";
	}

	// ����������ҳ��Ĵ�����
	@RequestMapping("/sys/addUser.html")
	public String addUser(Model model) {
		List<Role> roleList = roleService.getRole();
		model.addAttribute("roleList", roleList);
		return "addUser";
	}

	// ��������û��Ĵ�����
	@RequestMapping(value = "/sys/addSave.html", method = RequestMethod.POST)
	public String addUserSave(
			User user,
			HttpSession session,
			HttpServletRequest req,
			Model model,
			@RequestParam(value = "file", required = false) MultipartFile[] files) {

		// ����һ����·��
		String idPicPath = null;
		String workPicPath = null;
		String error = null;

		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			// �ж��ϴ��ļ��Ƿ�Ϊ��
			if (!file.isEmpty()) {
				if (i == 0) {
					error = "uploadIpError";
				} else if (i == 1) {
					error = "uploadWoError";
				}

				// �����ļ�·��
				String path = req.getSession().getServletContext()
						.getRealPath("statics/file");
				System.out.println(path);

				// ��ȡ�ϴ��ļ���
				String oldFileName = file.getOriginalFilename();
				System.out.println(oldFileName);

				// ��ȡԴ�ļ��ĺ�׺
				String suffix = FilenameUtils.getExtension(oldFileName);
				System.out.println("suffix");

				// ��ȡԴ�ļ��Ĵ�С
				long fileSize = file.getSize();
				System.out.println(fileSize);

				// ����һ���ļ���СΪ500K
				int fileSizeNow = 500000;

				// �ж��ļ���С����ʽ
				if (fileSize > fileSizeNow) {
					req.setAttribute(error, "*�ϴ��ļ����ô���500K");
					return "addUser";
				} else if (suffix.equalsIgnoreCase("jpg")
						|| suffix.equalsIgnoreCase("png")) {

					// �����ļ�����·������
					String fileName = System.currentTimeMillis()
							+ RandomUtils.nextInt(1000000)
							+ file.getOriginalFilename();
					System.out.println(fileName);

					// �����ļ���
					File targetFile = new File(path, fileName);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}

					// ���ļ��ϴ��������ļ�����
					try {
						file.transferTo(targetFile);
					} catch (IOException e) {
						e.printStackTrace();
						req.setAttribute(error, "*�ļ��ϴ�ʧ��");
						return "addUser";
					}
					if (i == 0) {
						idPicPath = "/SSM/statics/file/" + fileName;
						System.out.println(idPicPath);
					} else if (i == 1) {
						workPicPath = "/SSM/statics/file/" + fileName;
						System.out.println(workPicPath);
					}

				} else {
					req.setAttribute("uploadFileError", "*�ļ���ʽ����ȷ");
					return "addUser";
				}
			}
		}
		// �ϴ��ɹ�
		user.setCreatedBy(((User) session.getAttribute("user")).getId());
		user.setCreationDate(new Date());
		user.setIdPicPath(idPicPath);
		user.setWorkPicPath(workPicPath);
		int result = userService.addUser_service(user);
		List<Role> roleList = roleService.getRole();
		if (result > 0) {
			System.out.println("��ӳɹ�");
			return "redirect:/user/sys/userList.html";
		} else {
			System.out.println("���ʧ�ܣ������²���");
			model.addAttribute("roleList", roleList);
			return "addUser";
		}

	}

	// ��֤userCodeΨһ�ԵĴ�����
	@RequestMapping(value = "/userCode.json", method = RequestMethod.GET)
	@ResponseBody
	public Object userCodeIsExit(@RequestParam String userCode) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isNullOrEmpty(userCode)) {
			resultMap.put("userCode", "exist");
		} else {
			User user = userService.getUserThisByCode_service(userCode);

			if (null != user) {
				resultMap.put("userCode", "exist");
			} else {
				resultMap.put("userCode", "noexist");
			}
		}
		return JSONArray.toJSONString(resultMap);
	}

	// ��ȡ�û����ݽ����޸�ҳ��Ĵ�����
	@RequestMapping("/sys/update/{id}")
	public String updateUser(@PathVariable("id") String id, Model model) {
		User user = userService.getUserThis_service(id);
		List<Role> roleList = roleService.getRole();
		model.addAttribute("u", user);
		model.addAttribute("roleList", roleList);
		return "updateUser";
	}

	// �����޸��û����ݵĴ�����
	@RequestMapping(value = "/sys/updateUserSave.html", method = RequestMethod.POST)
	public String updateUserSave(
			User user,
			HttpSession session,
			HttpServletRequest req,
			Model model,
			@RequestParam(value = "file", required = false) MultipartFile[] files) {
		// ����һ����·��
		String idPicPath = null;
		String workPicPath = null;
		String error = null;
		List<Role> roleList = roleService.getRole();

		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			// �ж��ϴ��ļ��Ƿ�Ϊ��
			if (!file.isEmpty()) {
				if (i == 0) {
					error = "uploadIpError";
				} else if (i == 1) {
					error = "uploadWoError";
				}

				// �����ļ�·��
				String path = req.getSession().getServletContext()
						.getRealPath("statics/file");
				System.out.println(path);

				// ��ȡ�ϴ��ļ���
				String oldFileName = file.getOriginalFilename();
				System.out.println(oldFileName);

				// ��ȡԴ�ļ��ĺ�׺
				String suffix = FilenameUtils.getExtension(oldFileName);
				System.out.println("suffix");

				// ��ȡԴ�ļ��Ĵ�С
				long fileSize = file.getSize();
				System.out.println(fileSize);

				// ����һ���ļ���СΪ500K
				int fileSizeNow = 500000;

				// �ж��ļ���С����ʽ
				if (fileSize > fileSizeNow) {
					req.setAttribute(error, "*�ϴ��ļ����ô���500K");
					model.addAttribute("roleList", roleList);
					return "addUser";
				} else if (suffix.equalsIgnoreCase("jpg")
						|| suffix.equalsIgnoreCase("png")) {

					// �����ļ�����·������
					String fileName = System.currentTimeMillis()
							+ RandomUtils.nextInt(1000000)
							+ file.getOriginalFilename();
					System.out.println(fileName);

					// �����ļ���
					File targetFile = new File(path, fileName);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}

					// ���ļ��ϴ��������ļ�����
					try {
						file.transferTo(targetFile);
					} catch (IOException e) {
						e.printStackTrace();
						req.setAttribute(error, "*�ļ��ϴ�ʧ��");
						model.addAttribute("roleList", roleList);
						return "addUser";
					}
					if (i == 0) {
						idPicPath = "/SSM/statics/file/" + fileName;
						System.out.println(idPicPath);
					} else if (i == 1) {
						workPicPath = "/SSM/statics/file/" + fileName;
						System.out.println(workPicPath);
					}

				} else {
					req.setAttribute("uploadFileError", "*�ļ���ʽ����ȷ");
					model.addAttribute("roleList", roleList);
					return "addUser";
				}
			}
		}
		// �ϴ��ɹ�
		user.setModifyBy(((User) session.getAttribute("user")).getId());
		user.setModifyDate(new Date());
		user.setIdPicPath(idPicPath);
		user.setWorkPicPath(workPicPath);
		// �ҵ�WebApps�ĸ�Ŀ¼
		String realPath = req.getServletContext().getRealPath("/");
		String filePath = (new File(realPath)).getParent();
		int result = userService.updateUser_service(user, filePath);
		if (result > 0) {
			System.out.println("�޸ĳɹ�");
			return "redirect:/user/sys/userList.html";
		} else {
			System.out.println("�޸�ʧ��,�����²���������");
			return "updateUser";
		}
	}

	// ��ȡ��������޸�����ҳ��Ĵ�����
	@RequestMapping("/sys/updatePassword.html")
	public String updatePassword() {
		return "updatePassword";
	}

	// �жϾ������Ƿ���ȷ
	@RequestMapping(value = "/ChPwd.json")
	@ResponseBody
	public Object verifierPwd(@RequestParam String userPassword,
			@RequestParam String id) {
		HashMap<String, String> resultMap = new HashMap<String, String>();

		User user = userService.getUserThis_service(id);
		if (user.getUserPassword().equals(userPassword)) {
			resultMap.put("userPassword", "exist");
		} else {
			resultMap.put("userPassword", "noexist");
		}
		String result = JSONArray.toJSONString(resultMap);
		System.out.println(result);
		return result;
	}

	// �����޸�����Ĵ�����
	@RequestMapping(value = "/sys/updatePasswordSave.html", method = RequestMethod.POST)
	public String updatePasswordSave(@RequestParam("id") String id,
			@RequestParam("newUserPassword") String userPassword) {
		int result = userService.updatePassword_service(id, userPassword);
		if (result > 0) {
			System.out.println("�޸ĳɹ�,�����µ�¼������");
			return "redirect:/user/login.html";
		} else {
			System.out.println("�޸�ʧ��,�����²���������");
			return "updatePassword";
		}
	}

	// ����ɾ���û����ݵĴ�����
	@RequestMapping("/sys/delete/{id}")
	public String deleteUser(@PathVariable("id") String id,
			HttpServletRequest req) {
		String realPath = req.getServletContext().getRealPath("/");
		String filePath = (new File(realPath)).getParent();
		System.out.println(filePath);
		int result = userService.deleteUser_service(id, filePath);
		if (result > 0) {
			System.out.println("ɾ���ɹ�");
			return "redirect:/user/sys/userList.html";
		} else {
			System.out.println("ɾ��ʧ��,�����²���������");
			return "updateUser";
		}
	}

	// �����ȡRole�Ĵ�����
	@RequestMapping(value = "/roleList.json", method = RequestMethod.GET)
	@ResponseBody
	public Object getRoleList() {
		List<Role> roleList = roleService.getRole();
		return JSON.toJSONString(roleList);
	}

}
