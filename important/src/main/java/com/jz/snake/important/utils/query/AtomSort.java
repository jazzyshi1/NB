/**
 * Copyright: 瀚思安信（北京）软件技术有限公司，保留所有权利。
 * Author: neven (pengyu_yang#hansight.com)
 * Created: 2016年07月30日
 */
package com.jz.snake.important.utils.query;

/**
 * 排序实体类
 * Author: neven (pengyu_yang@hansight.com)
 * Created: 2016年07月30日
 */
public class AtomSort {
	/**
     * 排序-正序
	 */
	public static final String ORDER_ASC = "asc";

    /**
     * 排序-倒序
	 */
	public static final String ORDER_DESC = "desc";

	/**
	 * 序号
	 */
	private int index;

    /**
     * 属性名称
	 */
	private String field;

    /**
     * 排序
	 */
	private String order;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * 
	 */
	public AtomSort() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param field
	 * @param order
	 */
	public AtomSort(String field, String order) {
		super();
		this.field = field;
		this.order = order;
	}
	
	
}
