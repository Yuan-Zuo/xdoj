package com.xdoj.demo.result;

public class CodeMsg {
    private int code;
    private String msg;


    //
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");

    //通用异常
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");

    //登录模块异常 5002xx
//    public static CodeMsg SUCCESS = new CodeMsg(0, "success");

    //答题模块 5003xx

    //注册模块 5004xx

    //添加题目模块 5005xx

    //

    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

}
