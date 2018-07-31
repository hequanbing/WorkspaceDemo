package com.paic.lib.workbench;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.paic.lib.workbench.shortcut.adapter.CardGroupTitleAdapter;
import com.paic.lib.workbench.shortcut.adapter.CardItemAdapter;
import com.paic.lib.workbench.shortcut.adapter.GridGroupTitleAdapter;
import com.paic.lib.workbench.shortcut.adapter.GridItemAdapter;
import com.paic.lib.workbench.shortcut.listener.OnItemClickListener;
import com.paic.lib.workbench.shortcut.model.ShortcutTypes;
import com.paic.lib.workbench.shortcut.model.bean.GroupBean;
import com.paic.lib.workbench.shortcut.model.bean.ShortcutBean;
import com.paic.lib.workbench.shortcut.model.bean.ShortcutData;
import com.paic.lib.workbench.shortcut.model.bean.ShortcutGroup;
import com.paic.lib.workbench.shortcut.presenter.BasePresenter;
import com.paic.lib.workbench.shortcut.presenter.ShortcutPresenter;
import com.paic.lib.workbench.shortcut.view.IWorkspace;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements IWorkspace, OnItemClickListener {
    private static final String SHORT_CUT_SZSW_PATH = "shortcuts/szsw_config.json";
    private static final String SHORT_CUT_SZSCJGW_PATH = "shortcuts/szscjgw_config.json";
    private RecyclerView mRecyclerView;
    private VirtualLayoutManager mLayoutManager;
    private DelegateAdapter mDelegateAdapter;
    List<DelegateAdapter.Adapter> mAdapters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.workspace_shortcut_layout);

        mLayoutManager = new VirtualLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(ShortcutTypes.CARD_ITEM_SZSW, 20);
        viewPool.setMaxRecycledViews(ShortcutTypes.CARD_ITEM_SZSCJGW, 20);
        viewPool.setMaxRecycledViews(ShortcutTypes.GRID_ITEM_SZSCJGW, 20);

        mDelegateAdapter = new DelegateAdapter(mLayoutManager);
    }

    private void initData() {
        BasePresenter mPresenter = new ShortcutPresenter(this);
        mPresenter.loadDataFromAssets(this, SHORT_CUT_SZSW_PATH);
    }

    private void initRecyclerLayout(ShortcutData data) {
        if (data == null) {
            return;
        }
        boolean isNeedDivider = data.isNeedDivider();

        ArrayList<ShortcutGroup> shortcutGroups = data.getShortCutGroups();
        if (shortcutGroups != null && !shortcutGroups.isEmpty()) {
            for (ShortcutGroup shortcutGroup : shortcutGroups) {
                GroupBean groupBean = shortcutGroup.getGroup();
                if (groupBean != null && groupBean.isVisible()) {
                    CardGroupTitleAdapter cardGroupTitleAdapter = new CardGroupTitleAdapter(this, groupBean);
                    cardGroupTitleAdapter.setOnItemClickListener(this);
                    mAdapters.add(cardGroupTitleAdapter);
                }
                CardItemAdapter cardItemAdapter = new CardItemAdapter(this, shortcutGroup.getShortcuts(),
                        shortcutGroup.getGroup().getVhGap(), shortcutGroup.getGroup().getRowCount(), isNeedDivider);
                cardItemAdapter.setOnItemClickListener(this);
                mAdapters.add(cardItemAdapter);
            }
        }

        ShortcutGroup myApps = data.getMyApp();
        if (myApps != null) {
            GroupBean myAppGroup = myApps.getGroup();
            ArrayList<ShortcutBean> myAppItems = myApps.getShortcuts();
            if (myAppGroup != null && myAppItems != null && !myAppItems.isEmpty()) {
                GridGroupTitleAdapter gridGroupTitleAdapter = new GridGroupTitleAdapter(this, myAppGroup);
                gridGroupTitleAdapter.setOnItemClickListener(this);
                mAdapters.add(gridGroupTitleAdapter);

                GridItemAdapter gridItemAdapter = new GridItemAdapter(this, myAppItems,
                        myAppGroup.getVhGap(), myAppGroup.getRowCount(), myAppGroup.getGroupBgColor());
                gridItemAdapter.setOnItemClickListener(this);
                mAdapters.add(gridItemAdapter);
            }
        }

        mDelegateAdapter.setAdapters(mAdapters);
        mRecyclerView.setAdapter(mDelegateAdapter);
    }

    @Override
    public void initAllShortcuts(Object data) {
        if (data instanceof ShortcutData) {
            ShortcutData shortcutData = (ShortcutData) data;
            initRecyclerLayout(shortcutData);
        }
    }

    @Override
    public void initFailed(int errorCode, String errorMsg) {

    }

    @Override
    public <T> void onItemClick(View view, T data) {
        if (data == null) {
            return;
        }
        if (data instanceof GroupBean) {
            GroupBean groupBean = (GroupBean) data;
            Toast.makeText(this, "点击了：" + groupBean.getGroupTitle(), Toast.LENGTH_SHORT).show();
        } else if (data instanceof ShortcutBean) {
            ShortcutBean shortcutBean = (ShortcutBean) data;
            Toast.makeText(this, "点击了：" + shortcutBean.getTitle(), Toast.LENGTH_SHORT).show();
        }
    }
}
