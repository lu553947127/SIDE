package com.crania.side.view.login.first;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.crania.side.R;
import com.crania.side.base.BaseActivity;
import com.crania.side.base.SpConfig;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.view.login.first
 * @ClassName: FirstSetBirthdayActivity
 * @Description: 设置出生日期页面
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/9 14:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/9 14:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FirstSetBirthdayActivity extends BaseActivity {
    @BindView(R.id.et_year)
    AppCompatEditText etYear;
    @BindView(R.id.et_month)
    AppCompatEditText etMonth;
    @BindView(R.id.et_day)
    AppCompatEditText etDay;
    @BindView(R.id.tv_next)
    AppCompatTextView tvNext;
    //年月日
    private String year, month, day, birthday;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_first_set_birthday;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {

    }

    @OnTextChanged(value = R.id.et_year, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void inputYear(Editable editable) {
        year = editable.toString().trim();
        if (!TextUtils.isEmpty(year)){
            int yearNum = Integer.parseInt(year);
            if (year.length() == 4 && yearNum <= 1924){
                ToastUtils.showShort("异常年龄");
                editable.clear();
                return;
            }

            if (year.length() == 4 && yearNum >= 2005){
                ToastUtils.showShort("尚未成年");
                editable.clear();
                return;
            }
        }
        if (year.length() == 4) etMonth.requestFocus();
    }

    @OnTextChanged(value = R.id.et_month, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void inputMonth(Editable editable) {
        month = editable.toString().trim();
        if (!TextUtils.isEmpty(month)){
            int monthNum = Integer.parseInt(month);
            if (month.length() == 2 && monthNum > 12){
                editable.replace(0, 2, "12");
                etDay.requestFocus();
            }
        }else {
            etYear.requestFocus();
        }

        if (month.length() == 2) etDay.requestFocus();
    }

    @OnTextChanged(value = R.id.et_day, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void inputDay(Editable editable) {
        day = editable.toString().trim();
        if (!TextUtils.isEmpty(day)){
            int dayNum = Integer.parseInt(day);
            switch (month){
                case "1":
                case "3":
                case "5":
                case "7":
                case "8":
                case "10":
                case "12":
                    if (day.length() == 2 && dayNum > 31){
                        editable.replace(0, 2, "31");
                        etDay.requestFocus();
                    }
                    break;
                case "2":
                    if (day.length() == 2 && dayNum > 28){
                        editable.replace(0, 2, "28");
                        etDay.requestFocus();
                    }
                    break;
                case "4":
                case "6":
                case "9":
                case "11":
                    if (day.length() == 2 && dayNum > 30){
                        editable.replace(0, 2, "30");
                        etDay.requestFocus();
                    }
                    break;
            }
            String newMonth = Integer.parseInt(month) > 10 ? month : "0" + month;
            String newDay = Integer.parseInt(day) > 10 ? day : "0" + day;
            birthday = year + "-" + newMonth + "-" + newDay;
            SPUtils.getInstance().put(SpConfig.BIRTHDAY, birthday, true);
        }else {
            etMonth.requestFocus();
        }

        LogUtils.e(birthday);
        if (!year.isEmpty() && !month.isEmpty() && !day.isEmpty() && RegexUtils.isDate(birthday)){
            tvNext.setEnabled(true);
            ActivityUtils.startActivity(FirstSetAvatarActivity.class);
        }else {
            tvNext.setEnabled(false);
            ToastUtils.showShort(getString(R.string.first_birthday_not));
        }
    }

    @OnClick({R.id.iv_bar_back,R.id.tv_next})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_next://重新发送验证码
                ActivityUtils.startActivity(FirstSetAvatarActivity.class);
                break;
        }
    }
}
