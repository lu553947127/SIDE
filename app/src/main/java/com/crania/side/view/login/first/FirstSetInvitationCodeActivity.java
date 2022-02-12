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
import com.crania.side.widget.CustomEditText;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.view.login.first
 * @ClassName: FirstSetInvitationCodeActivity
 * @Description: 设置邀请码页面
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/12 09:43
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/12 09:43
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FirstSetInvitationCodeActivity extends BaseActivity {
    @BindView(R.id.et_code)
    CustomEditText etCode;
    @BindView(R.id.tv_next)
    AppCompatTextView tvNext;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_first_set_invitation_code;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {

    }

    @OnTextChanged(value = R.id.et_code, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void inputCode(Editable editable) {
        String code = editable.toString().trim();
        tvNext.setEnabled(!TextUtils.isEmpty(code));
    }

    @OnClick({R.id.iv_bar_back, R.id.tv_skip, R.id.tv_next})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_skip://跳过

                break;
            case R.id.tv_next:
//                SPUtils.getInstance().put(SpConfig.INVITE, etName.getTrimmedString(), true);
//                ActivityUtils.startActivity(FirstSetInvitationCodeActivity.class);
                break;
        }
    }
}
