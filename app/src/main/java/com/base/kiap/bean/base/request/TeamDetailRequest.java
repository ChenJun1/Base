package com.base.kiap.bean.base.request;

public class TeamDetailRequest {
    public int type;
    public int page;

    public TeamDetailRequest(int type, int page) {
        this.type = type;
        this.page = page;
    }
}
