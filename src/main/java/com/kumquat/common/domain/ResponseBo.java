package com.kumquat.common.domain;

import java.util.HashMap;

/**
 * @Author:deepblue
 * @Date:2019/4/29 18:10
 * @Description:状态类
 **/
public class ResponseBo extends HashMap<String,Object> {
    // 成功
    private static final Integer SUCCESS = 200;
    // 警告
    private static final Integer WARN = 1;
    // 异常 失败
    private static final Integer FAIL = 500;

    public ResponseBo() {
        put("code", SUCCESS);
        put("msg", "操作成功");
    }

    public static ResponseBo error(Object msg) {
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("code", FAIL);
        responseBo.put("msg", msg);
        return responseBo;
    }

    public static ResponseBo warn(Object msg) {
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("code", WARN);
        responseBo.put("msg", msg);
        return responseBo;
    }

    public static ResponseBo ok(Object msg) {
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("code", SUCCESS);
        responseBo.put("msg", msg);
        return responseBo;
    }

    public static ResponseBo ok() {
        return new ResponseBo();
    }

    public static ResponseBo error() {
        return ResponseBo.error("");
    }

    @Override
    public ResponseBo put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
