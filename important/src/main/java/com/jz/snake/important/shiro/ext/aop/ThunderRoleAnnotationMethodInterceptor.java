package com.jz.snake.important.shiro.ext.aop;

import org.apache.shiro.aop.AnnotationResolver;
import org.apache.shiro.authz.aop.RoleAnnotationMethodInterceptor;

/**
 * 
 * @author jzshi
 *
 * @email jz_shi@163.com
 *
 */
public class ThunderRoleAnnotationMethodInterceptor extends RoleAnnotationMethodInterceptor {

	public ThunderRoleAnnotationMethodInterceptor() {
		setHandler(new ThunderRoleAnnotationHandler());
	}

	public ThunderRoleAnnotationMethodInterceptor(AnnotationResolver resolver) {
		setHandler(new ThunderRoleAnnotationHandler());
		setResolver(resolver);
	}
}
