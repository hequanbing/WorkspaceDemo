package com.paic.lib.workbench.shortcut.adapter.holder;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.paic.lib.workbench.R;
import com.paic.lib.workbench.shortcut.model.bean.GroupBean;

/**
 * @author hiyi
 * @date 2018/7/12
 * @des
 * @modify
 */
public class GridGroupItemHolder extends BaseShortcutHolder {
    private Context mContext;
    private TextView mGroupNameView;
    private ImageView mGroupLeftIcon;
    private GroupBean mData;

    public GridGroupItemHolder(Context context, View itemView) {
        super(context, itemView);
        this.mContext = context;
        mGroupNameView = itemView.findViewById(R.id.grid_group_name);
        mGroupLeftIcon = itemView.findViewById(R.id.grid_group_left_icon);
    }

    @Override
    public <T> void initView(T itemData) {
        if (itemData != null && itemData instanceof GroupBean) {
            GroupBean groupBean = (GroupBean) itemData;
            mData = groupBean;

            //字体大小
            float textSize = groupBean.getGroupTextSize();
            if (textSize > 0) {
                mGroupNameView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
            }

            //字体颜色
            String textColor = groupBean.getGroupTextColor();
            if (!TextUtils.isEmpty(textColor)) {
                mGroupNameView.setTextColor(Color.parseColor(textColor));
            }

            //DrawableLeft
            String leftIconRes = groupBean.getLeftIcon();
            if (!TextUtils.isEmpty(leftIconRes)) {
                int leftIconId = mContext.getResources().getIdentifier(leftIconRes, "mipmap",
                        mContext.getApplicationInfo().packageName);
                if (leftIconId > 0) {
                    mGroupLeftIcon.setImageResource(leftIconId);
                    mGroupLeftIcon.setVisibility(View.VISIBLE);
                } else {
                    mGroupLeftIcon.setVisibility(View.GONE);
                }
            } else {
                mGroupLeftIcon.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public <T> void initData(T itemData) {
        if (itemData != null && itemData instanceof GroupBean) {
            GroupBean groupBean = (GroupBean) itemData;
            mGroupNameView.setText(groupBean.getGroupTitle());
        }
    }

    @Override
    public <T> T getData() {
        return (T) mData;
    }
}
