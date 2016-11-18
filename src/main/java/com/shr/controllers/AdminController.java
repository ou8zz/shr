package com.shr.controllers;

import com.shr.model.Ugroup;
import com.shr.services.AdminService;
import com.utils.Paramer;
import com.utils.RespsonData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description 后台用户管理
 * @author <a href="mailto:ou8zz@sina.com">OLE</a>
 * @date 2016/05/29
 * @version 1.0
 */
@Controller
@RequestMapping("/admin")
@Scope(value="request")
public class AdminController {
	private Log log = LogFactory.getLog(AdminController.class);
	private RespsonData rd = new RespsonData("success");			// 通用返回JSON对象

	@Resource(name="adminService")
	private AdminService adminService;

	/**
	 * 角色管理
	 * @param model
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/role")
	public String rolePage(Model model) {
		model.addAttribute("gtype", "ROLE");
		model.addAttribute("gtypeDes", "角色");
		return "admin/ugroup";
	}

	/**
	 * 部门管理
	 * @param model
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/department")
	public String departmentPage(Model model) {
		model.addAttribute("gtype", "DEPARTMENT");
		model.addAttribute("gtypeDes", "部门");
		return "admin/ugroup";
	}

	/**
	 * 角色列表
	 * @param p
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/getUgroups")
	@ResponseBody
	public Object getUgroups(@ModelAttribute("page") Paramer p, @ModelAttribute("u") Ugroup u) {
		try {
			p.setOb(u);
			return adminService.getUgroups(p);
		} catch(Exception e) {
			log.error("getUgroups error", e);
			rd.result("faild", e.getMessage());
		}
		return rd;
	}

	/**
	 * 新增
	 * @param u
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/addUgroup")
	@ResponseBody
	public Object addUgroup(@ModelAttribute("u") Ugroup u) {
		try {
			adminService.addUgroup(u);
		} catch(Exception e) {
			log.error("addUgroup error", e);
			rd.result("faild", e.getMessage());
		}
		return rd;
	}

	/**
	 * 修改
	 * @param u
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/editUgroup")
	@ResponseBody
	public Object editUgroup(@ModelAttribute("u") Ugroup u) {
		try {
			adminService.editUgroup(u);
		} catch(Exception e) {
			log.error("editUgroup error", e);
			rd.result("faild", e.getMessage());
		}
		return rd;
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/delUgroup")
	@ResponseBody
	public Object delUgroup(@RequestParam("id") Integer id) {
		try {
			adminService.delUgroup(id);
		} catch(Exception e) {
			log.error("delUgroup error", e);
			rd.result("faild", e.getMessage());
		}
		return rd;
	}

	/**
	 * save role ztree
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/saveRoleZtree")
	@ResponseBody
	public Object saveRoleZtree(@ModelAttribute Ugroup ug) {
		try {
			adminService.saveRoleZtree(ug);
		} catch(Exception e) {
			log.error("saveRoleZtree error", e);
			rd.result("faild", e.getMessage());
		}
		return rd;
	}
}
