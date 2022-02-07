package com.crania.side.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.crania.side.R;
import com.crania.side.base.BaseActivity;
import com.crania.side.base.SpConfig;
import com.crania.side.utils.NotificationsUtils;
import com.crania.side.utils.NumberUtils;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.view
 * @ClassName: MainActivity
 * @Description: 首页
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/7 10:56
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/7 10:56
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MainActivity extends BaseActivity {

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!NotificationsUtils.isNotificationEnabled(this)) {
            //通知权限开启弹窗 3天只弹出一次
            if (NumberUtils.dateDiff(SPUtils.getInstance().getString(SpConfig.NOTICE_TIME), TimeUtils.getNowString(), "yyyy-MM-dd hh:mm:ss") >= 3) {
                showNoticePermissionDialog(getString(R.string.permission_notice));
            }
        }
    }

    /**
     * 重写返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //实现只在冷启动时显示启动页，类似微信效果
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //实现只在冷启动时显示启动页，即点击返回键与点击HOME键退出效果一致
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}