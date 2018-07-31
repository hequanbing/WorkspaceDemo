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
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.paic.lib.workbench.R;
import com.paic.lib.workbench.shortcut.adapter.holder.BaseShortcutHolder;
import com.paic.lib.workbench.shortcut.adapter.holder.GridItemHolder;
import com.paic.lib.workbench.shortcut.listener.OnItemClickListener;
import com.paic.lib.workbench.shortcut.model.ShortcutTypes;
import com.paic.lib.workbench.shortcut.model.bean.ShortcutBean;
import com.paic.lib.workbench.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hiyi
 * @date 2018/7/12
 * @des
 * @modify
 */
public class GridItemAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {
    private List<ShortcutBean> mShortcuts = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener = null;
    private float mVHGap;
    private int mRowCount;
    private String mBgColor;

    public GridItemAdapter(Context context, List<ShortcutBean> shortcuts,
                           float vhGap, int rowCount, String bgColor) {
        this.mContext = context;
        this.mShortcuts.clear();
        this.mShortcuts.addAll(shortcuts);
        this.mVHGap = vhGap;
        this.mRowCount = rowCount;
        this.mBgColor = bgColor;
        mInflater = LayoutInflater.from(context.getApplicationContext());
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return getGridLayoutHelper();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case ShortcutTypes.GRID_ITEM_SZSCJGW:
                view = mInflater.inflate(R.layout.grid_item_layout, parent, false);
                holder = new GridItemHolder(mContext, view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder != null && holder instanceof BaseShortcutHolder) {
            ShortcutBean shortcut = mShortcuts.get(position);
            BaseShortcutHolder baseShortcutHolder = (BaseShortcutHolder) holder;
            baseShortcutHolder.initData(shortcut);
            baseShortcutHolder.initView(shortcut);
            baseShortcutHolder.setOnItemClickListener(mOnItemClickListener);
        }
    }

    @Override
    public int getItemCount() {
        if (mShortcuts == null || mShortcuts.isEmpty()) {
            return 0;
        }
        return mShortcuts.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mShortcuts.get(position).getViewType();
    }

    private GridLayoutHelper getGridLayoutHelper() {
        //设置每行的网格个数
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(mRowCount);

        // 公共属性
        //设置布局里Item个数
        gridLayoutHelper.setItemCount(getItemCount());

        //设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        gridLayoutHelper.setPadding(0, 0, 0, 0);

        //设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        gridLayoutHelper.setMargin(DensityUtils.dip2px(mContext, mVHGap), 0,
                DensityUtils.dip2px(mContext, mVHGap), DensityUtils.dip2px(mContext, mVHGap));

        //设置背景颜色
        //设置背景颜色
        if (!TextUtils.isEmpty(mBgColor)) {
            gridLayoutHelper.setBgColor(Color.parseColor(mBgColor));
        } else {
            gridLayoutHelper.setBgColor(Color.WHITE);
        }

        //设置设置布局内每行布局的宽与高的比
        gridLayoutHelper.setAspectRatio(mRowCount); //卡片均为正方形，因此宽高比即为每行卡片个数

        // gridLayoutHelper特有属性
        //设置每行中 每个网格宽度 占 每行总宽度 的比例
        float weightPercent = 100f / mRowCount;
        float[] weights = new float[mRowCount];
        for (int i = 0; i < mRowCount; i++) {
            weights[i] = weightPercent;
        }
        gridLayoutHelper.setWeights(weights);

        //控制子元素之间的垂直间距
        gridLayoutHelper.setVGap(DensityUtils.dip2px(mContext, mVHGap));

        //控制子元素之间的水平间距
        gridLayoutHelper.setHGap(DensityUtils.dip2px(mContext, mVHGap));

        //是否自动填充空白区域
        gridLayoutHelper.setAutoExpand(false);

        //设置每行多少个网格
        gridLayoutHelper.setSpanCount(mRowCount);

        return gridLayoutHelper;
    }
}
