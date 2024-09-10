package com.base.kiap.bean.request;

import java.io.Serializable;

/**
 * 返回实体泛型基类
 *
 * @param <T>
 */
public class BaseResult<T> implements Serializable {

    public String statusCode;

    public String statusInfo;

    public int status;

    public T data;


    public boolean isSuccess() {
        return status == 200;
    }

}
