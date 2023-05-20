package com.ebang.frontend.gateway.entity;

import java.io.Serializable;

public class CMSRes<T> implements Serializable {
    private int code;
    private T data;

    public CMSRes() {
    }

    public CMSRes(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
