package com.paic.lib.workbench;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
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

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        TextView test1 = findViewById(R.id.test1);
        TextView test2 = findViewById(R.id.test2);
        String link = "<a href='http://www.baidu.com' target='_blank'>百度一下我就知道</a>";

        test1.setText(link);

        SpannableString spStr = new SpannableString(link);
        ClickableSpan clickSpan = new NoLineClickSpan(spStr.toString());
        spStr.setSpan(clickSpan, 0, spStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        test2.append(spStr);
        test2.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private class NoLineClickSpan extends ClickableSpan {
        String text;
        public NoLineClickSpan(String text) {
            super();
            this.text = text;
        }
        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(ds.linkColor);
            ds.setUnderlineText(false); //去掉下划线
        }
        @Override
        public void onClick(View widget) {
            processHyperLinkClick(text); //点击超链接时调用
        }
    }
    private void processHyperLinkClick(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }
}
