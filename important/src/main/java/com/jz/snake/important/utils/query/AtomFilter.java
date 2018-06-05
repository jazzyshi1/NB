/**
 * Copyright: 瀚思安信（北京）软件技术有限公司，保留所有权利。
 * Author: neven (pengyu_yang#hansight.com)
 * Created: 2016年07月30日
 */
package com.jz.snake.important.utils.query;

/**
 * 过滤器实体类
 * Author: neven (pengyu_yang@hansight.com)
 * Created: 2016年07月30日
 */
public class AtomFilter {

	/**
     * 属性名
	 */
	private String field;

	/**
     * 逻辑运算符
	 */
	private String operator;

	/**
	 * 值
	 */
	private Object value;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * 
	 */
	public AtomFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param field
	 * @param operator
	 * @param value
	 */
	public AtomFilter(String field, String operator, Object value) {
		super();
		this.field = field;
		this.operator = operator;
		this.value = value;
	}
}
