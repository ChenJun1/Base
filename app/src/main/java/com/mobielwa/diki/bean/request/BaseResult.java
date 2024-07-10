package com.mobielwa.diki.bean.request;

import java.io.Serializable;

/**
 * 返回实体泛型基类
 *
 * @param <T>
 */
public class BaseResult<T> implements Serializable {
    public String msg;
    public int code;
    public T data;


    public boolean isSuccess() {
        return code == 0;
    }

}
