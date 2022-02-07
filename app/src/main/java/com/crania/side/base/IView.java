package com.crania.side.base;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.base
 * @ClassName: IView
 * @Description: 动画加载回调
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/7 10:13
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/7 10:13
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface IView {
    /**
     * 加载中
     */
    void showLoading();

    /**
     * 隐藏
     */
    void hideLoading();
}
