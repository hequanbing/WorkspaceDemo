package com.paic.lib.workbench.shortcut.model.bean;

/**
 * @author hiyi
 * @date 2018/7/5
 * @des
 * @modify
 */
public class ShortcutBean {
    /*
          "id": "101",
          "title": "待办",
          "iconRes": "ic_szsw_db",
          "iconUrl": "https://xxx.xxx.xxx/icon.png",
          "bgRes": "bg_szsw_db",
          "bgUrl": "https://xxx.xxx.xxx/bg.png",
          "textSize": "13",
          "textColor": "#67708C",
          "actionType": "1",
          "action": "http://10.248.85.77/iexoalogin.html?toPage=gongwenList&activedTab=0",
          "actionExtra": "action的扩展信息，会根据type的不同 解析规则也不一样",
          "downloadUrl"：""，
          "enable": "true",
          "viewType": "201"
    */
    private String id;
    private String title;
    private String iconRes;
    private String iconUrl;
    private String bgRes;
    private String bgUrl;
    private String textColor; //文字颜色
    private float textSize; //文字颜色
    private int actionType;
    private String action;
    private String actionExtra;
    private String downloadUrl;
    private boolean enable = true; //是否可点击，默认可点击
    private int viewType = 201;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconRes() {
        return iconRes;
    }

    public void setIconRes(String iconRes) {
        this.iconRes = iconRes;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getBgRes() {
        return bgRes;
    }

    public void setBgRes(String bgRes) {
        this.bgRes = bgRes;
    }

    public String getBgUrl() {
        return bgUrl;
    }

    public void setBgUrl(String bgUrl) {
        this.bgUrl = bgUrl;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionExtra() {
        return actionExtra;
    }

    public void setActionExtra(String actionExtra) {
        this.actionExtra = actionExtra;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
