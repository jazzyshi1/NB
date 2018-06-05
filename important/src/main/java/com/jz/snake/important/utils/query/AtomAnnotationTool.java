/**
 * Copyright: 瀚思安信（北京）软件技术有限公司，保留所有权利。
 * Author: neven (pengyu_yang@hansight.com)
 * Created: 2016年08月10日
 */
package com.jz.snake.important.utils.query;

import org.nutz.dao.entity.annotation.One;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Author: neven (pengyu_yang@hansight.com)
 * Created: 2016年08月10日
 */
public class AtomAnnotationTool {

    /**
     * 获取@One注解的属性名
     * @param clazz
     * @return
     */
    public static List<String> getOneProperties(Class<?> clazz) {
        List<String> list = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {

            One one = field.getAnnotation(One.class);
            if(one != null) {
                list.add(field.getName());
            }
        }

        return list;
    }




}
