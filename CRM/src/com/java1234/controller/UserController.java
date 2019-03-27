package com.java1234.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java1234.entity.PageBean;
import com.java1234.entity.User;
import com.java1234.service.UserService;
import com.java1234.util.ResponseUtil;
import com.java1234.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 用户controller层
 * 
 * @author crash
 *
 */
@Controller
@RequestMapping("/user")
public class UserController
{
	@Resource
	private UserService userService;

	/**
	 * 用户登录
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login(User user, HttpServletRequest request)
	{
		User currentUser = userService.login(user);
		if (currentUser == null)
		{
			request.setAttribute("user", user);
			request.setAttribute("errorMsg", "用户名或密码或角色错误！");
			return "login";
		} else
		{
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", currentUser);
			return "redirect:/main.jsp";
		}
	}

	/**
	 * 分页查询用户，以用户名模糊搜索用户
	 * @param page
	 * @param rows
	 * @param s_user
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/list")
	public void list(
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			User s_user, HttpServletResponse response) throws IOException
	{
		PageBean pageBean = new PageBean(Integer.parseInt(page),
				Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", StringUtil.formatLike(s_user.getUserName()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<User> userList = userService.find(map);
		Long total = userService.getTotal(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(userList);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(result, response);
	}

	/**
	 * 添加或修改用户
	 * @param user
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/save")
	public void save(User user, HttpServletResponse response) throws IOException
	{
		JSONObject result = new JSONObject();
		int modifyNum;
		if (user.getId() != null)
		{
			modifyNum = userService.update(user);
		} else
		{
			modifyNum = userService.add(user);
		}
		if (modifyNum > 0)
		{
			result.put("success", true);
		}
		ResponseUtil.write(result, response);
	}

	/**
	 * 删除用户
	 * @param ids
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/delete")
	public void delete(@RequestParam(value = "ids", required = true) String ids,
			HttpServletResponse response) throws IOException
	{
		JSONObject result = new JSONObject();
		String[] id = ids.split(",");
		for (String i : id)
		{
			userService.delete(Integer.parseInt(i));
		}
		result.put("success", true);
		ResponseUtil.write(result, response);
	}
	
	/**
	 * 获取客户经理真实姓名集合
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/customerManagerComboList")
	public void customerManagerComboList(HttpServletResponse response) throws IOException 
	{
		JSONArray jsonArray = new JSONArray();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleName", "客户经理");
		List<User> userList = userService.find(map);
		jsonArray= JSONArray.fromObject(userList);
		ResponseUtil.write(jsonArray, response);
	}
	
	/**
	 * 修改密码
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/modifyPassword")
	public void modifyPassword(@RequestParam(value="id") String id, @RequestParam(value="newPassword") String newPassword, HttpServletResponse response) throws IOException 
	{
		JSONObject result = new JSONObject();
		User user = userService.findById(Integer.parseInt(id));
		user.setPassword(newPassword);
		userService.update(user);
		result.put("success", true);
		ResponseUtil.write(result, response);
	}
	
	/**
	 * 安全退出
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) 
	{
		session.invalidate();
		return "redirect:/login.jsp";
	}
}
