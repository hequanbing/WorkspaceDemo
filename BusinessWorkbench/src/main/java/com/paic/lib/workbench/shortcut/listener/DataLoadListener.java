package com.paic.lib.workbench.shortcut.listener;

/**
 * @author hiyi
 * @date 2018/7/13
 * @des
 * @modify
 */
public interface DataLoadListener {
    void onDataLoadSuccess(Object result);
    void onDataLoadError(int errorCode, String errorMsg);
}
