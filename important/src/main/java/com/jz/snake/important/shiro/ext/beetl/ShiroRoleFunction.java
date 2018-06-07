package com.jz.snake.important.shiro.ext.beetl;

import org.apache.shiro.SecurityUtils;
import org.beetl.core.Context;
import org.beetl.core.Function;

/**
 * 
 * @author jzshi
 *
 * @email jz_shi@163.com
 *
 */
public class ShiroRoleFunction implements Function {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.beetl.core.Function#call(java.lang.Object[],
	 * org.beetl.core.Context)
	 */
	@Override
	public Object call(Object[] role, Context context) {
		if (role == null || role.length < 1) {
			return false;
		}
		return SecurityUtils.getSubject() != null && SecurityUtils.getSubject().hasRole(role[0].toString());
	}

}
