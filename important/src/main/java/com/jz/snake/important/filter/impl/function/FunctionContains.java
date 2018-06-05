package com.jz.snake.important.filter.impl.function;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.nutz.lang.Strings;

import java.util.List;

/**
 * 左操作数是否包含右操作数
 *
 */
public class FunctionContains extends AbstractFunction {
	private static final long serialVersionUID = -5012649087849628717L;

	public FunctionContains(String left, List<String> right,boolean isIgnoreCase) {
		super(left, right, isIgnoreCase);
	}

	public FunctionContains(String left, List<String> right) {
		super(left, right, false);
	}

	public FunctionContains(String left,String rightVal){
		super(left,rightVal);
	}
	
	@Override
	public String getName() {
		return "contains";
	}

	@Override
	public String toSql() {
		String left = this.getLeft();
		List<String> right = this.getRight();
		if(Strings.isBlank(left) || right == null || right.size() <= 0)
			return "";
	
		StringBuilder sb = new StringBuilder();
		String leftName = left;
		String rightValue = null;
		
		rightValue = right.get(0);

		if(isIgnoreCase()){
			sb.append("lower(").append(leftName).append(")");
		}else{
			sb.append(leftName);
		}
		sb.append(" like '%");
		if(isIgnoreCase()){
			sb.append(rightValue.toLowerCase());
		}else{
			sb.append(rightValue);
		}	
		sb.append("%'").toString();
		
		return sb.toString();
	}

	/**
	 * 性能低,不建议使用
	 */
	@Override
	public QueryBuilder toQueryBuilder() {
		String left = this.getLeft();
		List<String> right = this.getRight();
		if(Strings.isBlank(left) || right == null || right.size() <= 0)
			return QueryBuilders.matchAllQuery();	
		
		String leftName = left;
		String rightValue = right.get(0);
		
		return QueryBuilders.boolQuery().filter(QueryBuilders.wildcardQuery(leftName, "*" +rightValue+"*"));

	}


}
