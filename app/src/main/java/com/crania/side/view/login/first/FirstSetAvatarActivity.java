package com.crania.side.view.login.first;

import android.os.Bundle;

import com.crania.side.R;
import com.crania.side.base.BaseActivity;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.view.login.first
 * @ClassName: FirstSetAvatarActivity
 * @Description: 设置头像页面
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/10 13:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/10 13:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FirstSetAvatarActivity extends BaseActivity {
    @Override
    protected int initLayoutRes() {
        return R.layout.activity_first_set_avatar;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {

    }
}
