package com.crania.side.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.crania.side.base.SpConfig;
import com.crania.side.bean.ImTokenBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;

import java.util.HashMap;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.viewmodel
 * @ClassName: IMConnectViewModel
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/12 15:50
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/12 15:50
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class IMConnectViewModel extends ViewModel {
    public MutableLiveData<String> pageStateLiveData;
    public MutableLiveData<String> failStateLiveData;
    public MutableLiveData<String> tokenLiveData;
    private final String userId;

    public IMConnectViewModel() {
        userId = SPUtils.getInstance().getString(SpConfig.USER_ID);
        pageStateLiveData = new MutableLiveData<>();
        failStateLiveData = new MutableLiveData<>();
        tokenLiveData = new MutableLiveData<>();
    }

    /**
     * 获取融云token
     */
    public void getImToken(){
        HashMap<String, String> params = new HashMap();
        params.put("userId", userId);
        ParseCloud.callFunctionInBackground("chat-token", params, new FunctionCallback<Object>(){
            @Override
            public void done(Object object, ParseException e) {
                if (e == null) {
                    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
                    LogUtils.e("getImToken: "+ gson.toJson(object));
                    ImTokenBean imTokenBean = gson.fromJson(gson.toJson(object), ImTokenBean.class);
                    if (imTokenBean.getCode() == 0){
                        SPUtils.getInstance().put(SpConfig.IM_TOKEN, imTokenBean.getData().getToken(), true);
                        connect(imTokenBean.getData().getToken());
                    }else {
                        ToastUtils.showShort(imTokenBean.getError());
                        failStateLiveData.postValue("失败");
                    }
                }else {
                    failStateLiveData.postValue("失败");
                    LogUtils.e( "getImToken: " + e.getMessage());
                }
            }
        });
    }

    /**
     * <p>连接服务器，在整个应用程序全局，只需要调用一次，需在之后调用。</p>
     * <p>如果调用此接口遇到连接失败，SDK 会自动启动重连机制进行最多10次重连，分别是1, 2, 4, 8, 16, 32, 64, 128, 256, 512秒后。
     * 在这之后如果仍没有连接成功，还会在当检测到设备网络状态变化时再次进行重连。</p>
     *
     * @param token    从服务端获取的用户身份令牌（Token）。
     * @return RongIM  客户端核心类的实例。
     */
    public void connect(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onDatabaseOpened(RongIMClient.DatabaseOpenStatus code) {
                //消息数据库打开，可以进入到主页面
            }

            @Override
            public void onSuccess(String s) {
                //连接成功
                LogUtils.i("融云连接成功"+s);
                tokenLiveData.postValue(s);
            }

            @Override
            public void onError(RongIMClient.ConnectionErrorCode errorCode) {
                LogUtils.i("融云连接失败"+errorCode);
                ToastUtils.showShort("连接失败："+errorCode);
                failStateLiveData.postValue("失败");
                if(errorCode.equals(RongIMClient.ConnectionErrorCode.RC_CONN_TOKEN_INCORRECT)) {
                    //从 APP 服务获取新 token，并重连
                    getImToken();
                } else {
                    //无法连接 IM 服务器，请根据相应的错误码作出对应处理
                    ToastUtils.showShort("连接失败，请联系服务人员");
                }
            }
        });
    }
}
