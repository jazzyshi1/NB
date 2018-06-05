package com.jz.snake.important.filter.impl.function;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.nutz.lang.Strings;

import java.util.List;

/**
 * 是否为空,包括空字符串
 * 右操作符是 true/yes/y (忽略大小写) 中的任意一个表示‘是',其它均表示'否'
 *
 */
public class FunctionIsnull extends AbstractFunction {
	private static final long serialVersionUID = -2531535626206646620L;

	public FunctionIsnull(String left, List<String> right) {
		super(left, right);
	}

	public FunctionIsnull(String left,String rightVal){
		super(left,rightVal);
	}

	@Override
	public String getName() {
		return "isnull";
	}

	
	@Override
	public String toSql(){
		String left = this.getLeft();
		List<String> right = this.getRight();
		if(Strings.isBlank(left) || right == null || right.size() <= 0)
			return "";
	
		String rightValue = this.getRight().get(0);
		StringBuilder sb = new StringBuilder();
		String leftName = left;
		sb.append(leftName);
		if("true".equalsIgnoreCase(rightValue)
						|| "yes".equalsIgnoreCase(rightValue)
						|| "y".equalsIgnoreCase(rightValue)){
			sb.append(" = ").append(Integer.MAX_VALUE);
			sb.append(" OR trim("+ leftName + ") = ''");
		}else{
			//sb.append(" is not null");
			sb.append(" != ").append(Integer.MAX_VALUE);
			sb.append(" AND trim("+ leftName + ") != ''");
		}
		return sb.toString();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public QueryBuilder toQueryBuilder() {
		
		String left = this.getLeft();
		List<String> right = this.getRight();
		if(Strings.isBlank(left) || right == null || right.size() <= 0)
			return QueryBuilders.matchAllQuery();	
		
		String leftName = left;
		String rightValue = right.get(0);
		
		if("true".equalsIgnoreCase(rightValue)
				|| "yes".equalsIgnoreCase(rightValue)
				|| "y".equalsIgnoreCase(rightValue)){
			return QueryBuilders.boolQuery().filter(QueryBuilders.boolQuery().mustNot(QueryBuilders.existsQuery(leftName)));
			
		}else{
			return QueryBuilders.boolQuery().filter(QueryBuilders.existsQuery(leftName));
		}
		
	}
	
}
