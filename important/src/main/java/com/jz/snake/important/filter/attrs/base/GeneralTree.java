package com.jz.snake.important.filter.attrs.base;

/**
 * @author xuezhi_xu@hansight.com
 * @date 16/11/30 上午11:41
 */
public class GeneralTree {

    /**
     * 节点id
     */
    private String id;
    /**
     * 父节点id
     */
    private String parentId;

    /**
     * 真实值
     */
    private String realVal;

    /**
     * 显示值
     */
    private String name;

    /**
     * 本节点是否可以点击
     */
    private boolean clickable;

    /**
     * 所属模块
     */
    private String belongsFieldName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isClickable() {
        return clickable;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }

    public String getBelongsFieldName() {
        return belongsFieldName;
    }

    public void setBelongsFieldName(String belongsFieldName) {
        this.belongsFieldName = belongsFieldName;
    }

    public String getRealVal() {
        return realVal;
    }

    public void setRealVal(String realVal) {
        this.realVal = realVal;
    }
}
