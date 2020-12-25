package com.yueqian.xk.beans;

/**
 * ajax请求返回到前台的数据格式（最终转换为json）
 */
public class AjaxResponseInfo<T> {
    private int code;//验证码，-1：参数不足，-2：权限不足，0：正常应答
    private String msg;//提示消息
    private T data;//数据

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
