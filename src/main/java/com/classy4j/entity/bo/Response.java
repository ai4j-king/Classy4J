package com.classy4j.entity.bo;

import lombok.Data;

@Data
public class Response<T> {
    private int code;
    private String msg;
    private T data;

    public Response(T data) {
        this.data = data;
    }

    // 新增无参构造器
    public Response() {
        this.data = null; // 或者设置默认值
    }
}