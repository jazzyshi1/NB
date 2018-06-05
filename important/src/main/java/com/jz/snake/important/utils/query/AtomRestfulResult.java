/**
 * Copyright: 瀚思安信（北京）软件技术有限公司，保留所有权利。
 * Author: neven (pengyu_yang@hansight.com)
 * Created: 2016年08月01日
 */
package com.jz.snake.important.utils.query;

import java.util.ArrayList;
import java.util.List;

/**
 * Restful返回结果实体类
 * Author: neven (pengyu_yang@hansight.com)
 * Created: 2016年08月01日
 */
public class AtomRestfulResult {

	/**
     * 状态码-操作成功
	 */
	public static final int STATUS_CODE_SUCCESS = 0;

	/**
	 * 状态码-操作失败
	 */
	public static final int STATUS_CODE_ERROR = 1;

    /**
     * 状态码-没有权限
	 */
	public static final int STATUS_CODE_NO_PERMISSION = 2;

    /**
     * 状态码-回话超时
	 */
	public static final int STATUS_CODE_SESSION_TIMEOUT = 3;
	
	/**
	 * License不合法
	 */
	public static final int STATUS_CODE_LICENSE_ILLEGAL = 1001;

	/**
	 * 状态吗
	 */
	private int statusCode;

	/**
     * 操作描述信息数组
	 */
	private List<String> messages = new ArrayList<>();

    /**
     * 返回数据
	 */
	private Object data;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public void addMessages(String message) {
		messages.add(message);
	}

	public List<String> getMessages() {
		return messages;
	}

	public Object getData() {
		return data;
	}

	/**
	 * 返回数据ID
	 * @param id
	 */
	public void addData(String id){
		this.data = new AtomRestfulResultId(id);
	}

	/**
	 * 返回一个数据对象或者对象列表
	 * @param object
	 */
	public void addData(Object object){
		this.data = object;
	}


}

class AtomRestfulResultId {
	private String id;

	public AtomRestfulResultId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
