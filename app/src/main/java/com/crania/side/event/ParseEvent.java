package com.crania.side.event;

import com.parse.ParseObject;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.event
 * @ClassName: ParseEvent
 * @Description: Parse传输对象
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/7 10:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/7 10:08
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ParseEvent{

    private Object object;
    private ParseObject parseObject;
    private String message;

    public ParseEvent(Object object) {
        this.object = object;
    }

    public ParseEvent(ParseObject parseObject) {
        this.parseObject = parseObject;
    }

    public ParseEvent(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public ParseObject getParseObject() {
        return parseObject;
    }

    public void setParseObject(ParseObject parseObject) {
        this.parseObject = parseObject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
