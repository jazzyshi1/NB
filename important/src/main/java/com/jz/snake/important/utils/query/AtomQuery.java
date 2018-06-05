/**
 * Copyright: 瀚思安信（北京）软件技术有限公司，保留所有权利。
 * Author: neven (pengyu_yang@hansight.com)
 * Created: 2016年08月04日
 */
package com.jz.snake.important.utils.query;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询实体类
 * Author: neven (pengyu_yang@hansight.com)
 * Created: 2016年08月04日
 */
public class AtomQuery {

    /**
     * 模糊查询输入字符串
     */
    private String fuzzySearch;

    /**
     * 过滤器数组
     */
    private List<AtomFilter> filters = new ArrayList<AtomFilter>();

    /**
     * 是否分页查询，true：分页查询，false：返回全部数据
     */
    private boolean paginate;

    /**
     * 分页参数对象
     */
    private AtomPagination pagination;

    /**
     * 排序对象数组
     */
    private List<AtomSort> sorts;

    /**
     * 自定义sql条件
     */
    private String customSql;

    /**
     * 通用过滤条件字符串
     * @return
     */
    private String commonFilters;


    public String getFuzzySearch() {
        return fuzzySearch;
    }

    public void setFuzzySearch(String fuzzySearch) {
        this.fuzzySearch = fuzzySearch;
    }

    public List<AtomFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<AtomFilter> filters) {
		this.filters = filters;
	}

	public boolean isPaginate() {
        return paginate;
    }

    public void setPaginate(boolean paginate) {
        this.paginate = paginate;
    }

    public AtomPagination getPagination() {
        return pagination;
    }

    public void setPagination(AtomPagination pagination) {
        this.pagination = pagination;
    }

    public List<AtomSort> getSorts() {
        return sorts;
    }

    public void setSorts(List<AtomSort> sorts) {
        this.sorts = sorts;
    }

    public String getCustomSql() {
        return customSql;
    }

    public void setCustomSql(String customSql) {
        this.customSql = customSql;
    }

    public String getCommonFilters() {
        return commonFilters;
    }

    public void setCommonFilters(String commonFilters) {
        this.commonFilters = commonFilters;
    }

    public void addFilter (AtomFilter atomFilter) {
        if(filters == null) {
            filters = new ArrayList<>();
        }

        filters.add(atomFilter);
    }

    public void addSort(AtomSort sort) {
        if (sorts == null) {
            sorts = new ArrayList<>();
        }

        sorts.add(sort);
    }

}
