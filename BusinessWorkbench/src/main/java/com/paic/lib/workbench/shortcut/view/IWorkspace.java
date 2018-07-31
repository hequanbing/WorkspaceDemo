package com.paic.lib.workbench.shortcut.view;

/**
 * @author hiyi
 * @date 2018/7/13
 * @des
 * @modify
 */
public interface IWorkspace {
    void initAllShortcuts(Object data);
    void initFailed(int errorCode, String errorMsg);
}
