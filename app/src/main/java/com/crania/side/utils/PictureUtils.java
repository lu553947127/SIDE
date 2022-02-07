package com.crania.side.utils;

import android.content.Context;

import androidx.appcompat.widget.AppCompatImageView;

import com.crania.side.R;
import com.crania.side.app.MyApplication;
import com.crania.side.image.ImageConfig;
import com.crania.side.image.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.utils
 * @ClassName: PictureUtils
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/7 10:25
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/7 10:25
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PictureUtils {

    /**
     * 九宫格添加图片显示（列表带加号的）
     *
     * @param picture_list
     * @return
     */
    public static List<String> getImageBitmapAdd(List<String> picture_list) {
        List<String> list = new ArrayList<>();
        list.clear();
        for (int i = 0; i< picture_list.size(); i++){
            list.add(picture_list.get(i));
        }
        return list;
    }

    /**
     * 鲁班压缩时，设置压缩后存放的路径
     *
     * @return
     */
    public static String getLuBanPath() {
        String path = MyApplication.getInstance().getFilesDir() + "/Luban/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

    /**
     * 设置图片显示
     *
     * @param context
     * @param image
     * @param appCompatImageView
     */
    public static void setImage(Context context, String image, AppCompatImageView appCompatImageView){
        ImageLoader.load(context, new ImageConfig.Builder()
                .url(image)
                .placeholder(R.drawable.ic_empty_dracula)
                .errorPic(R.drawable.ic_empty_dracula)
                .imageView(appCompatImageView)
                .build());
    }
}
