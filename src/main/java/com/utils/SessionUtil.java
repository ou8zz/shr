package com.utils;

import com.shr.model.Guser;

import javax.servlet.http.HttpSession;

/**
 * @description 后台菜单管理
 * @author <a href="mailto:ou8zz@sina.com">OLE</a>
 * @date 2016/07/31
 * @version 1.0
 */
public class SessionUtil {

	/**
	 * 根据当前session获取当前登录用户对象
	 * @param session
	 * @return guser
	 */
	public static Guser getUser(HttpSession session) {
		try {
			return new Guser(1);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 根据当前session获取当前登录用户ID
	 * @param session
	 * @return guser
	 */
	public static Integer getUserId(HttpSession session) {
		try {
			return 1;
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 根据当前session获取当前登录用户名称
	 * @param session
	 * @return guser
	 */
	public static String getUserEname(HttpSession session) {
		try {
			return "admin";
		} catch (Exception e) {
		}
		return null;
	}
}
