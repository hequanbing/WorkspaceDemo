package com.paic.lib.workbench.shortcut.model.bean;

import java.util.ArrayList;

/**
 * @author hiyi
 * @date 2018/7/5
 * @des
 * @modify
 */
public class ShortcutGroup {
    private GroupBean group;
    private ArrayList<ShortcutBean> shortcuts;

    public GroupBean getGroup() {
        return group;
    }

    public void setGroup(GroupBean group) {
        this.group = group;
    }

    public ArrayList<ShortcutBean> getShortcuts() {
        return shortcuts;
    }

    public void setShortcuts(ArrayList<ShortcutBean> shortcuts) {
        this.shortcuts = shortcuts;
    }
}
