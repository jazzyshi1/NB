/**
 * Copyright: 瀚思安信（北京）软件技术有限公司，保留所有权利。
 * Author: neven (pengyu_yang#hansight.com)
 * Created: 2016年07月30日
 */
package com.jz.snake.important.utils.query;

import com.jz.snake.important.filter.base.FilterExpression;
import com.jz.snake.important.filter.impl.handle.DefaultFilterHandler;
import com.jz.snake.important.filter.impl.util.FilterUtil;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.util.cri.SimpleCriteria;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.dao.util.cri.Static;
import org.nutz.lang.Strings;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 查询排序SQL工具类
 * Author: neven (pengyu_yang@hansight.com)
 * Created: 2016年07月30日
 */
public class AtomCndTool {

	/**
	 * 构建模糊查询、过滤器查询、排序SQL
	 * @param clazz 模糊查询类的class
	 * @param atomQuery 分页对象
	 * @return
	 */
	public static SimpleCriteria makeCnd(Class<?> clazz, AtomQuery atomQuery) {

        Cnd cnd = null;

        String customSql = atomQuery.getCustomSql();

        //如果自定义sql不为空,需要校验之后完善
        if(!Strings.isEmpty(customSql)){
            cnd = Cnd.where(new Static(customSql));
        }else{
            cnd = Cnd.where("1" ,"=",1);
        }

        /**
         * 过滤条件属性
         */
        String filterExpressionStr = atomQuery.getCommonFilters();
         if(!Strings.isEmpty(filterExpressionStr)){
            FilterExpression expression = (FilterExpression) FilterUtil.fromJson(filterExpressionStr);
            if(null != expression){
                String experssionSql = DefaultFilterHandler.getInstance().toSql(expression);
                if(!Strings.isEmpty(experssionSql)){
                        cnd.and(new Static(experssionSql));
                }
            }
        }

        /***
         * 构建模糊查询sql
         */
        AtomFuzzySearch fuzzy = new AtomFuzzySearch(clazz, atomQuery.getFuzzySearch());
        SqlExpressionGroup fuzzySearchExpression = null;
        if ( fuzzy.getValue() != null && !"".equals(fuzzy.getValue())
                && fuzzy.getFields() != null && !fuzzy.getFields().isEmpty()) {
            fuzzySearchExpression = Cnd.exps("1", "!=", 1);
            for (String field : fuzzy.getFields()) {
                fuzzySearchExpression.or(getColumn(clazz, field), "like", fuzzy.getValue());
            }
        }

        //构建过滤器查询sql
        List<AtomFilter> filters = atomQuery.getFilters();
        SqlExpressionGroup filterExpression = Cnd.exps("1", "=", 1);
        if ( filters != null && filters.size() > 0 ) {
            for (AtomFilter filter : filters) {
                String opera = filter.getOperator();
                if (!Strings.isEmpty(opera)) {
                    if("like".equalsIgnoreCase(opera)){
                        String condValue = "%".concat(Sqls.escapteConditionValue(filter.getValue().toString()).toString()).concat("%");
                        filterExpression.and(getColumn(clazz, filter.getField()), filter.getOperator(),condValue);

                    } else {
                        filterExpression.and(getColumn(clazz, filter.getField()), filter.getOperator(), filter.getValue());
                    }
                }
            }
        }

        //构建排序sql
        List<AtomSort> sorts = atomQuery.getSorts();
        SimpleCriteria cri = null;
        if(fuzzySearchExpression == null){
            cri = cnd.and(filterExpression).getCri();
        } else {
            cri = cnd.and(filterExpression).and(fuzzySearchExpression).getCri();
        }
        if (sorts != null && sorts.size() > 0) {
            for (AtomSort sort : sorts) {
                if (AtomSort.ORDER_ASC.equals(sort.getOrder())) {
                    cri.getOrderBy().asc(sort.getField());
                } else if (AtomSort.ORDER_DESC.equals(sort.getOrder())) {
                    cri.getOrderBy().desc(sort.getField());
                } else {

                }
            }
        }
        return cri;
	}

    public static SimpleCriteria makeCnd(Class<?> clazz, AtomQuery atomQuery,String taskFunctionType) {

        Cnd cnd = null;

        String customSql = atomQuery.getCustomSql();

        //如果自定义sql不为空,需要校验之后完善
        if(!Strings.isEmpty(customSql)){
            cnd = Cnd.where(new Static(customSql));
        }else{
            cnd = Cnd.where("1" ,"=",1);
        }

        /**
         * 过滤条件属性
         */
        if(!Strings.isEmpty(taskFunctionType)){
            cnd.and(new Static("(task_function_type='" + taskFunctionType +"')"));
        }

        /***
         * 构建模糊查询sql
         */
        AtomFuzzySearch fuzzy = new AtomFuzzySearch(clazz, atomQuery.getFuzzySearch());
        SqlExpressionGroup fuzzySearchExpression = null;
        if ( fuzzy.getValue() != null && !"".equals(fuzzy.getValue())
                && fuzzy.getFields() != null && !fuzzy.getFields().isEmpty()) {
            fuzzySearchExpression = Cnd.exps("1", "!=", 1);
            for (String field : fuzzy.getFields()) {
                fuzzySearchExpression.or(getColumn(clazz, field), "like", fuzzy.getValue());
            }
        }

        //构建过滤器查询sql
        List<AtomFilter> filters = atomQuery.getFilters();
        SqlExpressionGroup filterExpression = Cnd.exps("1", "=", 1);
        if ( filters != null && filters.size() > 0 ) {
            for (AtomFilter filter : filters) {
                String opera = filter.getOperator();
                if (!Strings.isEmpty(opera)) {
                    if("like".equalsIgnoreCase(opera)){
                        String condValue = "%".concat(Sqls.escapteConditionValue(filter.getValue().toString()).toString()).concat("%");
                        filterExpression.and(getColumn(clazz, filter.getField()), filter.getOperator(),condValue);

                    } else {
                        filterExpression.and(getColumn(clazz, filter.getField()), filter.getOperator(), filter.getValue());
                    }
                }
            }
        }

        //构建排序sql
        List<AtomSort> sorts = atomQuery.getSorts();
        SimpleCriteria cri = null;
        if(fuzzySearchExpression == null){
            cri = cnd.and(filterExpression).getCri();
        } else {
            cri = cnd.and(filterExpression).and(fuzzySearchExpression).getCri();
        }
        if (sorts != null && sorts.size() > 0) {
            for (AtomSort sort : sorts) {
                if (AtomSort.ORDER_ASC.equals(sort.getOrder())) {
                    cri.getOrderBy().asc(sort.getField());
                } else if (AtomSort.ORDER_DESC.equals(sort.getOrder())) {
                    cri.getOrderBy().desc(sort.getField());
                } else {

                }
            }
        }

        return cri;
    }

    private static String getColumn(Class<?> clazz, String fieldName) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if(fieldName.equals(field.getName())){
                Column column = field.getAnnotation(Column.class);
                if (("").equals(column.value())) {
                    return fiedToColmun(field.getName());
                } else {
                    return column.value();
                }
            }
        }

        return fieldName;
    }

    private static String fiedToColmun(String str) {

        for (int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if( c >= 65 && c <= 90) {
                str = str.replace(String.valueOf(c), "_" + String.valueOf(c).toLowerCase());
            }
        }
        return str;
    }


}
