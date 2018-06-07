package com.jz.snake.important.shiro.modules;

import com.jz.snake.important.shiro.InstallPermission;
import com.jz.snake.important.shiro.Result;
import com.jz.snake.important.shiro.ext.anno.ThunderRequiresPermissions;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.nutz.integration.shiro.annotation.NutzRequiresPermissions;
import org.nutz.mvc.annotation.At;


/**
 * @author jzshi
 *
 * @email jz_shi@163.com
 *
 */
@At("shiro")
public class ShiroDemoModule extends AbstractBaseModule {

	@At
	@RequiresGuest
	public Result guest() {
		return Result.success().addData("tag", "guest");
	}

	@At
	@RequiresUser
	public Result user() {
		return Result.success().addData("tag", "user");
	}

	@At
	@RequiresAuthentication
	public Result authentication() {
		return Result.success().addData("tag", "authentication");
	}

	@At
	@RequiresRoles("admin")
	public Result admin() {
		return Result.success().addData("tag", "admin");
	}

	@At
	@RequiresRoles(value = { "admin", "kkk" }, logical = Logical.AND)
	public Result logical() {
		return Result.success().addData("tag", "logical");
	}

	@At
	@RequiresPermissions("user.list")
	public Result permission() {
		return Result.success().addData("tag", "permission");
	}

	@At
	@NutzRequiresPermissions(name = "Nutz", tag = "demo", value = { "user.list" })
	public Result nutz() {
		return Result.success().addData("tag", "nutz");
	}

	@At
	@ThunderRequiresPermissions(InstallPermission.CONFIG_ADD)
	public Result thunder() {
		return Result.success().addData("tag", "thunder");
	}

}
