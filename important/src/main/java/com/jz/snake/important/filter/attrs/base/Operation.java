package com.jz.snake.important.filter.attrs.base;

import java.io.Serializable;

/**
 * 操作符对象
* @ClassName: Operation 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author xuezhi_xu@hansight.com
* @date 2016年8月24日 下午10:45:58 
*
 */
public class Operation  implements Serializable{
	
	private static final long serialVersionUID = 1756030065301716074L;
	
	public static final String OPR_SINGLE = "single";
	public static final String OPR_COUPLE = "couple";
	public static final String OPR_MULTIPLE = "multiple";
	public static final String OPR_ISNULL = "IS_NULL";

	/**
	 * 操作符值（<,>,=...）
	 */
	protected String value;
	
	/**
	 * 操作符中文名称（小于,大于,等于...）
	 */
	protected String text;
	
	/**
	 * 操作符的操作数类型：单个-single，成对-couple，多个-multiple，是否为空-IS_NULL
	 */
	protected String oprands;
	
	

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getOprands() {
		return oprands;
	}

	public void setOprands(String oprands) {
		this.oprands = oprands;
	}

	public Operation(String value, String text, String oprands) {
		this.value = value;
		this.text = text;
		this.oprands = oprands;
	}

	public Operation() {
	}

}
