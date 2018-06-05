package com.jz.snake.important.filter.impl.function;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.nutz.lang.Strings;

import java.util.List;

/**
 * 等于,包括数字与字符串
 *
 */
public class FunctionEQ extends AbstractFunction {

	private static final long serialVersionUID = -8154324697006537996L;

	public FunctionEQ(String left,List<String>  right,boolean isIgnoreCase){
		super(left,right,isIgnoreCase);
	}
	
	public FunctionEQ(String left,List<String>  right){
		super(left,right,false);
	}

	public FunctionEQ(String left,String rightVal){
		super(left,rightVal);
	}
	
	@Override
	public String getName() {
		return "eq";
	}

	@Override
	public String toSql(){
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
		sb.append("=");
		if(this.isIgnoreCase())
			rightValue = rightValue.toLowerCase();

		if(!FuctionUtil.isNumber(left)){
			sb.append("'");
			sb.append(rightValue);
			sb.append("'");
		}else
			sb.append(rightValue);
		
		return sb.toString();
	}
	
	@Override
	public QueryBuilder toQueryBuilder() {
		
		String left = this.getLeft();
		List<String> right = this.getRight();
		if(Strings.isBlank(left) || right == null || right.size() <= 0)
			return QueryBuilders.matchAllQuery();	
		
		String leftName = left;
		String rightValue = right.get(0);
		
		return QueryBuilders.boolQuery().filter(QueryBuilders.termQuery(leftName,rightValue));
	}
	
}
