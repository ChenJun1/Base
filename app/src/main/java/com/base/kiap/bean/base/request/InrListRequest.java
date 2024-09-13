package com.base.kiap.bean.base.request;

public class InrListRequest {
    public long minAmount = 0;
    public long maxAmount = 1000 * 10000;
    public int page = 1;

    public InrListRequest(long minAmount, long maxAmount, int page) {
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.page = page;
    }

    public InrListRequest() {
    }
}
