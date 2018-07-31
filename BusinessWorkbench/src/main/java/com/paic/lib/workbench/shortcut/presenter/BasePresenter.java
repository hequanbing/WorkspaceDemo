package com.paic.lib.workbench.shortcut.presenter;

import android.content.Context;

/**
 * @author hiyi
 * @date 2018/7/13
 * @des
 * @modify
 */
public interface BasePresenter {
    void loadDataFromAssets(Context context, String filePath);
    void loadDataFromCache(Context context);
    void loadDataFromServer(Context context);
}
