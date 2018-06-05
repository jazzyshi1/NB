package com.jz.snake.important.filter.impl.function;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.nutz.lang.Strings;

import java.util.List;

/**
 * 不等于,包括数字与字符串
 *
 */
public class FunctionNE extends AbstractFunction {
	private static final long serialVersionUID = -9183692871518843069L;

	public FunctionNE(String left,List<String> right,boolean isIgnoreCase){
		super(left,right,isIgnoreCase);
	}
	
	public FunctionNE(String left,List<String> right){
		super(left,right,false);
	}

	public FunctionNE(String left,String rightVal){
		super(left,rightVal);
	}
	
	@Override
	public String getName() {
		return "!=";
	}

	@Override
	public String toSql(){
		String left = this.getLeft();
		List<String> right = this.getRight();
		if(Strings.isBlank(left) || right == null || right.size() <= 0)
			return "";
	
		StringBuilder sb = new StringBuilder();
		String leftName = left;
		String rightValue = right.get(0);

		sb.append("not (");
		if(isIgnoreCase()){
			sb.append("lower(").append(leftName).append(")");
		}else{
			sb.append(leftName);
		}
		sb.append("=");
		if(this.isIgnoreCase())
			rightValue = rightValue.toLowerCase();
		
		if(!FuctionUtil.isNumber(left)  ){
			sb.append("'");
			sb.append(rightValue);
			sb.append("'");
		}else{
			sb.append(rightValue);
		}
		//当数据库中存在值为null的字段时，通过not(item = '')查询无法获取值为null的数据，因此加null判断
		return sb.append(")").append(" or ").append(leftName).append(" is null").toString();
	}

	@Override
	public QueryBuilder toQueryBuilder() {
		
		String left = this.getLeft();
		List<String> right = this.getRight();
		if(Strings.isBlank(left) || right == null || right.size() <= 0)
			return QueryBuilders.matchAllQuery();	
		
		String leftName = left;
		String rightValue = right.get(0);
		
		return QueryBuilders.boolQuery().filter(QueryBuilders.boolQuery().mustNot(QueryBuilders.termQuery(leftName, rightValue)));

	}
	

}
