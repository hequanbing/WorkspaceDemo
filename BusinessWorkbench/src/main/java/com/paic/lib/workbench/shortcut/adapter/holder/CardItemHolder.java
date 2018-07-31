package com.paic.lib.workbench.shortcut.adapter.holder;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.paic.lib.workbench.R;
import com.paic.lib.workbench.shortcut.model.ShortcutTypes;
import com.paic.lib.workbench.shortcut.model.bean.ShortcutBean;

/**
 * @author hiyi
 * @date 2018/7/12
 * @des
 * @modify
 */
public class CardItemHolder extends BaseShortcutHolder {
    private Context mContext;
    private TextView mItemNameView;
    private ImageView mItemIconView;
    private ShortcutBean mData;
    private View mDividerLeft, mDividerRight, mDividerTop, mDividerBottom;

    public CardItemHolder(Context context, View itemView) {
        super(context, itemView);
        this.mContext = context;

        mItemNameView = itemView.findViewById(R.id.card_item_name);
        mItemIconView = itemView.findViewById(R.id.card_item_icon);

        mDividerLeft = itemView.findViewById(R.id.card_item_divider_left);
        mDividerRight = itemView.findViewById(R.id.card_item_divider_right);
        mDividerTop = itemView.findViewById(R.id.card_item_divider_top);
        mDividerBottom = itemView.findViewById(R.id.card_item_divider_bottom);

        itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        beginScale(R.anim.cellitem_zoom_in);
                        break;
                    case MotionEvent.ACTION_UP:
                        beginScale(R.anim.cellitem_zoom_out);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        beginScale(R.anim.cellitem_zoom_out);
                        break;
                }

                return true;
            }
        });
    }

    private synchronized void beginScale(int animation) {
        if (mItemIconView == null) {
            return;
        }

        Animation an = AnimationUtils.loadAnimation(mContext, animation);
        an.setDuration(50);
        an.setFillAfter(true);
        mItemIconView.startAnimation(an);
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
    public void initDivider(int count, int position, int rowCount) {
        //完整的分割线只需 左边+底部 即可完整显示，如果最最右边或者最顶部需要显示则放开其他判断，如此处未满一行的最后一个卡片的右分割线
        boolean showLeft = position % rowCount != 0;
        boolean showRight = position == count - 1 && position % rowCount != rowCount - 1;
        boolean showTop = false;
        boolean showBottom = (count - 1 - position) >= rowCount ;
        mDividerLeft.setVisibility(showLeft ? View.VISIBLE : View.GONE);
        mDividerRight.setVisibility(showRight ? View.VISIBLE : View.GONE);
        mDividerTop.setVisibility(showTop ? View.VISIBLE : View.GONE);
        mDividerBottom.setVisibility(showBottom ? View.VISIBLE : View.GONE);
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
