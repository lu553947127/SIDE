package com.crania.side.view.login.first;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.crania.side.R;
import com.crania.side.base.BaseActivity;
import com.crania.side.base.SpConfig;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.view.login.first
 * @ClassName: FirstSetGenderActivity
 * @Description: 设置性别页面
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/9 14:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/9 14:04
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FirstSetGenderActivity extends BaseActivity {
    @BindView(R.id.iv_female)
    AppCompatImageView ivFemale;
    @BindView(R.id.iv_male)
    AppCompatImageView ivMale;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_first_set_gender;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {

    }

    @OnClick({R.id.iv_bar_back, R.id.rl_female,R.id.rl_male})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.rl_female://女生
                SPUtils.getInstance().put(SpConfig.SEX, "female", true);
                ivFemale.setImageResource(R.drawable.icon_sex_female_select);
                ivMale.setImageResource(R.drawable.icon_sex_male);
                ActivityUtils.startActivity(FirstSetBirthdayActivity.class);
                break;
            case R.id.rl_male://男生
                SPUtils.getInstance().put(SpConfig.SEX, "male", true);
                ivFemale.setImageResource(R.drawable.icon_sex_female);
                ivMale.setImageResource(R.drawable.icon_sex_male_select);
                ActivityUtils.startActivity(FirstSetBirthdayActivity.class);
                break;
        }
    }
}
