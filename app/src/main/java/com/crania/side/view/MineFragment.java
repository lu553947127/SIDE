package com.crania.side.view;

import android.os.Bundle;
import android.view.View;

import com.crania.side.R;
import com.crania.side.base.BaseFragment;

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
}
