package com.crania.side.view.common;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.BarUtils;
import com.crania.side.R;
import com.crania.side.base.BaseActivity;
import com.crania.side.base.BuildConfig;
import com.crania.side.widget.CustomWebView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.view.common
 * @ClassName: WebViewActivity
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/7 11:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/7 11:04
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class WebViewActivity extends BaseActivity {
    @BindView(R.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R.id.tv_bar_title)
    AppCompatTextView tvBarTitle;
    @BindView(R.id.web_view)
    CustomWebView webView;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_web_view;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        BarUtils.setStatusBarColor(fakeStatusBar, getResources().getColor(R.color.white));
        switch (getIntent().getStringExtra("type")){
            case "user":
                webView.loadUrl(BuildConfig.USER_URL);
                tvBarTitle.setText("用户协议");
                break;
            case "privacy":
                webView.loadUrl(BuildConfig.AGREEMENT_URL);
                tvBarTitle.setText("隐私协议");
                break;
        }
    }

    @OnClick({R.id.iv_bar_back})
    void onClick() {
        finish();
    }
}
