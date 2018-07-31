package com.paic.lib.workbench.shortcut.model.bean;

import java.util.ArrayList;

/**
 * @author hiyi
 * @date 2018/7/5
 * @des
 * @modify
 */
public class ShortcutData {
    private ArrayList<ShortcutGroup> shortCutGroups;
    private boolean needDivider = true;
    private ShortcutGroup myApp;

    public ArrayList<ShortcutGroup> getShortCutGroups() {
        return shortCutGroups;
    }

    public void setShortCutGroups(ArrayList<ShortcutGroup> shortCutGroups) {
        this.shortCutGroups = shortCutGroups;
    }

    public boolean isNeedDivider() {
        return needDivider;
    }

    public void setNeedDivider(boolean needDivider) {
        this.needDivider = needDivider;
    }

    public ShortcutGroup getMyApp() {
        return myApp;
    }

    public void setMyApp(ShortcutGroup myApp) {
        this.myApp = myApp;
    }
}
