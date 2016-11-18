package com.shr.controllers;

import com.shr.model.Guser;
import com.shr.services.AdminService;
import com.shr.services.GuserService;
import com.utils.RespsonData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static org.apache.shiro.web.util.WebUtils.SAVED_REQUEST_KEY;

/**
 * @description 后台用户管理
 * @author <a href="mailto:ou8zz@sina.com">OLE</a>
 * @date 2016/05/29
 * @version 1.0
 */
@Controller
@Scope(value="request")
public class IndexController {
	private Log log = LogFactory.getLog(IndexController.class);
	private RespsonData rd = new RespsonData("success");			// 通用返回JSON对象
	
	@Resource(name="guserService")
	private GuserService guserService;
	
	@Resource(name="adminService")
	private AdminService adminService;
	
	/**
	 * 空白首页
	 * @return
	 */
	@RequestMapping("/")
    public String home() {
        return "app/index";
    }
	
	/**
	 * 登录
	 */
	@RequestMapping(method=RequestMethod.GET, value="/login")
	public String login() {
		return "login";
	}

    /**
     * 登录校验
     */
    @RequestMapping(method=RequestMethod.POST, value="/web_login")
    @ResponseBody
    public Object login(@ModelAttribute Guser u, HttpServletRequest request) {
        rd = new RespsonData("success");
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(u.getEname(), u.getPwd());
            token.setRememberMe(u.getRememberMe());

            //获取当前的Subject
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);
            //验证是否登录成功
            if(currentUser.isAuthenticated()){
                // 设置session登录用户信息后返回登录前页面的url
                Session session = currentUser.getSession(false);
                session.setAttribute("uid", u.getId());
                session.setAttribute("ename", u.getEname());
                session.setAttribute("contextPath", request.getContextPath());
                if (session != null) {
                    SavedRequest savedRequest = (SavedRequest) session.getAttribute(SAVED_REQUEST_KEY);
                    rd.result("SUCCESS", savedRequest.getRequestURI());
                }
            }else{
                token.clear();
            }
        } catch (UnknownAccountException uae) {
            rd.result("UnknownAccount", "未知账户");
            log.error("UnknownAccount", uae);
        } catch (IncorrectCredentialsException ice) {
            rd.result("IncorrectPassword", "密码不正确");
            log.error("IncorrectPassword", ice);
        } catch (LockedAccountException lae) {
            rd.result("AccountLocked", "账户已锁定");
            log.error("AccountLocked", lae);
        } catch (ExcessiveAttemptsException eae) {
            rd.result("TooManyErrorCount", "用户名或密码错误次数过多");
            log.error("TooManyErrorCount", eae);
        } catch (AuthenticationException ae) {
            rd.result("AuthenticationException", "用户名或密码不正确");
            log.error("AuthenticationException", ae);
        }
        return rd;
    }

	/**
	 * 首页
	 * @param model
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value={"/app/index", "/audit/index", "/admin/index"})
	public String rolePage(Model model, HttpServletRequest req) {
		return "app/index";
	}
}
