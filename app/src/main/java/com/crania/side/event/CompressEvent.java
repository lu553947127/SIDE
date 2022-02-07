package com.crania.side.event;

import java.io.File;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.event
 * @ClassName: CompressEvent
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/7 10:26
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/7 10:26
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CompressEvent {

    private File compressFile;

    public CompressEvent(File compressFile) {
        this.compressFile = compressFile;
    }

    public File getCompressFile() {
        return compressFile;
    }

    public void setCompressFile(File compressFile) {
        this.compressFile = compressFile;
    }
}
