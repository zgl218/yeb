package com.yy.yeb.exceptions;

/**
 * 自定义参数异常
 *
 * @author xingtong
 */
public class ParamsException extends RuntimeException {
    private static final long serialVersionUID = 5214395428800197858L;

    private Integer code = 300;
    private String msg = "参数异常！";

    public ParamsException(){
        super("参数异常！");
    }

    public ParamsException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ParamsException(Integer code) {
        super("参数异常！");
        this.code = code;
    }

    public ParamsException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public ParamsException setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ParamsException setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}