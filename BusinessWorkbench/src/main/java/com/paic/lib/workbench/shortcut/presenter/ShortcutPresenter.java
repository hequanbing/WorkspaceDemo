package com.paic.lib.workbench.shortcut.presenter;

import android.content.Context;

import com.paic.lib.workbench.shortcut.listener.DataLoadListener;
import com.paic.lib.workbench.shortcut.model.ShortcutModel;
import com.paic.lib.workbench.shortcut.view.IWorkspace;

/**
 * @author hiyi
 * @date 2018/7/5
 * @des
 * @modify
 */
public class ShortcutPresenter implements BasePresenter, DataLoadListener {
    private final IWorkspace mView;
    private final ShortcutModel mModel;

    public ShortcutPresenter(IWorkspace view) {
        if (null == view) {
            throw new RuntimeException("IWorkspace must Not Be Null !!!");
        }
        mView = view;
        mModel = new ShortcutModel();
    }

    @Override
    public void loadDataFromAssets(Context context, String filePath) {
        mModel.loadDataFromAssets(context, filePath, this);
    }

    @Override
    public void loadDataFromCache(Context context) {
        mModel.loadDataFromCache(context, this);
    }

    @Override
    public void loadDataFromServer(Context context) {
        mModel.loadDataFromServer(context, this);
    }

    @Override
    public void onDataLoadSuccess(Object result) {
        mView.initAllShortcuts(result);
    }

    @Override
    public void onDataLoadError(int errorCode, String errorMsg) {
        mView.initFailed(errorCode, errorMsg);
    }
}
