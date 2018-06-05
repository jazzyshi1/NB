package com.jz.snake.important.filter.attrs.structure;


import com.jz.snake.important.filter.attrs.base.AttrDataOption;

import java.util.LinkedHashMap;
import java.util.List;


/**
 * 事件过滤条件属性列表
* @ClassName: EventFilterAttrDataPool 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author xuezhi_xu@hansight.com
* @date 2016年8月24日 下午11:02:18 
*
 */
public class EventFilterAttrDataPool extends  FilterAttrDataPool{ 
	
	private static final long serialVersionUID = 1508952581090655400L;
	/**
	 * 根据事件分类获取该事件分类下的特有属性列表
	 */
	protected LinkedHashMap<String,List<AttrDataOption>> eventTypeAttrsMap = new LinkedHashMap<String,List<AttrDataOption>>();
	
	public EventFilterAttrDataPool() {
		super();
	}

	public LinkedHashMap<String, List<AttrDataOption>> getEventTypeAttrsMap() {
		return eventTypeAttrsMap;
	}

	public void setEventTypeAttrsMap(LinkedHashMap<String, List<AttrDataOption>> eventTypeAttrsMap) {
		this.eventTypeAttrsMap = eventTypeAttrsMap;
	}
	
	
	

}
