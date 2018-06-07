package com.jz.snake.important.shiro.modules;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.inject.AbstractModule;
import com.jz.snake.important.shiro.DES;
import com.jz.snake.important.shiro.Result;
import com.jz.snake.important.shiro.service.imp.ShiroUserService;
import org.apache.shiro.SecurityUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.ViewModel;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import com.jz.snake.important.shiro.ShiroDemo.SessionKeys;
/**
 * @author jzshi
 *
 * @email jz_shi@163.com
 *
 */
@At("system")
@IocBean
public class SystemModule extends AbstractBaseModule{

	@Inject
	ShiroUserService shiroUserService;

	@At
	@POST
	@Ok("re:->:/user/list")
	public String login(@Param("user") String user, @Param("password") String password, @Param("rememberMe") boolean rememberMe, HttpSession session, ViewModel model,
			HttpServletRequest request) {
		Result result = shiroUserService.login(user, password);
		if (result.isSuccess()) {
			// 登录成功处理
			session.setAttribute(SessionKeys.USER_KEY, result.getData().get("loginUser"));
			if (rememberMe) {
				NutMap data = NutMap.NEW();
				data.put("user", user);
				data.put("password", password);
				data.put("rememberMe", rememberMe);
				_addCookie("jzsnake", DES.encrypt(Json.toJson(data)), 24 * 60 * 60 * 365);
			}
			return null;
		}
		String cookie = _getCookie("jzsnake");
		NutMap data = NutMap.NEW();
		if (!Strings.isBlank(cookie)) {
			data = Lang.map(DES.decrypt(cookie));
		}
		request.setAttribute("loginInfo", data);
		model.addv("error", result.getData().get("reason"));
		return "beetl:pages/login.html";
	}

	@At
	@Ok(">>:/")
	public Result logout(HttpSession session) {
		SecurityUtils.getSubject().logout();
		return Result.success();
	}

}
