package com.jz.snake.important.filter.impl.handle;

import com.jz.snake.important.filter.base.FilterExpression;
import com.jz.snake.important.filter.base.IFilterHandler;
import com.jz.snake.important.filter.base.IFunction;
import com.jz.snake.important.filter.base.OperatorLogic;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.nutz.lang.Strings;

public class DefaultFilterHandler implements IFilterHandler {
	
	private static DefaultFilterHandler instance = new DefaultFilterHandler();
	
	private DefaultFilterHandler(){}
	
	public static IFilterHandler getInstance(){
		return instance;
	}
	

	/**
	 *转换过滤条件为sql语句
	 */
	@Override
	public String toSql(FilterExpression expression) {
		if(expression == null)
			return "";

		StringBuilder result = new StringBuilder();
		OperatorLogic oper = expression.getOperator();
		
		result.append("(");
		switch(oper){
		case BASE:
			IFunction func = expression.getFunc();
			result.append(func.toSql());
			break;
		case NOT:{
			String sql = toSql(expression.getChildren().get(0));
			if(!Strings.isBlank(sql)){
				result.append(" NOT ").append("(").append(sql).append(")");
			}
			break;
		}
		case AND:{
			int index = 0;
			for(FilterExpression expr : expression.getChildren()){
				String sql = toSql(expr);
				if(!Strings.isBlank(sql)){
					if(index++ != 0)
						result.append(" AND ");
				  result.append("(").append(sql).append(")");
				}
			}
			break;
		}
		case OR:{
			int index = 0;
			for(FilterExpression expr : expression.getChildren()){
				String sql = toSql(expr);
				if(!Strings.isBlank(sql)){
					if(index++ != 0)
						result.append(" OR ");
				  result.append("(").append(sql).append(")");
				}
			}
			break;
		}
		}
		result.append(")");
		return result.toString();
	}
	
	@Override
	public QueryBuilder toQueryBuilder(FilterExpression expression) {
		if(expression == null)
			return null;

		BoolQueryBuilder bqb = QueryBuilders.boolQuery();
		OperatorLogic oper = expression.getOperator();
		QueryBuilder qb = null;
		
		switch(oper){
		case BASE:
			IFunction func = expression.getFunc();
			qb = func.toQueryBuilder();
			if(null != qb)
				bqb.must(qb);
			break;
		case NOT:{
			qb = toQueryBuilder(expression.getChildren().get(0));
			if(null != qb){
				bqb.mustNot(qb);
			}
			break;
		}
		case AND:{
			for(FilterExpression expr : expression.getChildren()){
				qb = toQueryBuilder(expr);
				if(null != qb){
					bqb.must(qb);
				}
			}
			break;
		}
		case OR:{
			for(FilterExpression expr : expression.getChildren()){
				qb = toQueryBuilder(expr);
				if(null != qb){
					bqb.should(qb);
				}
			}
			break;
		}
		}
		return bqb;
	}


}
