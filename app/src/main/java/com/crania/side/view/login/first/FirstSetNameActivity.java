package com.crania.side.view.login.first;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.crania.side.R;
import com.crania.side.base.BaseActivity;
import com.crania.side.base.SpConfig;
import com.crania.side.utils.NumberUtils;
import com.crania.side.widget.CustomEditText;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.view.login.first
 * @ClassName: FirstSetNameActivity
 * @Description: 设置昵称页面
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/11 16:31
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/11 16:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FirstSetNameActivity extends BaseActivity {
    @BindView(R.id.et_name)
    CustomEditText etName;
    @BindView(R.id.tv_error)
    AppCompatTextView tvError;
    @BindView(R.id.tv_next)
    AppCompatTextView tvNext;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_first_set_name;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {

    }

    @OnTextChanged(value = R.id.et_name, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void inputName(Editable editable) {
        String name = editable.toString().trim();
        if (NumberUtils.isIllegalCharacter(name)){
            tvError.setVisibility(View.VISIBLE);
            etName.setBackgroundResource(R.drawable.shape_red_half_2);
        }else {
            tvError.setVisibility(View.INVISIBLE);
            etName.setBackgroundResource(R.drawable.shape_black_half_2);
        }
        tvNext.setEnabled(!TextUtils.isEmpty(name) && !NumberUtils.isIllegalCharacter(name));
    }

    @OnClick({R.id.iv_bar_back,R.id.tv_next})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_next:
                SPUtils.getInstance().put(SpConfig.NAME, etName.getTrimmedString(), true);
                ActivityUtils.startActivity(FirstSetInvitationCodeActivity.class);
                break;
        }
    }
}
