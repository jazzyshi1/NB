/**
 * Copyright: 瀚思安信（北京）软件技术有限公司，保留所有权利。
 * Author: neven (pengyu_yang@hansight.com)
 * Created: 2016年08月04日
 */
package com.jz.snake.important.utils.query;

/**
 * 分页实体类
 * Author: neven (pengyu_yang@hansight.com)
 * Created: 2016年08月04日
 */
public class AtomPagination {

    /**
     * 第几页
     */
    private int page;

    /**
     * 每页多少条记录
     */
    private int pageSize;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
