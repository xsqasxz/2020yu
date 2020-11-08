package com.small.utils.exception;

public class MyException extends RuntimeException {
	//异常错误码
    private int code;
    //异常信息
    private String msg;
	//异常构造方法 在使用时方便传入错误码和信息
	public MyException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
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
}