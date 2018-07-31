package com.paic.lib.workbench.shortcut.model;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.paic.lib.workbench.WorkspaceErrorCode;
import com.paic.lib.workbench.shortcut.listener.DataLoadListener;
import com.paic.lib.workbench.shortcut.model.bean.ShortcutData;
import com.paic.lib.workbench.utils.FileUtils;

/**
 * @author hiyi
 * @date 2018/7/13
 * @des
 * @modify
 */
public class ShortcutModel implements BaseModel {

    @Override
    public void loadDataFromAssets(Context context, String filePath, DataLoadListener listener) {
        if (listener == null) {
            return;
        }
        if (context == null || TextUtils.isEmpty(filePath)) {
            listener.onDataLoadError(WorkspaceErrorCode.ERROR_PARAM_IS_NULL, "context or filePath is null !!!");
            return;
        }
        try {
            String data = FileUtils.getJsonFromAssets(context, filePath);
            ShortcutData shortcutData = new Gson().fromJson(data, ShortcutData.class);
            listener.onDataLoadSuccess(shortcutData);
        } catch (Exception e) {
            e.printStackTrace();
            listener.onDataLoadError(WorkspaceErrorCode.ERROR_PARSE_JSON_FAILED, e.getMessage() + "\n" + e.getCause());
        }
    }

    @Override
    public void loadDataFromCache(Context context, DataLoadListener listener) {
        if (listener == null) {
            return;
        }
        if (context == null) {
            listener.onDataLoadError(WorkspaceErrorCode.ERROR_PARAM_IS_NULL, "context is null !!!");
            return;
        }
        //TODO
    }

    @Override
    public void loadDataFromServer(Context context, DataLoadListener listener) {
        if (listener == null) {
            return;
        }
        if (context == null) {
            listener.onDataLoadError(WorkspaceErrorCode.ERROR_PARAM_IS_NULL, "context is null !!!");
            return;
        }
        //TODO
    }
}
