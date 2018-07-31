package com.paic.lib.workbench.shortcut.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.paic.lib.workbench.R;
import com.paic.lib.workbench.shortcut.adapter.holder.BaseShortcutHolder;
import com.paic.lib.workbench.shortcut.adapter.holder.CardGroupItemHolder;
import com.paic.lib.workbench.shortcut.listener.OnItemClickListener;
import com.paic.lib.workbench.shortcut.model.ShortcutTypes;
import com.paic.lib.workbench.shortcut.model.bean.GroupBean;

/**
 * @author hiyi
 * @date 2018/7/12
 * @des
 * @modify
 */
public class CardGroupTitleAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {
    private GroupBean mGroupBean;
    private LayoutInflater mInflater;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener = null;

    public CardGroupTitleAdapter(Context context, GroupBean groupBean) {
        this.mContext = context;
        this.mGroupBean = groupBean;
        mInflater = LayoutInflater.from(context.getApplicationContext());
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return getLinearLayoutHelper();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case ShortcutTypes.CARD_GROUP_SZSW:
                view = mInflater.inflate(R.layout.card_group_item_layout, parent, false);
                holder = new CardGroupItemHolder(mContext, view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder != null && holder instanceof BaseShortcutHolder) {
            BaseShortcutHolder baseShortcutHolder = (BaseShortcutHolder) holder;
            baseShortcutHolder.initView(mGroupBean);
            baseShortcutHolder.initData(mGroupBean);
            baseShortcutHolder.setOnItemClickListener(mOnItemClickListener);
        }
    }

    @Override
    public int getItemCount() {
        if (mGroupBean == null || !mGroupBean.isVisible()) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return mGroupBean.getViewType();
    }

    private LinearLayoutHelper getLinearLayoutHelper() {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();

        // 公共属性
        linearLayoutHelper.setItemCount(1);

        //设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        linearLayoutHelper.setPadding(0, 0, 0, 0);

        //设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        linearLayoutHelper.setMargin(0, 0, 0, 0);
        //设置背景颜色
        if (!TextUtils.isEmpty(mGroupBean.getGroupBgColor())) {
            linearLayoutHelper.setBgColor(Color.parseColor(mGroupBean.getGroupBgColor()));
        }

        //设置设置布局内每行布局的宽与高的比
        //linearLayoutHelper.setAspectRatio(6f); //如不设置，则以实际高度计算

        // linearLayoutHelper特有属性
        //设置间隔高度，设置的间隔会与RecyclerView的addItemDecoration（）添加的间隔叠加.
        //linearLayoutHelper.setDividerHeight(10);

        //设置布局底部与下个布局的间隔
        //linearLayoutHelper.setMarginBottom(100);

        return linearLayoutHelper;
    }
}
