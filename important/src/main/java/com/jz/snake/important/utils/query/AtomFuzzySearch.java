/**
 * Copyright: 瀚思安信（北京）软件技术有限公司，保留所有权利。
 * Author: neven (pengyu_yang#hansight.com)
 * Created: 2016年07月30日
 */
package com.jz.snake.important.utils.query;

import java.util.List;

/**
 * 模糊查询实体类
 * Author: neven (pengyu_yang@hansight.com)
 * Created: 2016年07月30日
 */
public class AtomFuzzySearch {

	private Class<?> clazz;

	/**
     * 模糊查询属性列表
	 */
	private List<String> fields;

	/**
     * 模糊查询输入字符串
	 */
	private String value;

	public AtomFuzzySearch(Class<?> clazz, String value) {
		this.value = "%" + value + "%";
	}

	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
