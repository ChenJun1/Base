package com.base.kiap.bean.base;


import java.util.List;

public class BaseIndexBean {


    public int withdrawSwitch;
    public int inTransactionWithdrawAmountTotal;
    public int todayWithdrawSuccessAmountTotal;
    public int upiTools;
    public int bankTools;
    public NewbieTaskActivityDTO newbieTaskActivity;

    public static class NewbieTaskActivityDTO {
        public int rechargeTotal;
        public int day;
        public List<Integer> rewardList;
        public List<Integer> targetList;
    }
}
