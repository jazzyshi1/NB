package com.jz.snake.important.filter.impl.function;

import com.jz.snake.important.filter.base.IFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * IFunction 的抽象实现
 *
 */
public abstract class AbstractFunction implements IFunction {
	
	private static final long serialVersionUID = -9012788808571718417L;

	/**
	 * 左操作数
	 */
	protected String left;
	
	/**
	 * 右操作数
	 */
	
	protected List<String> right;
	
	/**
	 * 是否忽略大小写
	 */
	protected boolean isIgnoreCase;

	/**
	 * @param left 左操作数
	 * @param right 右操作数
	 * @param isIgnoreCase 是否忽略大小写,只对字符串类型的操作符生效
	 */
	protected AbstractFunction(String left,List<String> right,boolean isIgnoreCase){
		this.left = left;
		this.right = right;
		this.isIgnoreCase = isIgnoreCase;

	}

	protected AbstractFunction(String left,String rightVal){
		this.left = left;
		if(null == this.right){
			this.right = new ArrayList<String>();
		}
		if(null != rightVal){
			this.right.add(rightVal);
		}
	}
	
	protected AbstractFunction(String left,List<String> right){
		this(left,right,false);
	}
	
	/**
	 * @param right 只有右操作数,比如 :匹配过滤器
	 */
	protected AbstractFunction(List<String> right){
		this(null, right, false);
	}
	
	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public List<String>  getRight() {
		return right;
	}

	public void setRight(List<String>  right) {
		this.right = right;
	}

	public boolean isIgnoreCase() {
		return isIgnoreCase;
	}

	public void setIgnoreCase(boolean isIgnoreCase) {
		this.isIgnoreCase = isIgnoreCase;
	}

}

