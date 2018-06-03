package com.jz.snake.important.shiro.modules;

import com.jz.snake.important.shiro.Result;
import com.jz.snake.important.shiro.bean.User;
import com.jz.snake.important.shiro.service.imp.UserService;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Attr;
import org.nutz.mvc.annotation.Ok;
import com.jz.snake.important.shiro.ShiroDemo.SessionKeys;

@At("setting")
public class SettingModule extends AbstractBaseModule {

	@Inject
	UserService userService;

	@At
	@Ok("beetl:pages/user/detail.html")
	public Result profile(@Attr(SessionKeys.USER_KEY) User user) {
		return Result.success().setTitle("个人信息").addData("user", userService.fetch(user.getId()));
	}
}
