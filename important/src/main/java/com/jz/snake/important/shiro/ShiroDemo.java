package com.jz.snake.important.shiro;

import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.mvc.Mvcs;

/**
 * @author jzshi
 *
 * @email jz_shi@163.com
 *
 */
public class ShiroDemo {
	public final static String VERSION = "1.0.0";

	public static class SessionKeys {
		/**
		 * 用戶在session中保存的key
		 */
		public static final String USER_KEY = "JZSHI_USER";
		public static final String WECHAT_USER_KEY = "JZSHI_WECHAT_USER";
	}

	public static String config(String key) {
		return Mvcs.getIoc().get(PropertiesProxy.class, "config").get(key);
	}
}
