package com.base.kiap.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

/**
 * @Author: June
 * @CreateDate: 12/28/20 4:31 PM
 * @Description: java类作用描述
 */
public class OrderBean implements Parcelable {

    @Id(autoincrement = true)
    @Index(unique = true)
    private int id;
    private String txMode;
    private int money;
    private int amount;
    private String createTime;
    private int status;
    private int rate;
    private String accountNo;
    private String accountHolder;
    private String ifsc;
    private String transactionTime;

    protected OrderBean(Parcel in) {
        id = in.readInt();
        txMode = in.readString();
        money = in.readInt();
        amount = in.readInt();
        createTime = in.readString();
        status = in.readInt();
        rate = in.readInt();
        accountNo = in.readString();
        accountHolder = in.readString();
        ifsc = in.readString();
        transactionTime = in.readString();
    }
    public OrderBean() {
    }

    public static final Creator<OrderBean> CREATOR = new Creator<OrderBean>() {
        @Override
        public OrderBean createFromParcel(Parcel in) {
            return new OrderBean(in);
        }

        @Override
        public OrderBean[] newArray(int size) {
            return new OrderBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTxMode() {
        return txMode;
    }

    public void setTxMode(String txMode) {
        this.txMode = txMode;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(txMode);
        dest.writeInt(money);
        dest.writeInt(amount);
        dest.writeString(createTime);
        dest.writeInt(status);
        dest.writeInt(rate);
        dest.writeString(accountNo);
        dest.writeString(accountHolder);
        dest.writeString(ifsc);
        dest.writeString(transactionTime);
    }
}
