package com.example.test.login.entity.tools;

public class Result<T> {
    private String msg;

    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Result{"+
                "msg='" + msg + '\'' +
                ", code=" + code +
                '}';
    }
}
