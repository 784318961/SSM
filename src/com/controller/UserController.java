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

	// 定义登录页面对应的处理器
	@RequestMapping("/login.html")
	public String login() {
		return "login";
	}

	// 定义处理客户登录请求的处理器
	@RequestMapping(value = "/dologin.html", method = RequestMethod.POST)
	public String doLogin(@RequestParam("userCode") String userCode,
			@RequestParam("userPassword") String userPassword,
			HttpSession session, HttpServletRequest req) {
		User user = userService.login(userCode, userPassword);
		if (user != null) {
			session.setAttribute("user", user);
			return "redirect:/user/sys/frame.html";
		} else {
			req.setAttribute("error", "用户名密码错误，请重新输入！！！");
			return "login";
		}
	}

	// 定义主页面对应的处理器
	@RequestMapping(value = "/sys/frame.html")
	public String main() {
		return "frame";
	}

	// 定义系统退出功能的处理器
	@RequestMapping("/out.html")
	public String exit(HttpSession session) {
		session.removeAttribute("user");
		session.invalidate();
		return "login";
	}

	// 分页查询显示用户信息的处理器
	@RequestMapping("/sys/userList.html")
	public String getUserList(
			Model model,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "userRole", required = false) Integer userRole,
			@RequestParam(value = "currentPageNo", required = false) Integer currentPageNo,
			HttpSession session) {

		Integer userRoleThis = null;

		// 定义初始页
		Integer currentPageNoThis = 1;

		// 定义每页显示记录数
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

		// 获取总记录数
		int totalCount = userService.getUserCount_service(userName, userRole);

		// 计算总页数
		int totalPageCount = totalCount % pageSize == 0 ? totalCount / pageSize
				: totalCount / pageSize + 1;

		

		// 对页码跳转进行限定
		if (currentPageNoThis < 1) {
			currentPageNoThis = 1;
		} else if (currentPageNoThis > totalPageCount) {
			currentPageNoThis = totalPageCount;
		}
		// 查询指定的用户列表信息
		List<User> userList = userService.getUserList_service(userName,
				userRole, currentPageNoThis, pageSize);
		List<Role> roleList = roleService.getRole();

		// 保存主要信息在模型中
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

	// 查看指定用户的处理器
	@RequestMapping("/sys/view/{id}")
	public String getThisUser(Model model, @PathVariable String id) {
		User user = userService.getUserThis_service(id);
		model.addAttribute("u", user);
		return "thisUser";
	}

	// 定义进入添加页面的处理器
	@RequestMapping("/sys/addUser.html")
	public String addUser(Model model) {
		List<Role> roleList = roleService.getRole();
		model.addAttribute("roleList", roleList);
		return "addUser";
	}

	// 定义添加用户的处理器
	@RequestMapping(value = "/sys/addSave.html", method = RequestMethod.POST)
	public String addUserSave(
			User user,
			HttpSession session,
			HttpServletRequest req,
			Model model,
			@RequestParam(value = "file", required = false) MultipartFile[] files) {

		// 定义一个空路径
		String idPicPath = null;
		String workPicPath = null;
		String error = null;

		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			// 判断上传文件是否为空
			if (!file.isEmpty()) {
				if (i == 0) {
					error = "uploadIpError";
				} else if (i == 1) {
					error = "uploadWoError";
				}

				// 创建文件路径
				String path = req.getSession().getServletContext()
						.getRealPath("statics/file");
				System.out.println(path);

				// 获取上传文件名
				String oldFileName = file.getOriginalFilename();
				System.out.println(oldFileName);

				// 获取源文件的后缀
				String suffix = FilenameUtils.getExtension(oldFileName);
				System.out.println("suffix");

				// 获取源文件的大小
				long fileSize = file.getSize();
				System.out.println(fileSize);

				// 定义一个文件大小为500K
				int fileSizeNow = 500000;

				// 判断文件大小及格式
				if (fileSize > fileSizeNow) {
					req.setAttribute(error, "*上传文件不得大于500K");
					return "addUser";
				} else if (suffix.equalsIgnoreCase("jpg")
						|| suffix.equalsIgnoreCase("png")) {

					// 定义文件保存路径名称
					String fileName = System.currentTimeMillis()
							+ RandomUtils.nextInt(1000000)
							+ file.getOriginalFilename();
					System.out.println(fileName);

					// 创建文件夹
					File targetFile = new File(path, fileName);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}

					// 把文件上传保存在文件夹中
					try {
						file.transferTo(targetFile);
					} catch (IOException e) {
						e.printStackTrace();
						req.setAttribute(error, "*文件上传失败");
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
					req.setAttribute("uploadFileError", "*文件格式不正确");
					return "addUser";
				}
			}
		}
		// 上传成功
		user.setCreatedBy(((User) session.getAttribute("user")).getId());
		user.setCreationDate(new Date());
		user.setIdPicPath(idPicPath);
		user.setWorkPicPath(workPicPath);
		int result = userService.addUser_service(user);
		List<Role> roleList = roleService.getRole();
		if (result > 0) {
			System.out.println("添加成功");
			return "redirect:/user/sys/userList.html";
		} else {
			System.out.println("添加失败，请重新操作");
			model.addAttribute("roleList", roleList);
			return "addUser";
		}

	}

	// 验证userCode唯一性的处理器
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

	// 获取用户数据进入修改页面的处理器
	@RequestMapping("/sys/update/{id}")
	public String updateUser(@PathVariable("id") String id, Model model) {
		User user = userService.getUserThis_service(id);
		List<Role> roleList = roleService.getRole();
		model.addAttribute("u", user);
		model.addAttribute("roleList", roleList);
		return "updateUser";
	}

	// 定义修改用户数据的处理器
	@RequestMapping(value = "/sys/updateUserSave.html", method = RequestMethod.POST)
	public String updateUserSave(
			User user,
			HttpSession session,
			HttpServletRequest req,
			Model model,
			@RequestParam(value = "file", required = false) MultipartFile[] files) {
		// 定义一个空路径
		String idPicPath = null;
		String workPicPath = null;
		String error = null;
		List<Role> roleList = roleService.getRole();

		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			// 判断上传文件是否为空
			if (!file.isEmpty()) {
				if (i == 0) {
					error = "uploadIpError";
				} else if (i == 1) {
					error = "uploadWoError";
				}

				// 创建文件路径
				String path = req.getSession().getServletContext()
						.getRealPath("statics/file");
				System.out.println(path);

				// 获取上传文件名
				String oldFileName = file.getOriginalFilename();
				System.out.println(oldFileName);

				// 获取源文件的后缀
				String suffix = FilenameUtils.getExtension(oldFileName);
				System.out.println("suffix");

				// 获取源文件的大小
				long fileSize = file.getSize();
				System.out.println(fileSize);

				// 定义一个文件大小为500K
				int fileSizeNow = 500000;

				// 判断文件大小及格式
				if (fileSize > fileSizeNow) {
					req.setAttribute(error, "*上传文件不得大于500K");
					model.addAttribute("roleList", roleList);
					return "addUser";
				} else if (suffix.equalsIgnoreCase("jpg")
						|| suffix.equalsIgnoreCase("png")) {

					// 定义文件保存路径名称
					String fileName = System.currentTimeMillis()
							+ RandomUtils.nextInt(1000000)
							+ file.getOriginalFilename();
					System.out.println(fileName);

					// 创建文件夹
					File targetFile = new File(path, fileName);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}

					// 把文件上传保存在文件夹中
					try {
						file.transferTo(targetFile);
					} catch (IOException e) {
						e.printStackTrace();
						req.setAttribute(error, "*文件上传失败");
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
					req.setAttribute("uploadFileError", "*文件格式不正确");
					model.addAttribute("roleList", roleList);
					return "addUser";
				}
			}
		}
		// 上传成功
		user.setModifyBy(((User) session.getAttribute("user")).getId());
		user.setModifyDate(new Date());
		user.setIdPicPath(idPicPath);
		user.setWorkPicPath(workPicPath);
		// 找到WebApps的根目录
		String realPath = req.getServletContext().getRealPath("/");
		String filePath = (new File(realPath)).getParent();
		int result = userService.updateUser_service(user, filePath);
		if (result > 0) {
			System.out.println("修改成功");
			return "redirect:/user/sys/userList.html";
		} else {
			System.out.println("修改失败,请重新操作！！！");
			return "updateUser";
		}
	}

	// 获取输出进入修改密码页面的处理器
	@RequestMapping("/sys/updatePassword.html")
	public String updatePassword() {
		return "updatePassword";
	}

	// 判断旧密码是否正确
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

	// 定义修改密码的处理器
	@RequestMapping(value = "/sys/updatePasswordSave.html", method = RequestMethod.POST)
	public String updatePasswordSave(@RequestParam("id") String id,
			@RequestParam("newUserPassword") String userPassword) {
		int result = userService.updatePassword_service(id, userPassword);
		if (result > 0) {
			System.out.println("修改成功,请重新登录！！！");
			return "redirect:/user/login.html";
		} else {
			System.out.println("修改失败,请重新操作！！！");
			return "updatePassword";
		}
	}

	// 定义删除用户数据的处理器
	@RequestMapping("/sys/delete/{id}")
	public String deleteUser(@PathVariable("id") String id,
			HttpServletRequest req) {
		String realPath = req.getServletContext().getRealPath("/");
		String filePath = (new File(realPath)).getParent();
		System.out.println(filePath);
		int result = userService.deleteUser_service(id, filePath);
		if (result > 0) {
			System.out.println("删除成功");
			return "redirect:/user/sys/userList.html";
		} else {
			System.out.println("删除失败,请重新操作！！！");
			return "updateUser";
		}
	}

	// 定义获取Role的处理器
	@RequestMapping(value = "/roleList.json", method = RequestMethod.GET)
	@ResponseBody
	public Object getRoleList() {
		List<Role> roleList = roleService.getRole();
		return JSON.toJSONString(roleList);
	}

}
