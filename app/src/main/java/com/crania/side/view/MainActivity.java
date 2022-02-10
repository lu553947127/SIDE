package com.crania.side.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.crania.side.R;
import com.crania.side.base.BaseActivity;
import com.crania.side.base.SpConfig;
import com.crania.side.utils.BadgeUtils;
import com.crania.side.utils.NotificationsUtils;
import com.crania.side.utils.NumberUtils;
import com.crania.side.view.common.WebViewActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    List<Fragment> mFragments;
    //用于记录上个选择的Fragment
    private int lastFragment;

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
        initFragment();
        //设置角标
        BadgeUtils.addBadgeAt(activity, navigation, 3, 111);
        //设置底部tab icon大小
        BadgeUtils.adjustNavigationIcoSize(activity, navigation);
    }

    /**
     * 初始化fragment和fragment数组
     */
    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new FindFragment());
        mFragments.add(new MessagesFragment());
        mFragments.add(new MineFragment());

        navigation.setItemIconTintList(null);
        switchFragment(lastFragment,0);lastFragment=0;
        navigation.getMenu().getItem(0).setChecked(true);
        //判断选择的菜单
        navigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_home:
                    if(lastFragment!=0)switchFragment(lastFragment,0);lastFragment=0;
                    break;
                case R.id.menu_find:
                    if(lastFragment!=1)switchFragment(lastFragment,1);lastFragment=1;
                    break;
                case R.id.menu_add:
                    break;
                case R.id.menu_message:
                    if(lastFragment!=2)switchFragment(lastFragment,2);lastFragment=2;
                    break;
                case R.id.menu_mine:
                    if(lastFragment!=3)switchFragment(lastFragment,3);lastFragment=3;
                    break;
                default:
                    break;
            }
            // 这里注意返回true,否则点击失效
            return true;
        });
    }

    /**
     * 切换Fragment
     *
     * @param lastFragment
     * @param index
     */
    private void switchFragment(int lastFragment,int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(mFragments.get(lastFragment));//隐藏上个Fragment
        if(!mFragments.get(index).isAdded()) {
            transaction.add(R.id.fragment,mFragments.get(index));
        }
        transaction.show(mFragments.get(index)).commitAllowingStateLoss();
    }

    @OnClick({R.id.iv_add})
    void onClick() {
        Bundle bundle = new Bundle();
        bundle.putString("type", "user");
        ActivityUtils.startActivity(bundle, WebViewActivity.class);
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