package com.crania.side.dialog;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.amap.api.maps.MapsInitializer;
import com.crania.side.R;
import com.crania.side.base.BaseActivity;
import com.crania.side.base.SpConfig;
import com.crania.side.event.StartEvent;
import com.crania.side.utils.LoginUtils;
import com.crania.side.utils.SharedUtils;
import com.crania.side.utils.TextViewUtils;
import com.lxj.xpopup.core.BottomPopupView;

import org.greenrobot.eventbus.EventBus;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.dialog
 * @ClassName: AgreementDialog
 * @Description: 协议弹窗
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/7 11:09
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/7 11:09
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AgreementDialog extends BottomPopupView {
    private final BaseActivity activity;

    public AgreementDialog(@NonNull BaseActivity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_agreement;
    }

    @Override
    protected void onShow() {
        super.onShow();
        TextView tv_content = findViewById(R.id.tv_content);
        TextView tv_agree = findViewById(R.id.tv_agree);
        TextView tv_disagree = findViewById(R.id.tv_disagree);

        TextViewUtils.setTextInfo(activity, tv_content, activity.getResources().getString(R.string.agreement_content)
                ,133,141,231,239);

        SharedUtils sharedUtils = new SharedUtils(activity);

        tv_agree.setOnClickListener(v -> {
            //设置高德地图隐私协议
            MapsInitializer.updatePrivacyShow(activity, true, true);
            MapsInitializer.updatePrivacyAgree(activity, true);
            sharedUtils.addShared(SpConfig.FIRST_APP,"1","first");
            EventBus.getDefault().post(new StartEvent());
            dismiss();
        });

        tv_disagree.setOnClickListener(v -> {
            dismiss();
            LoginUtils.getExitApp(activity);
        });
    }
}
