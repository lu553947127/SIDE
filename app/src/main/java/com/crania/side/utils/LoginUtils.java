package com.crania.side.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.crania.side.base.BaseActivity;
import com.crania.side.base.SpConfig;
import com.crania.side.view.login.LoginActivity;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.util.List;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.utils
 * @ClassName: LoginUtils
 * @Description: 登录相关检测工具类
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/7 10:27
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/7 10:27
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoginUtils {

    /**
     * 检测是否第一次打开app
     *
     * @return
     */
    public static boolean isFirstApp(BaseActivity activity) {
        SharedUtils sharedUtils = new SharedUtils(activity);
        String first = sharedUtils.getShared(SpConfig.FIRST_APP,"first");
        if (TextUtils.isEmpty(first)) {
            return false;
        }
        return true;
    }

    /**
     * 检测是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        String token = SPUtils.getInstance().getString(SpConfig.TOKEN);
        if (StringUtils.isTrimEmpty(token)) {
            return false;
        }
        return true;
    }

    /**
     * 检测是否登记用户信息
     *
     * @return
     */
    public static boolean isFirstUser(){
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            ParseFile parseFile = (ParseFile) currentUser.get("photo");
            if (StringUtils.isTrimEmpty(String.valueOf(currentUser.get("sex")))){
                return false;
            }
            if (StringUtils.isTrimEmpty(String.valueOf(currentUser.get("birthday")))){
                return false;
            }
            if (StringUtils.isTrimEmpty(String.valueOf(currentUser.get("job")))){
                return false;
            }
            if (StringUtils.isTrimEmpty(String.valueOf(currentUser.get("tag")))){
                return false;
            }
            if (StringUtils.isTrimEmpty(String.valueOf(currentUser.get("event_tag")))){
                return false;
            }
            if (StringUtils.isTrimEmpty(currentUser.getUsername())){
                return false;
            }
            if (parseFile != null &&StringUtils.isTrimEmpty(parseFile.getUrl())){
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * 极光推送别名设置
     */
    public static boolean setJPushAlias(BaseActivity activity) {
        String userId = SPUtils.getInstance().getString(SpConfig.USER_ID);
        int aliasStatus = SPUtils.getInstance().getInt(SpConfig.ALIAS_STATUS, 0);
        if (!TextUtils.isEmpty(userId) && aliasStatus != 1) {
            JPushInterface.setAlias(activity, 0, userId);
            return true;
        }
        return false;
    }

    /**
     * 退出账号到登录页面
     *
     * @return
     */
    public static void getExitLogin() {
        SPUtils.getInstance().clear(true);
        ActivityUtils.startActivity(LoginActivity.class);
        ActivityUtils.finishOtherActivities(LoginActivity.class);
        RongIM.getInstance().logout();
    }

    /**
     * 退出App
     *
     * @return
     */
    public static void getExitApp(BaseActivity activity) {
        // 1. 通过Context获取ActivityManager
        ActivityManager activityManager = (ActivityManager) activity.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        // 2. 通过ActivityManager获取任务栈
        List<ActivityManager.AppTask> appTaskList = activityManager.getAppTasks();
        // 3. 逐个关闭Activity
        for (ActivityManager.AppTask appTask : appTaskList) {
            appTask.finishAndRemoveTask();
        }
    }
}
