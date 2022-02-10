package com.crania.side.view;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.crania.side.R;
import com.crania.side.base.BaseFragment;
import com.crania.side.utils.LoginUtils;
import com.lxj.xpopup.XPopup;

import butterknife.OnClick;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.view
 * @ClassName: MineFragment
 * @Description: 我的fragment
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/7 15:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/7 15:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineFragment extends BaseFragment {
    @Override
    protected int initLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState, View view) {

    }

    @Override
    protected void initDataFromService() {

    }

    @OnClick({R.id.tv_quit})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_quit:
                new XPopup.Builder(mActivity).asConfirm(getString(R.string.reminder), getString(R.string.login_out_is), () -> {
                    ToastUtils.showShort(R.string.sign_out);
                    LoginUtils.getExitLogin();
                }).show();
                break;
        }
    }
}
