package com.paic.lib.workbench.shortcut.adapter.holder;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.paic.lib.workbench.R;
import com.paic.lib.workbench.shortcut.model.bean.ShortcutBean;

/**
 * @author hiyi
 * @date 2018/7/12
 * @des
 * @modify
 */
public class GridItemHolder extends BaseShortcutHolder {
    private Context mContext;
    private TextView mItemNameView;
    private ImageView mItemIconView;
    private ShortcutBean mData;

    public GridItemHolder(Context context, View itemView) {
        super(context, itemView);
        this.mContext = context;

        mItemNameView = itemView.findViewById(R.id.grid_item_name);
        mItemIconView = itemView.findViewById(R.id.grid_item_icon);
    }

    @Override
    public <T> void initView(T itemData) {
        if (itemData != null && itemData instanceof ShortcutBean) {
            ShortcutBean shortcutBean = (ShortcutBean) itemData;
            mData = shortcutBean;

            String iconRes = shortcutBean.getIconRes();
            if (!TextUtils.isEmpty(iconRes)) {
                int iconResId = mContext.getResources().getIdentifier(iconRes, "mipmap",
                        mContext.getApplicationInfo().packageName);
                if (iconResId > 0) {
                    mItemIconView.setImageResource(iconResId);
                }
            }

            //字体大小
            float textSize = shortcutBean.getTextSize();
            if (textSize > 0) {
                mItemNameView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
            }

            //字体颜色
            String textColor = shortcutBean.getTextColor();
            if (!TextUtils.isEmpty(textColor)) {
                mItemNameView.setTextColor(Color.parseColor(textColor));
            }
        }
    }

    @Override
    public <T> void initData(T itemData) {
        if (itemData != null && itemData instanceof ShortcutBean) {
            ShortcutBean shortcutBean = (ShortcutBean) itemData;
            mItemNameView.setText(shortcutBean.getTitle());
        }
    }

    @Override
    public <T> T getData() {
        return (T) mData;
    }
}
