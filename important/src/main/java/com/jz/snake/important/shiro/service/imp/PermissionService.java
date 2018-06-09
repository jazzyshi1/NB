package com.jz.snake.important.shiro.service.imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.google.inject.Inject;
import com.jz.snake.important.shiro.bean.Permission;
import com.jz.snake.important.shiro.service.BaseService;
import net.sf.ehcache.CacheManager;
import org.nutz.dao.sql.Sql;

/**
 * 
 * @author jzshi
 *
 * @email jz_shi@163.com
 *
 */
public class PermissionService extends BaseService<Permission> {

	/**
	 * 用户的全部权限
	 * 
	 * @author jzshi
	 * @param id
	 *            用户id
	 * @return
	 */
	public List<Permission> getAllPermissionsByUserId(int id) {
		List<Permission> target = listDirectPermissionsByUserId(id);
		target.addAll(listIndirectPermissionsByUserId(id));
		return new ArrayList(new HashSet(target));
	}

	/**
	 * 获取用户的直接权限
	 * 
	 * @author jzshi
	 * @param id
	 *            用户id
	 * @return 角色列表
	 */
	public List<Permission> listDirectPermissionsByUserId(int id) {
		Sql sql = dao().sqls().create("list.direct.permission.by.user.id");
		sql.params().set("userId", id);
		return searchObj(sql);
	}

	/**
	 * 获取用户的间接权限
	 * 
	 * @author jzshi
	 * @param id
	 *            用户id
	 * @return 角色列表
	 */
	public List<Permission> listIndirectPermissionsByUserId(int id) {
		Sql sql = dao().sqls().create("list.indirect.permission.by.user.id");
		sql.params().set("userId", id);
		return searchObj(sql);
	}

}
