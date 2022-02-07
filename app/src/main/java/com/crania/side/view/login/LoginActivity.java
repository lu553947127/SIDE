package com.crania.side.view.login;

import android.os.Bundle;
import android.text.Editable;

import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.crania.side.R;
import com.crania.side.base.BaseActivity;
import com.crania.side.utils.NumberUtils;
import com.crania.side.view.MainActivity;
import com.crania.side.viewmodel.LoginViewModel;
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
    @BindView(R.id.ccp_country)
    CountryCodePicker countryCodePicker;
    @BindView(R.id.et_phone)
    CustomEditText etPhone;
    @BindView(R.id.tv_login)
    AppCompatTextView tvLogin;
    private LoginViewModel loginViewModel;
    //世界各国区号code
    private String code = "+86";
    //各个国家简称
    private String abbreviation = "cn";

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

        loginViewModel = getViewModel(LoginViewModel.class);

        //验证手机号返回结果
        loginViewModel.verificationPhoneLiveData.observe(this, object -> {
            Bundle bundle = new Bundle();
            //老用户
            if (object.getParseObject() != null){
                bundle.putString("abbreviation", abbreviation);
                bundle.putString("phone", object.getParseObject().getString("phonenumber"));
//                ActivityUtils.startActivity(bundle, LoginPasswordActivity.class);
            }else {
                //新用户
                if (object.getMessage().equals("Invalid session token")){
                    ToastUtils.showShort(object.getMessage());
                    return;
                }
                bundle.putString("phone", code + etPhone.getTrimmedString());
//                ActivityUtils.startActivity(bundle, VerificationCodeActivity.class);
            }
        });
    }

    @OnTextChanged(value = R.id.et_phone, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void inputPhoneNumber(Editable editable) {
        String phoneNumber = editable.toString().trim();
        tvLogin.setEnabled(NumberUtils.isPhoneNumberValid(activity,code + phoneNumber, code));
        if (NumberUtils.isPhoneNumberValid(activity,code + phoneNumber, code) || StringUtils.isEmpty(phoneNumber)){

        }
    }

    @OnClick({R.id.tv_login})
    void onClick() {
//        loginViewModel.getVerificationPhone(code + etPhone.getTrimmedString());
        ActivityUtils.startActivity(MainActivity.class);
    }
}
