package com.crania.side.view.start;

import android.os.Bundle;

import com.blankj.utilcode.util.ActivityUtils;
import com.crania.side.R;
import com.crania.side.base.BaseActivity;
import com.crania.side.dialog.AgreementDialog;
import com.crania.side.event.StartEvent;
import com.crania.side.utils.LoginUtils;
import com.crania.side.utils.PermissionUtils;
import com.crania.side.view.MainActivity;
import com.crania.side.view.login.LoginActivity;
import com.lxj.xpopup.XPopup;

import org.greenrobot.eventbus.Subscribe;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.view.start
 * @ClassName: StartUpActivity
 * @Description: 启动页
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/7 10:56
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/7 10:56
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class StartUpActivity extends BaseActivity {
    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_start_up;
    }

    @Override
    public boolean isUseEventBus() {
        return true;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        if (LoginUtils.isFirstApp(activity)){
            getIntoActivity();
        }else {
            //协议弹窗
            new XPopup.Builder(activity)
                    .dismissOnTouchOutside(false)
                    .enableDrag(false)
                    .asCustom(new AgreementDialog(activity))
                    .show();
        }
    }

    //判断跳转
    private void getIntoActivity() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1500);//休眠1.5秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //判断是否获取过token
                if (LoginUtils.isLogin()){
                    if (PermissionUtils.isCheckPermission(activity)){
                        ActivityUtils.startActivity(MainActivity.class);
                    }else {
//                        ActivityUtils.startActivity(FirstPermissionActivity.class);
                    }
                }else {
                    ActivityUtils.startActivity(LoginActivity.class);
                }

                finish();
            }
        }.start();
    }

    /**
     * 点击跳转登录页
     *
     * @param event
     */
    @Subscribe
    public void onEventStart(StartEvent event) {
        getIntoActivity();
    }
}
