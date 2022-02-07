package com.crania.side.utils;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.utils
 * @ClassName: RefreshUtils
 * @Description: 上拉加载刷新数据工具
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/7 10:29
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/7 10:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RefreshUtils {

    /**
     * 上拉加载刷新数据 分页工具
     *
     * @param refresh
     * @param page
     * @param count
     */
    public static void setNoMore(SmartRefreshLayout refresh, int page, int count){
        if (page == 0) {
            if (page * 10 >= count) {
                if (refresh.getState() == RefreshState.None) {
                    refresh.setNoMoreData(true);
                } else {
                    refresh.finishRefreshWithNoMoreData();
                }
            } else {
                refresh.finishRefresh();
            }
        } else {
            if (page * 10 >= count) {
                refresh.finishLoadMoreWithNoMoreData();
            } else {
                refresh.finishLoadMore();
            }
        }
    }

}
