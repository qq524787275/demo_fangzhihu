package com.feeljoy.library.http;

import java.util.List;

/**
 * 网络返回数据
 * Created by Cate on 2016/3/25.
 */
public class FzResponse<T> {
    private int flag = -10;
    private String responseString;
    private String msg;
    private T data;
    private List<T> dataList;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
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

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
