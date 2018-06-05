package com.jz.snake.important.filter.impl.function;


import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.nutz.lang.Strings;

import java.util.List;


/**
 * 左右操作数之间,包含边界
 * 只对数值型属性有效
 *
 */
public class FunctionBetween extends AbstractFunction {
	
	private static final long serialVersionUID = -5164825606682568673L;

	public FunctionBetween(String left, List<String> right) {
		super(left, right);
	}

	public FunctionBetween(String left,String rightVal){
		super(left,rightVal);
	}

	@Override
	public String getName() {
		return "between";
	}
	@Override
	public QueryBuilder toQueryBuilder() {
		
		QueryBuilder query = QueryBuilders.matchAllQuery();
		String left = this.getLeft();
		List<String> right = this.getRight();
		
		if(Strings.isBlank(left) || right == null || right.size() < 2)
			return query;
	
		String leftName = left;
		return QueryBuilders.boolQuery().filter(QueryBuilders.rangeQuery(leftName).gte(right.get(0)).lte(right.get(1)));
	}

	@Override
	public String toSql() {
		String left = this.getLeft();
		List<String> right = this.getRight();
		
		if(Strings.isBlank(left) || right == null || right.size() < 2)
			return "";
	
		StringBuilder sb = new StringBuilder();
		String leftName = left;
		sb.append(leftName);
		
		return new StringBuilder().append(sb)
				 .append(" between ")
				 .append(right.get(0))
				 .append(" and ")
				 .append(right.get(1))
				 .toString();
	}

}
