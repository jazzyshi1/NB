package com.jz.snake.important.filter.base;

import org.elasticsearch.index.query.QueryBuilder;

import java.io.Serializable;

/**
 * 函数接口,所有的子函数必须实现它,比如 = | != | > | >= |...
 *
 */
public interface IFunction extends Serializable {

	public String getName();
	
	/**
	 * 将 Function 转换成 sql 条件格式
	 * @return
	 */
	public String toSql();
	
	/**
	 * @return
	 */
	public QueryBuilder toQueryBuilder();
	
}
