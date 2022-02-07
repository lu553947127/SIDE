package com.crania.side.base;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.base
 * @ClassName: PageState
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/7 10:14
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/7 10:14
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PageState {
    public static final String PAGE_NORMAL = "PAGE_NORMAL";//正常界面
    public static final String PAGE_LOADING = "PAGE_LOADING";//加载中
    public static final String PAGE_REFRESH = "PAGE_REFRESH";//刷新状态
    public static final String PAGE_LOAD_SUCCESS = "PAGE_LOAD_SUCCESS";//加载成功
    public static final String PAGE_NET_ERROR = "PAGE_NET_ERROR";//无网络
    public static final String PAGE_SERVICE_ERROR = "PAGE_SERVICE_ERROR";//服务器返回成功，非200状态
    public static final String PAGE_ERROR = "PAGE_ERROR";//报错
}
