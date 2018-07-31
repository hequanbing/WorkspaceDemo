package com.paic.lib.workbench.shortcut.model.bean;

/**
 * @author hiyi
 * @date 2018/7/5
 * @des
 * @modify
 */
public class GroupBean {
    /*
        "groupTitle": "市委办办公系统",
        "groupId": "1",
        "groupTextSize": "15",
        "groupTextColor": "#FF67708C",
        "visible": "true",
        "leftIcon": "icon_res",
        "groupBgColor": "#FFFFFFFF",
        "vhGap": "5",
        "rowCount": "3",
        "viewType": "101"
    */
    private String groupId;
    private String groupTitle;
    private String groupTextColor;
    private float groupTextSize = -1;
    private String leftIcon;
    private boolean visible = true;
    private String groupBgColor;
    private float vhGap = 0;
    private int rowCount = 3;
    private int viewType = 101;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public String getGroupTextColor() {
        return groupTextColor;
    }

    public void setGroupTextColor(String groupTextColor) {
        this.groupTextColor = groupTextColor;
    }

    public float getGroupTextSize() {
        return groupTextSize;
    }

    public void setGroupTextSize(float groupTextSize) {
        this.groupTextSize = groupTextSize;
    }

    public String getLeftIcon() {
        return leftIcon;
    }

    public void setLeftIcon(String leftIcon) {
        this.leftIcon = leftIcon;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getGroupBgColor() {
        return groupBgColor;
    }

    public void setGroupBgColor(String groupBgColor) {
        this.groupBgColor = groupBgColor;
    }

    public float getVhGap() {
        return vhGap;
    }

    public void setVhGap(float vhGap) {
        this.vhGap = vhGap;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

}
