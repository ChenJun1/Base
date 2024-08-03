package com.base.kiap.utlis;


import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 格式化货币数字
 */

public class StringFormatUtils {



    public static String indMoneyFormat(double money) {
        return String.format("Rp.%,.0f", money).replaceAll(",", ".");
    }

    public static String indMoneyFormat2(double money) {
        return String.format("Rp.%,.0f", money).replaceAll(",", ".");
    }

    public static String moneyFormat(double paidAmount) {
        return moneyFormat(String.valueOf(paidAmount));
    }

    public static String moneyFormat(String paidAmount) {
        if (paidAmount == null) {
            return null;
        }
        String[] split = null;
        if (paidAmount.contains(".")) {
            split = paidAmount.split("\\.");
            paidAmount = split[0];
        }
        StringBuilder result = new StringBuilder();
        int length = paidAmount.length();
        int startIndex = length;
        String substring = null;
        while (startIndex > 3) {
            substring = paidAmount.substring(startIndex - 3, startIndex);
            startIndex = startIndex - 3;
            result.insert(0, "," + substring);
        }
        result.insert(0, paidAmount.substring((0), startIndex));
        if (split != null && split.length >= 2) {
            String str = "0";
            if (split[1].substring(1)==null||split[1].substring(1).isEmpty()||split[1].substring(1).equals("0")) {
                str = "0";
            }else{
                str = split[1].substring(1);
            }

            result.append("." + split[1].substring(0, 1)+str);
        }
        return result.toString();
    }


    public static double formatDouble(double d, int i) {
        BigDecimal bg = new BigDecimal(d);
        return bg.setScale(i, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static String formatNumber(int selectInterest) {
        return CurrencyFormatUtils.formatDecimal(String.valueOf(selectInterest));
    }

    public static String formatNumber(float selectInterest) {
        if(selectInterest == (int)selectInterest){
            return (int)selectInterest+"";
        }
        return String.valueOf(new BigDecimal(selectInterest).setScale(4, RoundingMode.HALF_UP));
    }


    public static String formatNumber(String selectInterest) {
        return CurrencyFormatUtils.formatDecimal(selectInterest);
    }
}
