package com.crania.side.bean;

import java.io.Serializable;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.bean
 * @ClassName: SendCodeBean
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/7 13:58
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/7 13:58
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SendCodeBean {
    /**
     * code : 0
     * data : {"msg":"校验验证码成功","tempPass":"Lu123456","status":true}
     * error :
     */

    private int code;
    private DataBean data;
    private String error;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static class DataBean implements Serializable {
        /**
         * msg : 校验验证码成功
         * tempPass : Lu123456
         * status : true
         */

        private String msg;
        private String tempPass;
        private boolean status;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getTempPass() {
            return tempPass;
        }

        public void setTempPass(String tempPass) {
            this.tempPass = tempPass;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }
    }
}
