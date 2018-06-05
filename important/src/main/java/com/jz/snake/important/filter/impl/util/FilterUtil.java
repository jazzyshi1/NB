package com.jz.snake.important.filter.impl.util;

import com.jz.snake.important.filter.base.FilterExpression;
import com.jz.snake.important.filter.base.IFunction;
import com.jz.snake.important.filter.impl.function.AbstractFunction;
import org.apache.log4j.Logger;
import org.nutz.resource.Scans;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class FilterUtil {

	
	private static final Logger logger = Logger.getLogger(FilterUtil.class);
	/**
	 * 过滤器实现类所在的包名
	 */
	private static final String FUNCTION_PACKAGE_NAME = "com.jz.snake.important.filter.impl.function";
	

	/**
	 * 将 Filter.class表达式相关的类注册到 xsteam,以缩短表达式字符串
	 */
	static{
		registerFunc(FUNCTION_PACKAGE_NAME,IFunction.class);
		
		SerializeUtil.registerClass(FilterExpression.class);
		SerializeUtil.implicitCollection(AbstractFunction.class,"right");
		SerializeUtil.registerClass("right",String.class);
		
	}
	
	/**
	 * 将 Object 转换成 xml
	 * @param obj
	 * @return
	 */
	public static String toXml(Object obj){
		return SerializeUtil.getInstance().toXml(obj);
	}
	
	/**
	 * 将 xml 转换成 Object
	 * @param xml
	 * @return
	 */
	public static Object fromXml(String xml){
		return SerializeUtil.getInstance().fromXml(xml);
	}
	
	public static String toJson(Object obj) {
		return SerializeUtil.getInstance().toJson(obj);
	}
	
	public static Object fromJson(String json) {
		return SerializeUtil.getInstance().fromJson(json);
	} 
	
	@SuppressWarnings({ "rawtypes", "unchecked" }) 
	public static List<String> getSimpleNameList(String packageName, Class interfaceClass) {
		List<String> list = new ArrayList<String>();
		List<Class<?>> classes = Scans.me().scanPackage(packageName);
		for(Class clazz : classes){
			if (!clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers()) && interfaceClass.isAssignableFrom(clazz)) {
				try{
					list.add(clazz.getSimpleName());
				}catch(Exception ex){
					logger.error("register class " + clazz.getSimpleName() + " error:" + ex.getMessage(),ex);
				}
			}
		}		
		return list;
	}
	
	/**
	 * 将所有的实现类注册到 xsteam 
	 * @param packageName 接口所在的包名
	 * @param interfaceClass 接口类,比如: IFunction.class,IAlertAction.class
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void registerFunc(String packageName,Class interfaceClass){
		List<Class<?>> classes = Scans.me().scanPackage(packageName);
		for(Class clazz : classes){
			if (!clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers()) && interfaceClass.isAssignableFrom(clazz)) {
				try{
					SerializeUtil.registerClass(clazz);
				}catch(Exception ex){
					logger.error("register func " + clazz.getSimpleName() + " error:" + ex.getMessage(),ex);
				}
			}
		}
	}

	/**
	 * 将包里所有的类注册到 xsteam ,除抽象类与接口类
	 * @param packageName 接口所在的包名
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	private static void registerClass(String packageName){
		List<Class<?>> classes = Scans.me().scanPackage(packageName);
		for(Class clazz : classes){
			if (!clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers())) {
				try{
					SerializeUtil.registerClass(clazz);
				}catch(Exception ex){
					logger.error("register func " + clazz.getSimpleName() + " error:" + ex.getMessage(),ex);
				}
			}
		}
	}
}
