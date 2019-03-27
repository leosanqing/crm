package com.java1234.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java1234.entity.Contact;
import com.java1234.service.ContactService;
import com.java1234.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 交往记录controller层
 * 
 * @author crash
 *
 */
@Controller
@RequestMapping("/contact")
public class ContactController
{
	@Resource
	private ContactService contactService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 设置严格的数据匹配
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
	}
	
	/**
	 * 查询交往记录
	 * @param cusId
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/list")
	public void list(@RequestParam(value = "cusId") String cusId,
			HttpServletResponse response) throws IOException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cusId", cusId);
		List<Contact> contactList = contactService.find(map);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "customer" });
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray = JSONArray.fromObject(contactList, jsonConfig);
		JSONObject result = new JSONObject();
		result.put("rows", jsonArray);
		ResponseUtil.write(result, response);
	}

	/**
	 * 添加或修改交往记录
	 * 
	 * @param contact
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/save")
	public void save(Contact contact, HttpServletResponse response)
			throws IOException
	{
		JSONObject result = new JSONObject();
		int modifyNum;
		if(contact.getId() != null) 
		{
			modifyNum = contactService.update(contact);
		}else 
		{
			modifyNum = contactService.add(contact);
		}
		if (modifyNum > 0)
		{
			result.put("success", true);
		}
		ResponseUtil.write(result, response);
	}

	/**
	 * 删除交往记录
	 * 
	 * @param ids
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/delete")
	public void delete(@RequestParam(value = "id") String id,
			HttpServletResponse response) throws IOException
	{
		JSONObject result = new JSONObject();
		contactService.delete(Integer.parseInt(id));
		result.put("success", true);
		ResponseUtil.write(result, response);
	}

}
