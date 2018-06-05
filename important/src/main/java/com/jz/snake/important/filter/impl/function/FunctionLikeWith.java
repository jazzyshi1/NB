package com.jz.snake.important.filter.impl.function;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.nutz.lang.Strings;

import java.util.List;

/**
 * @author xuezhi_xu@hansight.com
 * @date 17/1/9 下午2:26
 */

public class FunctionLikeWith extends AbstractFunction{

    private static final long serialVersionUID = 1195907693730199416L;

    public FunctionLikeWith(String left, List<String> right, boolean isIgnoreCase) {
        super(left, right, isIgnoreCase);
    }

    public FunctionLikeWith(String left, List<String> right) {
        super(left, right, false);
    }

    public FunctionLikeWith(String left,String rightVal){
        super(left,rightVal);
    }

    @Override
    public String getName(){
        return "like";
    }

    @Override
    public String toSql() {
        String left = this.getLeft();
        List<String> right = this.getRight();
        if(Strings.isBlank(left) || right == null || right.size() <= 0)
            return "";

        StringBuilder sb = new StringBuilder();
        String leftName = left;
        String rightValue = right.get(0);

        if(isIgnoreCase()){
            sb.append("lower(").append(leftName).append(")");
        }else{
            sb.append(leftName);
        }
        sb.append(" like '");
        sb.append("%");
        rightValue = rightValue.replaceAll("/", "//").replaceAll("%", "/%").replaceAll("_", "/_");
        if(this.isIgnoreCase())
            sb.append(rightValue.toLowerCase());
        else
            sb.append(rightValue);
        return sb.append("%'").append(" ESCAPE '/'").toString();
    }

    @Override
    public QueryBuilder toQueryBuilder() {

        String left = this.getLeft();
        List<String> right = this.getRight();
        if(Strings.isBlank(left) || right == null || right.size() <= 0)
            return QueryBuilders.matchAllQuery();

        String leftName = left;
        String rightValue = right.get(0);

        return QueryBuilders.boolQuery().filter(QueryBuilders.wildcardQuery(leftName, "*"+rightValue+"*"));
    }
}
