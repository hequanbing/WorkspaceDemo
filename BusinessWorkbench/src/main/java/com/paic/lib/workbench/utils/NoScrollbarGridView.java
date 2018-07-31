package com.paic.lib.workbench.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class NoScrollbarGridView extends GridView {

    public NoScrollbarGridView(Context context) {
        super(context);
    }

    public NoScrollbarGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollbarGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
