package com.jz.snake.important.filter.impl.function;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.nutz.lang.Strings;

import java.util.List;


/**
 * 左操作数以右操作数结束
 *
 */
public class FunctionEndswith extends AbstractFunction{
	private static final long serialVersionUID = -706353386514678884L;

	public FunctionEndswith(String left, List<String> right,boolean isIgnoreCase) {
		super(left, right,isIgnoreCase);
	}

	public FunctionEndswith(String left, List<String> right) {
		super(left, right,false);
	}

	public FunctionEndswith(String left,String rightVal){
		super(left,rightVal);
	}

	@Override
	public String getName() {
		return "endswith";
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
			sb.append(leftName).append(".toLowerCase()");
		}else{
			sb.append(leftName);
		}
		sb.append(" like '%");
		rightValue = rightValue.replaceAll("/", "//").replaceAll("%", "/%").replaceAll("_", "/_");
		if(this.isIgnoreCase())
			sb.append(rightValue.toLowerCase());
		else
			sb.append(rightValue);
		return sb.append("'").append(" ESCAPE '/'").toString();
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
		
		return QueryBuilders.boolQuery().filter(QueryBuilders.wildcardQuery(leftName, "*" +rightValue));
	}
}
