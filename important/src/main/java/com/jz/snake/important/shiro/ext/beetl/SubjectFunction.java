package com.jz.snake.important.shiro.ext.beetl;

import com.jz.snake.important.shiro.ShiroDemo;
import org.apache.shiro.SecurityUtils;
import org.beetl.core.Context;
import org.beetl.core.Function;
import org.nutz.lang.Lang;

/**
 * @author jzshi
 *
 * @email jz_shi@163.com
 *
 */
public class SubjectFunction implements Function {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.beetl.core.Function#call(java.lang.Object[],
	 * org.beetl.core.Context)
	 */
	@Override
	public Object call(Object[] paras, Context ctx) {
		if (paras != null && paras.length > 0) {
			String key = paras[0].toString();
			Object obj = SecurityUtils.getSubject().getSession().getAttribute(ShiroDemo.SessionKeys.USER_KEY);
			return obj == null ? null : Lang.obj2nutmap(obj).get(key);
		}
		return SecurityUtils.getSubject().getSession().getAttribute(ShiroDemo.SessionKeys.USER_KEY);
	}

}
