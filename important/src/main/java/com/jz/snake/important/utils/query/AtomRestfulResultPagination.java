/**
 * Copyright: 瀚思安信（北京）软件技术有限公司，保留所有权利。
 * Author: neven (pengyu_yang@hansight.com)
 * Created: 2016年08月04日
 */
package com.jz.snake.important.utils.query;

import java.util.List;

/**
 *
 * Author: neven (pengyu_yang@hansight.com)
 * Created: 2016年08月04日
 */
public class AtomRestfulResultPagination {

        /**
         * 总记录数
         */
        private int total;

        /**
         * 数据列表
         */
        private List<?> list;

        public AtomRestfulResultPagination(int total, List<?> list) {
            this.total = total;
            this.list = list;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<?> getList() {
            return list;
        }

        public void setList(List<?> list) {
            this.list = list;
        }
}
