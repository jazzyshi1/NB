package com.jz.snake.important.filter.impl.function;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.nutz.lang.Strings;

import java.util.List;

/**
 * in 函数,可以是整数,也可以是字符
 * 目前没有对浮点数做处理,因为应用中可能没有这种需求
 *
 */
public class FunctionIn extends AbstractFunction {
	
	private static final long serialVersionUID = -7780573755752681000L;
	

	public FunctionIn(String left, List<String> right, boolean isIgnoreCase) {
		super(left, right, isIgnoreCase);
	}

	public FunctionIn(String left, List<String> right) {
		super(left, right,false);
	}

	public FunctionIn(String left,String rightVal){
		super(left,rightVal);
	}

	@Override
	public String getName() {
		return "in";
	}

	

	@Override
	public String toSql() {

		String left = this.getLeft();
		List<String> right = this.getRight();
		String fieldName = left;
		if(Strings.isBlank(fieldName) || right == null || right.size() <= 0)
			return "";
	
		StringBuilder sb = new StringBuilder();
		String leftName = fieldName;

		if(isIgnoreCase()){
			sb.append("lower(").append(leftName).append(")");
		}else{
			sb.append(leftName);
		}

		sb.append(" in (");
		int index = 0;
		for(String rightValue : this.getRight()){
			String value = rightValue;

			if(index++ != 0)
				sb.append(",");
			if(!FuctionUtil.isNumber(left)){

				sb.append("'");
				if(this.isIgnoreCase())
					sb.append(value.toLowerCase());
				else
					sb.append(value);

				sb.append("'");

			}else{
				sb.append(value);
			}
		}
		return sb.append(")").toString();
	}

	@Override
	public QueryBuilder toQueryBuilder() {
	
		String left = this.getLeft();
		List<String> right = this.getRight();
		if(Strings.isBlank(left) || right == null || right.size() <= 0)
			return QueryBuilders.matchAllQuery();	
		
		String leftName = left;
		List<String> rightValue = right;
		return QueryBuilders.boolQuery().filter(QueryBuilders.termsQuery(leftName, rightValue));
	}
}
