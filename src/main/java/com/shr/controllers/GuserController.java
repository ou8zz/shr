package com.shr.controllers;

import com.constant.GroupType;
import com.shr.model.Guser;
import com.shr.model.Ugroup;
import com.shr.services.AdminService;
import com.shr.services.GuserService;
import com.utils.Paramer;
import com.utils.RegexUtil;
import com.utils.ResourceUtil;
import com.utils.RespsonData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description 后台用户管理
 * @author <a href="mailto:ou8zz@sina.com">OLE</a>
 * @date 2016/05/29
 * @version 1.0
 */
@Controller
@RequestMapping("/admin")
@Scope(value="request")
public class GuserController {
	private Log log = LogFactory.getLog(GuserController.class);
	private RespsonData rd = new RespsonData("success");			// 通用返回JSON对象
	
	@Resource(name="guserService")
	private GuserService guserService;
	
	@Resource(name="adminService")
	private AdminService adminService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df,false));
	}
	
	/**
	 * 进入用户管理界面
	 * 在进入页面时加载对应的部门和角色信息到界面
	 * @param model
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/users")
	public Object getGusers(Model model) {
		Ugroup u = new Ugroup();
		u.setGtype(GroupType.DEPARTMENT);
		Object userDepartment = adminService.getUgroup(u);
		u.setGtype(GroupType.ROLE);
		Object userRole = adminService.getUgroup(u);
		model.addAttribute("userDepartment", userDepartment);
		model.addAttribute("userRole", userRole);
		return "admin/users";
	}

	/**
	 * 获取用户资源
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/users")
	@ResponseBody
	public Object getGusers(@ModelAttribute Paramer p, @ModelAttribute Guser u) {
		try {
			p.setOb(u);
			Paramer pt = guserService.getGusers(p);
			return pt;
		} catch (Exception e) {
			log.error("getGusers", e);
			rd.result("faild", e.getMessage());
		}
		return rd;
	}
	
	/**
	 * 添加资源
	 * @param u
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/addUser")
	@ResponseBody
	public Object addGuser(@ModelAttribute(value="u") Guser u) {
		try {
			String conf = ResourceUtil.getConf("dpwd");
			u.setPwd(RegexUtil.encodeMd5(conf));
			guserService.addGuser(u);
		} catch(Exception e) {
			log.error("addGuser", e);
			rd.result("faild", e.getMessage());
		}
		return rd;
	}
	
	/**
	 * 修改资源
	 * @param u
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/editUser")
	@ResponseBody
	public Object editGuser(@ModelAttribute(value="u") Guser u) {
		try {
			guserService.editGuser(u);
		} catch(Exception e) {
			log.error("editGuser", e);
			rd.result("faild", e.getMessage());
		}
		return rd;
	}
	
	/**
	 * 修改用户部门和权限
	 * @param u
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/editUserUgroup")
	@ResponseBody
	public Object editUserUgroup(@ModelAttribute(value="u") Guser u) {
		try {
			guserService.editUserUgroup(u);
		} catch(Exception e) {
			log.error("editGuser", e);
			rd.result("faild", e.getMessage());
		}
		return rd;
	}
	
	/**
	 * 删除资源
	 * @param id
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/delUser")
	@ResponseBody
	public Object delGuser(@RequestParam(required=false) Integer[] id) {
		try {
			guserService.delGuser(id);
		} catch(Exception e) {
			log.error("delGuser error", e);
			rd.result("faild", e.getMessage());
		}
		return rd;
	}
}
