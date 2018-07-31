package com.paic.lib.workbench.shortcut.model;

import android.content.Context;

import com.paic.lib.workbench.shortcut.listener.DataLoadListener;

/**
 * @author hiyi
 * @date 2018/7/13
 * @des
 * @modify
 */
public interface BaseModel {
    void loadDataFromAssets(Context context, String filePath, DataLoadListener listener);
    void loadDataFromCache(Context context, DataLoadListener listener);
    void loadDataFromServer(Context context, DataLoadListener listener);
}
