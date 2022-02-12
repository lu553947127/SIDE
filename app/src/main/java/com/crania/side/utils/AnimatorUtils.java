package com.crania.side.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.utils
 * @ClassName: AnimatorUtils
 * @Description: 动画工具类
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/11 16:25
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/11 16:25
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AnimatorUtils {

    /**
     * 控件上下浮动动画
     *
     * @param view
     */
    public static void setTranslateAnimation(View view){
        ObjectAnimator downAnim = ObjectAnimator.ofFloat(view, "translationY", 0F, 100F, 0F);
        ObjectAnimator upAnim = ObjectAnimator.ofFloat(view, "translationY", 0F, -100F, 0F);
        downAnim.setDuration(1000);
        upAnim.setDuration(1000);
        downAnim.setInterpolator(new LinearInterpolator());
        upAnim.setInterpolator(new LinearInterpolator());
        downAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // 向下动画结束，启动向上动画
                upAnim.start();
            }
        });
        upAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // 向上动画结束，启动向下动画
                downAnim.start();
            }
        });
        // 先启动一次
        downAnim.start();
    }
}
