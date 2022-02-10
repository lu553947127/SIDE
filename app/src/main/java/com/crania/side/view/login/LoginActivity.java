package com.crania.side.view.login;

import android.os.Bundle;
import android.text.Editable;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.crania.side.R;
import com.crania.side.base.BaseActivity;
import com.crania.side.base.SpConfig;
import com.crania.side.utils.NumberUtils;
import com.crania.side.widget.CustomEditText;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.view.login
 * @ClassName: LoginActivity
 * @Description: 登录页面
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/7 13:34
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/7 13:34
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.ll_bg)
    LinearLayout llBg;
    @BindView(R.id.ccp_country)
    CountryCodePicker countryCodePicker;
    @BindView(R.id.et_phone)
    CustomEditText etPhone;
    @BindView(R.id.tv_login)
    AppCompatTextView tvLogin;
    //世界各国区号code
    private String code = "+86";
    //各个国家简称
    private String abbreviation = "cn";

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        //国家选择器与输入框绑定
        countryCodePicker.registerPhoneNumberTextView(etPhone);
        //国家选择器选择返回事件
        countryCodePicker.setOnCountryChangeListener(selectedCountry -> {
            abbreviation = selectedCountry.getIso();
            code = "+" + selectedCountry.getPhoneCode();
        });

        etPhone.setHint("请输入你的手机号");

        //手机号输入框点击选择/未选中效果
        etPhone.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                llBg.setBackgroundResource(R.drawable.shape_black_half_2);
            } else {
                llBg.setBackgroundResource(R.drawable.shape_gray_half_2);
            }
        });
    }

    @OnTextChanged(value = R.id.et_phone, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void inputPhoneNumber(Editable editable) {
        String phoneNumber = editable.toString().trim();
        tvLogin.setEnabled(NumberUtils.isPhoneNumberValid(activity,code + phoneNumber, code));
    }

    @OnClick({R.id.tv_login})
    void onClick() {
        if (!NumberUtils.isPhoneNumberValid(activity,code + etPhone.getTrimmedString(), code) ||
                StringUtils.isEmpty(etPhone.getTrimmedString())){
            ToastUtils.showShort(getString(R.string.login_phone_not));
            return;
        }

        SPUtils.getInstance().put(SpConfig.ABBREVIATION, abbreviation, true);
        SPUtils.getInstance().put(SpConfig.PHONE, code + etPhone.getTrimmedString(), true);
        ActivityUtils.startActivity(VerificationCodeActivity.class);
    }
}
