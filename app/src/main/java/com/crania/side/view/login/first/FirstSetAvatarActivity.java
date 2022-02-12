package com.crania.side.view.login.first;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.crania.side.R;
import com.crania.side.app.MyApplication;
import com.crania.side.base.BaseActivity;
import com.crania.side.base.SpConfig;
import com.crania.side.event.CompressEvent;
import com.crania.side.image.ImageLoader;
import com.crania.side.utils.AnimatorUtils;
import com.crania.side.utils.CompressUtils;
import com.crania.side.utils.PermissionUtils;
import com.crania.side.utils.PictureUtils;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.view.login.first
 * @ClassName: FirstSetAvatarActivity
 * @Description: 设置头像页面
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/10 13:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/10 13:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FirstSetAvatarActivity extends BaseActivity {
    @BindView(R.id.rl_one)
    RelativeLayout rlOne;
    @BindView(R.id.rl_two)
    RelativeLayout rlTwo;
    @BindView(R.id.iv_image)
    AppCompatImageView ivImage;
    @BindView(R.id.iv_image1)
    AppCompatImageView ivImage1;
    @BindView(R.id.iv_image2)
    AppCompatImageView ivImage2;
    @BindView(R.id.iv_image3)
    AppCompatImageView ivImage3;
    @BindView(R.id.ll_head)
    LinearLayout llHead;
    @BindView(R.id.iv_avatar)
    AppCompatImageView ivAvatar;
    @BindView(R.id.iv_avatar2)
    AppCompatImageView ivAvatar2;
    @BindView(R.id.iv_avatar3)
    AppCompatImageView ivAvatar3;
    @BindView(R.id.iv_avatar4)
    AppCompatImageView ivAvatar4;
    @BindView(R.id.iv_avatar5)
    AppCompatImageView ivAvatar5;
    @BindView(R.id.iv_avatar6)
    AppCompatImageView ivAvatar6;
    @BindView(R.id.iv_avatar7)
    AppCompatImageView ivAvatar7;
    @BindView(R.id.iv_avatar8)
    AppCompatImageView ivAvatar8;
    @BindView(R.id.iv_avatar9)
    AppCompatImageView ivAvatar9;
    @BindView(R.id.iv_avatar_add2)
    AppCompatImageView ivAvatarAdd2;
    @BindView(R.id.iv_avatar_add3)
    AppCompatImageView ivAvatarAdd3;
    @BindView(R.id.iv_avatar_add4)
    AppCompatImageView ivAvatarAdd4;
    @BindView(R.id.iv_avatar_add5)
    AppCompatImageView ivAvatarAdd5;
    @BindView(R.id.iv_avatar_add6)
    AppCompatImageView ivAvatarAdd6;
    @BindView(R.id.iv_avatar_add7)
    AppCompatImageView ivAvatarAdd7;
    @BindView(R.id.iv_avatar_add8)
    AppCompatImageView ivAvatarAdd8;
    @BindView(R.id.iv_avatar_add9)
    AppCompatImageView ivAvatarAdd9;
    @BindView(R.id.iv_avatar_delete)
    AppCompatImageView ivAvatarDelete;
    @BindView(R.id.iv_avatar_delete2)
    AppCompatImageView ivAvatarDelete2;
    @BindView(R.id.iv_avatar_delete3)
    AppCompatImageView ivAvatarDelete3;
    @BindView(R.id.iv_avatar_delete4)
    AppCompatImageView ivAvatarDelete4;
    @BindView(R.id.iv_avatar_delete5)
    AppCompatImageView ivAvatarDelete5;
    @BindView(R.id.iv_avatar_delete6)
    AppCompatImageView ivAvatarDelete6;
    @BindView(R.id.iv_avatar_delete7)
    AppCompatImageView ivAvatarDelete7;
    @BindView(R.id.iv_avatar_delete8)
    AppCompatImageView ivAvatarDelete8;
    @BindView(R.id.iv_avatar_delete9)
    AppCompatImageView ivAvatarDelete9;
    @BindView(R.id.rl_avatar_add2)
    RelativeLayout rlAvatarAdd2;
    @BindView(R.id.rl_avatar_add3)
    RelativeLayout rlAvatarAdd3;
    @BindView(R.id.rl_avatar_add4)
    RelativeLayout rlAvatarAdd4;
    @BindView(R.id.rl_avatar_add5)
    RelativeLayout rlAvatarAdd5;
    @BindView(R.id.rl_avatar_add6)
    RelativeLayout rlAvatarAdd6;
    @BindView(R.id.rl_avatar_add7)
    RelativeLayout rlAvatarAdd7;
    @BindView(R.id.rl_avatar_add8)
    RelativeLayout rlAvatarAdd8;
    @BindView(R.id.rl_avatar_add9)
    RelativeLayout rlAvatarAdd9;
    @BindView(R.id.tv_next)
    AppCompatTextView tvNext;
    private final List<String> picture_list = new ArrayList<>();

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_first_set_avatar;
    }

    @Override
    public boolean isUseEventBus() {
        return true;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        if (SPUtils.getInstance().getString(SpConfig.SEX).equals("female")){
            ivImage.setImageResource(R.drawable.icon_avatar_woman);
            ivImage1.setImageResource(R.drawable.icon_avatar_woman1);
            ivImage2.setImageResource(R.drawable.icon_avatar_woman2);
            ivImage3.setImageResource(R.drawable.icon_avatar_woman3);
        }else {
            ivImage.setImageResource(R.drawable.icon_avatar_man);
            ivImage1.setImageResource(R.drawable.icon_avatar_man1);
            ivImage2.setImageResource(R.drawable.icon_avatar_man2);
            ivImage3.setImageResource(R.drawable.icon_avatar_man3);
        }

        llHead.getBackground().setAlpha(120);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PermissionUtils.ALBUM_CODE & resultCode == RESULT_OK) {
            if (MyApplication.isAndroidQ) {
                if (Matisse.obtainResult(data).size() + picture_list.size() > 9){
                    ToastUtils.showShort(getString(R.string.photo_size_not));
                    return;
                }
                for (int i = 0; i < Matisse.obtainResult(data).size(); i++) {
                    CompressUtils.uploadFileCompress(new File(ImageLoader.getUriRealFilePath(activity,
                            Matisse.obtainResult(data).get(i))));
                }
            }else {
                if (Matisse.obtainPathResult(data).size()+ picture_list.size() > 9){
                    ToastUtils.showShort(getString(R.string.photo_size_not));
                    return;
                }
                for (int i = 0; i < Matisse.obtainPathResult(data).size(); i++) {
                    CompressUtils.uploadFileCompress(new File(Matisse.obtainPathResult(data).get(i)));
                }
            }
        }
    }

    /**
     * 图片压缩后回显
     *
     * @param event
     */
    @Subscribe
    public void onEventCompress(CompressEvent event) {
        picture_list.add(String.valueOf(event.getCompressFile()));
        LogUtils.e(picture_list);
        setImage();
    }

    @OnClick({R.id.iv_bar_back, R.id.rl_add,
            R.id.rl_avatar_add2, R.id.rl_avatar_add3, R.id.rl_avatar_add4,
            R.id.rl_avatar_add5, R.id.rl_avatar_add6, R.id.rl_avatar_add7,
            R.id.rl_avatar_add8, R.id.rl_avatar_add9,
            R.id.iv_avatar_delete, R.id.iv_avatar_delete2, R.id.iv_avatar_delete3,
            R.id.iv_avatar_delete4, R.id.iv_avatar_delete5, R.id.iv_avatar_delete6, R.id.iv_avatar_delete7,
            R.id.iv_avatar_delete8, R.id.iv_avatar_delete9, R.id.tv_next})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_next://下一步
                ActivityUtils.startActivity(FirstSetNameActivity.class);
                break;
            case R.id.rl_add://添加图片
            case R.id.rl_avatar_add2:
            case R.id.rl_avatar_add3:
            case R.id.rl_avatar_add4:
            case R.id.rl_avatar_add5:
            case R.id.rl_avatar_add6:
            case R.id.rl_avatar_add7:
            case R.id.rl_avatar_add8:
            case R.id.rl_avatar_add9:
                PermissionUtils.openCameraOfStoragePermission(activity, 9);
                break;
            case R.id.iv_avatar_delete:
                picture_list.remove(0);
                deleteImage();
                setImage();
                break;
            case R.id.iv_avatar_delete2:
                picture_list.remove(1);
                deleteImage();
                setImage();
                break;
            case R.id.iv_avatar_delete3:
                picture_list.remove(2);
                deleteImage();
                setImage();
                break;
            case R.id.iv_avatar_delete4:
                picture_list.remove(3);
                deleteImage();
                setImage();
                break;
            case R.id.iv_avatar_delete5:
                picture_list.remove(4);
                deleteImage();
                setImage();
                break;
            case R.id.iv_avatar_delete6:
                picture_list.remove(5);
                deleteImage();
                setImage();
                break;
            case R.id.iv_avatar_delete7:
                picture_list.remove(6);
                deleteImage();
                setImage();
                break;
            case R.id.iv_avatar_delete8:
                picture_list.remove(7);
                deleteImage();
                setImage();
                break;
            case R.id.iv_avatar_delete9:
                picture_list.remove(8);
                deleteImage();
                setImage();
                break;
        }
    }

    /**
     * 设置图片
     */
    private void setImage() {
        for (String image : picture_list){
            if (picture_list.size() == 1){
                PictureUtils.setImage(activity, picture_list.get(0), ivAvatar);
                rlOne.setVisibility(View.GONE);
                rlTwo.setVisibility(View.VISIBLE);
                tvNext.setEnabled(true);
                AnimatorUtils.setTranslateAnimation(tvNext);
                return;
            }
            if (picture_list.size() == 2){
                PictureUtils.setImage(activity, picture_list.get(1), ivAvatar2);
                rlAvatarAdd2.setEnabled(false);
                ivAvatarAdd2.setVisibility(View.GONE);
                ivAvatarDelete2.setVisibility(View.VISIBLE);
                return;
            }
            if (picture_list.size() == 3){
                PictureUtils.setImage(activity, picture_list.get(2), ivAvatar3);
                rlAvatarAdd3.setEnabled(false);
                ivAvatarAdd3.setVisibility(View.GONE);
                ivAvatarDelete3.setVisibility(View.VISIBLE);
                return;
            }
            if (picture_list.size() == 4){
                PictureUtils.setImage(activity, picture_list.get(3), ivAvatar4);
                rlAvatarAdd4.setEnabled(false);
                ivAvatarAdd4.setVisibility(View.GONE);
                ivAvatarDelete4.setVisibility(View.VISIBLE);
                return;
            }
            if (picture_list.size() == 5){
                PictureUtils.setImage(activity, picture_list.get(4), ivAvatar5);
                rlAvatarAdd5.setEnabled(false);
                ivAvatarAdd5.setVisibility(View.GONE);
                ivAvatarDelete5.setVisibility(View.VISIBLE);
                return;
            }
            if (picture_list.size() == 6){
                PictureUtils.setImage(activity, picture_list.get(5), ivAvatar6);
                rlAvatarAdd6.setEnabled(false);
                ivAvatarAdd6.setVisibility(View.GONE);
                ivAvatarDelete6.setVisibility(View.VISIBLE);
                return;
            }
            if (picture_list.size() == 7){
                PictureUtils.setImage(activity, picture_list.get(6), ivAvatar7);
                rlAvatarAdd7.setEnabled(false);
                ivAvatarAdd7.setVisibility(View.GONE);
                ivAvatarDelete7.setVisibility(View.VISIBLE);
                return;
            }
            if (picture_list.size() == 8){
                PictureUtils.setImage(activity, picture_list.get(7), ivAvatar8);
                rlAvatarAdd8.setEnabled(false);
                ivAvatarAdd8.setVisibility(View.GONE);
                ivAvatarDelete8.setVisibility(View.VISIBLE);
                return;
            }
            if (picture_list.size() == 9){
                PictureUtils.setImage(activity, picture_list.get(8), ivAvatar9);
                rlAvatarAdd9.setEnabled(false);
                ivAvatarAdd9.setVisibility(View.GONE);
                ivAvatarDelete9.setVisibility(View.VISIBLE);
                return;
            }
        }
    }

    /**
     * 删除图片
     */
    private void deleteImage() {
         if (picture_list.size() == 0){
                rlOne.setVisibility(View.VISIBLE);
                rlTwo.setVisibility(View.GONE);
                tvNext.setEnabled(false);
                return;
            }
        for (String image : picture_list){
            if (picture_list.size() == 1){
                PictureUtils.setImage(activity, null, ivAvatar2, R.color.color_F9FAFB);
                rlAvatarAdd2.setEnabled(true);
                ivAvatarAdd2.setVisibility(View.VISIBLE);
                ivAvatarDelete2.setVisibility(View.GONE);
                return;
            }
            if (picture_list.size() == 2){
                PictureUtils.setImage(activity, null, ivAvatar3, R.color.color_F9FAFB);
                rlAvatarAdd3.setEnabled(true);
                ivAvatarAdd3.setVisibility(View.VISIBLE);
                ivAvatarDelete3.setVisibility(View.GONE);
                return;
            }
            if (picture_list.size() == 3){
                PictureUtils.setImage(activity, null, ivAvatar4, R.color.color_F9FAFB);
                rlAvatarAdd4.setEnabled(true);
                ivAvatarAdd4.setVisibility(View.VISIBLE);
                ivAvatarDelete4.setVisibility(View.GONE);
                return;
            }
            if (picture_list.size() == 4){
                PictureUtils.setImage(activity, null, ivAvatar5, R.color.color_F9FAFB);
                rlAvatarAdd5.setEnabled(true);
                ivAvatarAdd5.setVisibility(View.VISIBLE);
                ivAvatarDelete5.setVisibility(View.GONE);
                return;
            }
            if (picture_list.size() == 5){
                PictureUtils.setImage(activity, null, ivAvatar6, R.color.color_F9FAFB);
                rlAvatarAdd6.setEnabled(true);
                ivAvatarAdd6.setVisibility(View.VISIBLE);
                ivAvatarDelete6.setVisibility(View.GONE);
                return;
            }
            if (picture_list.size() == 6){
                PictureUtils.setImage(activity, null, ivAvatar7, R.color.color_F9FAFB);
                rlAvatarAdd7.setEnabled(true);
                ivAvatarAdd7.setVisibility(View.VISIBLE);
                ivAvatarDelete7.setVisibility(View.GONE);
                return;
            }
            if (picture_list.size() == 7){
                PictureUtils.setImage(activity, null, ivAvatar8, R.color.color_F9FAFB);
                rlAvatarAdd8.setEnabled(true);
                ivAvatarAdd8.setVisibility(View.VISIBLE);
                ivAvatarDelete8.setVisibility(View.GONE);
                return;
            }
            if (picture_list.size() == 8){
                PictureUtils.setImage(activity, null, ivAvatar9, R.color.color_F9FAFB);
                rlAvatarAdd9.setEnabled(true);
                ivAvatarAdd9.setVisibility(View.VISIBLE);
                ivAvatarDelete9.setVisibility(View.GONE);
                return;
            }
        }
    }
}
