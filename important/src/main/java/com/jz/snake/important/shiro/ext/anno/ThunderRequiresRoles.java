package com.jz.snake.important.shiro.ext.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.jz.snake.important.shiro.InstalledRole;
import org.apache.shiro.authz.annotation.Logical;

/**
 * 
 * @author jzshi
 *
 * @email jz_shi@163.com
 *
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ThunderRequiresRoles {
	Logical logical() default Logical.AND;

	InstalledRole[] value();
}
