package com.crania.side.view;

import android.os.Bundle;
import android.view.View;

import com.crania.side.R;
import com.crania.side.base.BaseFragment;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.view
 * @ClassName: FindFragment
 * @Description: 发现frgament
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/7 15:41
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/7 15:41
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FindFragment extends BaseFragment {
    @Override
    protected int initLayout() {
        return R.layout.fragment_find;
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
