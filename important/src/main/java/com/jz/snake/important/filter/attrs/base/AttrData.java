package com.jz.snake.important.filter.attrs.base;

import java.io.Serializable;

/**
 * 
* @ClassName: AttrData 
* @Description: 过滤条件属性对象
* @author xuezhi_xu@hansight.com
* @date 2016年8月24日 下午10:41:14 
*
 */
public class AttrData implements Serializable{

	private static final long serialVersionUID = 671593656301295526L;
	public static final String INPUT_TEXT = "text";
	public static final String INPUT_DATATIME = "date";
	public static final String INPUT_SELECT = "select";
	public static final String INPUT_REFERTREE = "referTree";
	public static final String INPUT_DYNAMIC_SELECT = "dynamicSelect";
	
	public static final String TYPE_INTERGER = "integer";
	public static final String TYPE_STRING = "string";
	public static final String TYPE_IP = "ip";
	public static final String TYPE_DATE = "date";

	public static final String OPR_IP = "ip";
	public static final String OPR_NUM = "num";
	public static final String OPR_DATE = "date";
	public static final String OPR_TEXT = "text";
	/**
	 * 字段属性类型
	 */
	protected String type;
	
	/**
	 * 字段属性值类型，字段属性值是select,text, date,referTree后续会有其他的
	 */
	protected String componentType;
	
	/**
	 * 当commonType = select的时候制定attrMap中的哪个值作为options的选项
	 */
	protected String referName;
	/**
	 * 操作符类型
     * 类型：针对每一个属性可以有不同的操作符集合
	 */
	protected String oprs;
	
	/**
	 * 字段属性的中文名称
	 */
	protected String text;
	
	/**
	 * 字段属性的英文名称
	 */
	protected String value;

	/**
	 * 字段是否不可以作为条件添加
	 * @return
	 */
	protected  boolean notCondition;

	/**
	 * 字段是否不可以进行微观分析统计
	 * @return
	 */
	protected boolean notAggreation;


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOprs() {
		return oprs;
	}

	public void setOprs(String oprs) {
		this.oprs = oprs;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

	public String getComponentType() {
		return componentType;
	}

	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	public String getReferName() {
		return referName;
	}

	public void setReferName(String referName) {
		this.referName = referName;
	}

	public boolean isNotCondition() {
		return notCondition;
	}

	public void setNotCondition(boolean notCondition) {
		this.notCondition = notCondition;
	}

	public boolean isNotAggreation() {
		return notAggreation;
	}

	public void setNotAggreation(boolean notAggreation) {
		this.notAggreation = notAggreation;
	}

	public AttrData() {
		
	}
	/**
	 * 属性对象
	 * @param value: 英文名称
	 * @param text: 中文名称
	 * @param type: 属性类型
	 * @param oprs: 属性操作符类型
	 * @param oprs: 值域操作类型
	 * @param referName :引用类型值
	 */
	public AttrData(String value, String text, String type, String oprs ,String commonType,String referName) {
		
		this.value = value;
		this.text = text;
		this.type = type;
		this.oprs = oprs;
		this.componentType = commonType;
		this.referName = referName;
		this.notCondition = false;
		this.notAggreation = false;
	}


	/**
	 * 属性对象
	 * @param value: 英文名称
	 * @param text: 中文名称
	 * @param type: 属性类型
	 * @param oprs: 属性操作符类型
	 * @param oprs: 值域操作类型
	 * @param referName :引用类型值
	 * @param notCondition :是否可以作为过滤条件
	 * @param notAggreation :是否可以被聚合
	 */
	public AttrData(String value, String text, String type, String oprs ,String commonType,String referName,boolean notCondition ,boolean notAggreation) {

		this.value = value;
		this.text = text;
		this.type = type;
		this.oprs = oprs;
		this.componentType = commonType;
		this.referName = referName;
		this.notCondition = notCondition;
		this.notAggreation = notAggreation;
	}
}
