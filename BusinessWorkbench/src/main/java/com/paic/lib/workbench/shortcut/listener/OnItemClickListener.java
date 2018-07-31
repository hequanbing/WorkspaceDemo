package com.paic.lib.workbench.shortcut.listener;

import android.view.View;

/**
 * 定义RecycleView的Item点击事件
 */
public interface OnItemClickListener {

    <T> void onItemClick(View view, T data);
}
