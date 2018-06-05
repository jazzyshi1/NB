package com.jz.snake.important.filter.base;
import org.elasticsearch.index.query.QueryBuilder;

public interface IFilterHandler {
	/**
	 * 将过滤器表达式转换成 sql条件语句,格式如： ((event.lid > 1000 AND event.iport = 8080) OR (NOT (lid = 1001)))
	 * @param expression
	 * @return
	 */ 
	public String toSql(FilterExpression expression);
	
	/**
	 * 将过滤器表达式转换为QueryBuilder
	 * @param expression
	 * @return
	 */ 
	public QueryBuilder toQueryBuilder(FilterExpression expression);
	
}
