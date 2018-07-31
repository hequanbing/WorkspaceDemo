package com.paic.lib.workbench.shortcut.adapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.paic.lib.workbench.shortcut.listener.OnItemClickListener;

public abstract class BaseShortcutHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected Context mContext;

    private OnItemClickListener mListener;

    public BaseShortcutHolder(Context context, View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.mContext = context;
    }

    public abstract <T> void initView(T itemData);
    public abstract <T> void initData(T itemData);
    public abstract <T> T getData();

    public void initDivider(int count, int position, int rowCount) {

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onItemClick(v, getData());
        }
    }
}