package com.jz.snake.important.filter.attrs.base;

import java.io.Serializable;

/**
 * 属性值备选值对象
* @ClassName: AttrDataOption 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author xuezhi_xu@hansight.com
* @date 2016年8月24日 下午10:43:16 
*
 */
public class AttrDataOption implements Serializable{
	
	private static final long serialVersionUID = -6616152932466363044L;
	
	/**
     *具体属性英文名称
     */
    protected String value;

    /**
     * 具体属性中文名称
     */
    protected String text;
	
	public AttrDataOption(String value, String text) {
		this.value = value;
		this.text = text;
	}
	
	public AttrDataOption() {
		
	}

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
	
	
}
