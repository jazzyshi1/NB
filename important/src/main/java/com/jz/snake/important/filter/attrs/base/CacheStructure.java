package com.jz.snake.important.filter.attrs.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author xuezhi_xu@hansight.com
 * @date 16/11/24 下午3:35
 */
public class CacheStructure implements Serializable{


    //缓存名称
    private String cacheName;

    //依赖模块
    private String baseName;

    //属性列表
    private List<String> attrFields;

    //操作符列表
    private Map<String,List<String>> operation;

    //字典列表
    private List<String> option;

    //动态字典引用列表(树形结构)
    private List<String> referOption;

    //动态字典引用列表(列表结构)
    private List<String> dynamicOption;


    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public List<String> getAttrFields() {
        return attrFields;
    }

    public void setAttrFields(List<String> attrFields) {
        this.attrFields = attrFields;
    }

    public Map<String, List<String>> getOperation() {
        return operation;
    }

    public void setOperation(Map<String, List<String>> operation) {
        this.operation = operation;
    }

    public List<String> getOption() {
        return option;
    }

    public void setOption(List<String> option) {
        this.option = option;
    }

    public List<String> getReferOption() {
        return referOption;
    }

    public void setReferOption(List<String> referOption) {
        this.referOption = referOption;
    }

    public List<String> getDynamicOption() {
        return dynamicOption;
    }

    public void setDynamicOption(List<String> dynamicOption) {
        this.dynamicOption = dynamicOption;
    }
}
