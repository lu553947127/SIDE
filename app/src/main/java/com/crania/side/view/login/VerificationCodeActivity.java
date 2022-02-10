package com.crania.side.view.login;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.crania.side.R;
import com.crania.side.base.BaseActivity;
import com.crania.side.base.SpConfig;
import com.crania.side.view.MainActivity;
import com.crania.side.view.login.first.FirstSetGenderActivity;
import com.crania.side.viewmodel.LoginViewModel;
import com.crania.side.widget.VerificationCodeInputView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.view.login
 * @ClassName: VerificationCodeActivity
 * @Description: 输入短信验证码页面
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/8 14:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/8 14:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class VerificationCodeActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.tv_verification_code)
    VerificationCodeInputView tvVerificationCode;
    @BindView(R.id.tv_resend)
    AppCompatTextView tvResend;
    @BindView(R.id.tv_second)
    AppCompatTextView tvSecond;
    private LoginViewModel loginViewModel;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_verification_code;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        tvTitle.setText(getString(R.string.register_tips , SPUtils.getInstance().getString(SpConfig.PHONE)));
        tvResend.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        loginViewModel = getViewModel(LoginViewModel.class);

        //获取验证码返回回调
        loginViewModel.sendCodeLiveData.observe(this, object -> {
            loginViewModel.sendVerificationCode();
        });

        //获取验证码倒计时返回结果
        loginViewModel.timeLiveDataLiveData.observe(this, aLong -> {
            getCode(aLong);
        });

        //验证验证码返回结果
        loginViewModel.verificationCodeLiveData.observe(this, sendCodeBean -> {
            if (sendCodeBean.getCode() == 0){
                if (!TextUtils.isEmpty(sendCodeBean.getData().getTempPass())){
                    SPUtils.getInstance().put(SpConfig.PASSWORD, sendCodeBean.getData().getTempPass(), true);
                    loginViewModel.getVerificationPhone(SPUtils.getInstance().getString(SpConfig.PHONE));
                }else {
                    ActivityUtils.startActivity(FirstSetGenderActivity.class);
                }
            }else {
                ToastUtils.showShort(sendCodeBean.getData().getMsg());
                tvVerificationCode.setBackgroundError();
            }
        });

        //验证手机号返回结果
        loginViewModel.verificationPhoneLiveData.observe(this, object -> {
            //老用户
            if (object.getParseObject() != null){
                loginViewModel.getLoginPassword(object.getParseObject().getString("phonenumber"),
                        SPUtils.getInstance().getString(SpConfig.PASSWORD));
            }else {
             //新用户
                ActivityUtils.startActivity(FirstSetGenderActivity.class);
            }
        });

        //登录返回结果
        loginViewModel.loginLiveData.observe(this, parseUser -> {
            SPUtils.getInstance().put(SpConfig.USER_ID, parseUser.getObjectId(), true);
            SPUtils.getInstance().put(SpConfig.TOKEN, parseUser.getSessionToken(), true);
            ActivityUtils.startActivity(MainActivity.class);
        });

        loginViewModel.getSendCode(SPUtils.getInstance().getString(SpConfig.PHONE));

        //验证码输入完成回调
        tvVerificationCode.setOnCompleteListener(content -> {
            loginViewModel.getVerificationCode(SPUtils.getInstance().getString(SpConfig.PHONE), content);
        });
    }

    //验证码获取成功后操作
    private void getCode(Long aLong) {
        if (aLong == -1) {
            //重新获取
            tvResend.setClickable(true);
            tvResend.setTextColor(getResources().getColor(R.color.black));
            tvResend.setVisibility(View.VISIBLE);
            tvSecond.setVisibility(View.GONE);
        } else {
            tvResend.setClickable(false);
            tvResend.setTextColor(getResources().getColor(R.color.color_979797));
            tvResend.setVisibility(View.GONE);
            tvSecond.setVisibility(View.VISIBLE);
            tvSecond.setText(getString(R.string.second ,String.valueOf(aLong)));
        }
    }

    @OnClick({R.id.iv_bar_back,R.id.tv_resend})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_resend://重新发送验证码
                loginViewModel.getSendCode(SPUtils.getInstance().getString(SpConfig.PHONE));
                break;
        }
    }
}
