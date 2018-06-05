package com.jz.snake.important.filter.attrs.structure;

import com.jz.snake.important.filter.attrs.base.AttrData;
import com.jz.snake.important.filter.attrs.base.AttrDataOption;
import com.jz.snake.important.filter.attrs.base.GeneralTree;
import com.jz.snake.important.filter.attrs.base.Operation;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 过滤条件通用结构体结构
* @ClassName: FilterAttrDataPool 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author xuezhi_xu@hansight.com
* @date 2016年8月24日 下午10:51:08 
*
 */
public  class FilterAttrDataPool implements Serializable{

	private static final long serialVersionUID = -1528300806771163645L;
	
	/**
	 * 首先取到operationMap然后 根据commonType分别取不同的操作符列表
	 */
	protected LinkedHashMap<String ,Map<String,Operation>> operationMap = new LinkedHashMap<String,Map<String,Operation>>();
	
	/**
	 * 所有的属性列表
	 */
	protected LinkedHashMap<String, AttrData> attrDatasMap = new LinkedHashMap<String, AttrData>();
	
	/**
	 * 所有select属性列表的 备选项列表集合
	 */
	protected LinkedHashMap<String, List<AttrDataOption>> optionDatasMap = new LinkedHashMap<String, List<AttrDataOption>>();

	/**
	 * 所有component为dynamicSelect属性列表的备选列表
	 */
	protected LinkedHashMap<String,List<AttrDataOption>> dynamicOptionDatasMap = new LinkedHashMap<>();

	/**
	 * 所有component为referTree属性列表的备选树列表
	 */
	protected LinkedHashMap<String,List<GeneralTree>> referOptionDatasMap = new LinkedHashMap<>();
	

	public LinkedHashMap<String, Map<String, Operation>> getOperationMap() {
		return operationMap;
	}

	public void setOperationMap(LinkedHashMap<String, Map<String, Operation>> operationMap) {
		this.operationMap = operationMap;
	}

	public LinkedHashMap<String, AttrData> getAttrDatasMap() {
		return attrDatasMap;
	}

	public void setAttrDatasMap(LinkedHashMap<String, AttrData> attrDatasMap) {
		this.attrDatasMap = attrDatasMap;
	}

	public LinkedHashMap<String, List<AttrDataOption>> getOptionDatasMap() {
		return optionDatasMap;
	}

	public void setOptionDatasMap(LinkedHashMap<String, List<AttrDataOption>> optionDatasMap) {
		this.optionDatasMap = optionDatasMap;
	}

	public LinkedHashMap<String, List<GeneralTree>> getReferOptionDatasMap() {
		return referOptionDatasMap;
	}

	public void setReferOptionDatasMap(LinkedHashMap<String, List<GeneralTree>> referOptionDatasMap) {
		this.referOptionDatasMap = referOptionDatasMap;
	}

	public LinkedHashMap<String, List<AttrDataOption>> getDynamicOptionDatasMap() {
		return dynamicOptionDatasMap;
	}

	public void setDynamicOptionDatasMap(LinkedHashMap<String, List<AttrDataOption>> dynamicOptionDatasMap) {
		this.dynamicOptionDatasMap = dynamicOptionDatasMap;
	}
}
