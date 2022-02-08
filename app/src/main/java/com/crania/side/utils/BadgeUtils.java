package com.crania.side.utils;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.crania.side.R;
import com.crania.side.base.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.utils
 * @ClassName: BadgeUtils
 * @Description: 底部tab/view角标设置工具类
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/8 11:57
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/8 11:57
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BadgeUtils {

    /**
     * 设置底部tab角标
     *
     * @param activity
     * @param bottomNavigationView
     * @param position 角标数量
     * @param number tab位置
     * @return
     */
    public static Badge addBadgeAt(BaseActivity activity, BottomNavigationView bottomNavigationView, int position, int number) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        //这里就是获取所添加的每一个Tab(或者叫menu)，设置在标题栏的位置
        View tab = menuView.getChildAt(position);
        BottomNavigationItemView itemView = (BottomNavigationItemView) tab;
        // add badge
        return new QBadgeView(activity)
                .setBadgeNumber(number)
                .setGravityOffset(12, 2, true)
                .bindTarget(itemView)
                .setOnDragStateChangedListener((dragState, badge, targetView) -> {
                    if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState)
                        ToastUtils.showShort("清除");
                });
    }

    /**
     * 设置底部tab icon大小
     *
     * @param activity
     * @param navigation
     */
    public static void adjustNavigationIcoSize(BaseActivity activity, BottomNavigationView navigation){
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, displayMetrics);
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }
    }
}
