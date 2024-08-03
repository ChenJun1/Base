package com.base.kiap.utlis;

import java.util.Random;

/**
 * @Author: June
 * @CreateDate: 1/5/21 4:29 PM
 * @Description: java类作用描述
 */
public class RandomUtils {
    
    public static int[] rate=new int[8];//大转盘
    public static int[] rate12=new int[12];//大转盘
    public static int[] box=new int[8];//宝箱

    public static int[] rate12_5=new int[12];//大转盘
    public static int[] rate12_10=new int[12];//大转盘
    public static int[] rate12_20=new int[12];//大转盘
    public static int[] rate12_30=new int[12];//大转盘
    public static int[] rate12_50=new int[12];//大转盘
    public static int[] rate12_100=new int[12];//大转盘

//    /**
//     * 0出现的概率为%50
//     */
//    public static int rate[0] =10000;
//    /**
//     * 1出现的概率为%20
//     */
//    public static int rate[1] = 10000;
//    /**
//     * 2出现的概率为%15
//     */
//    public static int rate[2] =20000;
//    /**
//     * 3出现的概率为%10
//     */
//    public static int rate[3] = 30000;
//    /**
//     * 4出现的概率为%4
//     */
//    public static int rate[4] =40000;
//    /**
//     * 5出现的概率为%1
//     */
//    public static int rate[5] = 10000;
//
//    public static int rate[6] = 10000;
//
//    public static int rate[7] = 10000;

    /**
     * Math.random()产生一个double型的随机数，判断一下
     * 例如0出现的概率为%50，则介于0到0.50中间的返回0
     * @return int
     *
     */
    public static int PercentageRandom()
    {


        int randomNumber;
        randomNumber = new Random().nextInt(100000);
        if (randomNumber >= 0 && randomNumber <= rate[0])
        {
            return 0;
        }
        else if (randomNumber >= rate[0]  && randomNumber <= rate[0] + rate[1])
        {
            return 1;
        }
        else if (randomNumber >= rate[0] + rate[1]
                && randomNumber <= rate[0] + rate[1] + rate[2])
        {
            return 2;
        }
        else if (randomNumber >= rate[0] + rate[1] + rate[2]
                && randomNumber <= rate[0] + rate[1] + rate[2] + rate[3])
        {
            return 3;
        }
        else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3]
                && randomNumber <= rate[0] + rate[1] + rate[2] + rate[3] + rate[4])
        {
            return 4;
        }
        else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]
                && randomNumber <= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]
                + rate[5])
        {
            return 5;
        } else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]+rate[5]
                && randomNumber <= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]
                + rate[5]+rate[6])
        {
            return 6;
        }
        else if (randomNumber >= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]+rate[5]+rate[6]
                && randomNumber <= rate[0] + rate[1] + rate[2] + rate[3] + rate[4]
                + rate[5]+rate[6]+rate[7])
        {
            return 7;
        }
        return 0;
    }

    public static int PercentageRandom12(int BetMoney)
    {
        if (BetMoney<1||BetMoney>100) {
            return 0;
        }
        switch (BetMoney) {
            case 1:
                System.arraycopy(rate12_5, 0, rate12, 0, rate12.length);
                break;
            case 5:
                System.arraycopy(rate12_10, 0, rate12, 0, rate12.length);
                break;
            case 10:
                System.arraycopy(rate12_20, 0, rate12, 0, rate12.length);
                break;
            case 20:
                System.arraycopy(rate12_30, 0, rate12, 0, rate12.length);
                break;
            case 50:
                System.arraycopy(rate12_50, 0, rate12, 0, rate12.length);
                break;
            case 100:
                System.arraycopy(rate12_100, 0, rate12, 0, rate12.length);
                break;
        }

        int randomNumber;
        randomNumber = new Random().nextInt(100000);
        if (randomNumber >= 0 && randomNumber <= rate12[0])
        {
            return 0;
        }
        else if (randomNumber >= rate12[0]  && randomNumber <= rate12[0] + rate12[1])
        {
            return 1;
        }
        else if (randomNumber >= rate12[0] + rate12[1]
                && randomNumber <= rate12[0] + rate12[1] + rate12[2])
        {
            return 2;
        }
        else if (randomNumber >= rate12[0] + rate12[1] + rate12[2]
                && randomNumber <= rate12[0] + rate12[1] + rate12[2] + rate12[3])
        {
            return 3;
        }
        else if (randomNumber >= rate12[0] + rate12[1] + rate12[2] + rate12[3]
                && randomNumber <= rate12[0] + rate12[1] + rate12[2] + rate12[3] + rate12[4])
        {
            return 4;
        }
        else if (randomNumber >= rate12[0] + rate12[1] + rate12[2] + rate12[3] + rate12[4]
                && randomNumber <= rate12[0] + rate12[1] + rate12[2] + rate12[3] + rate12[4]
                + rate12[5])
        {
            return 5;
        } else if (randomNumber >= rate12[0] + rate12[1] + rate12[2] + rate12[3] + rate12[4]+rate12[5]
                && randomNumber <= rate12[0] + rate12[1] + rate12[2] + rate12[3] + rate12[4]
                + rate12[5]+rate12[6])
        {
            return 6;
        }
        else if (randomNumber >= rate12[0] + rate12[1] + rate12[2] + rate12[3] + rate12[4]+rate12[5]+rate12[6]
                && randomNumber <= rate12[0] + rate12[1] + rate12[2] + rate12[3] + rate12[4]
                + rate12[5]+rate12[6]+rate12[7])
        {
            return 7;
        }else if (randomNumber >= rate12[0] + rate12[1] + rate12[2] + rate12[3] + rate12[4]+rate12[5]+rate12[6]+rate12[7]
                && randomNumber <= rate12[0] + rate12[1] + rate12[2] + rate12[3] + rate12[4]
                + rate12[5]+rate12[6]+rate12[7]+rate12[8])
        {
            return 8;
        }else if (randomNumber >= rate12[0] + rate12[1] + rate12[2] + rate12[3] + rate12[4]+rate12[5]+rate12[6]+rate12[7]
                +rate12[8]
                && randomNumber <= rate12[0] + rate12[1] + rate12[2] + rate12[3] + rate12[4]
                + rate12[5]+rate12[6]+rate12[7]+rate12[8]+rate12[9])
        {
            return 9;
        }else if (randomNumber >= rate12[0] + rate12[1] + rate12[2] + rate12[3] + rate12[4]+rate12[5]+rate12[6]+rate12[7]
                +rate12[8]+rate12[9]
                && randomNumber <= rate12[0] + rate12[1] + rate12[2] + rate12[3] + rate12[4]
                + rate12[5]+rate12[6]+rate12[7]+rate12[8]+rate12[9]+rate12[10])
        {
            return 10;
        }else if (randomNumber >= rate12[0] + rate12[1] + rate12[2] + rate12[3] + rate12[4]+rate12[5]+rate12[6]+rate12[7]
                +rate12[8]+rate12[9]+rate12[10]
                && randomNumber <= rate12[0] + rate12[1] + rate12[2] + rate12[3] + rate12[4]
                + rate12[5]+rate12[6]+rate12[7]+rate12[8]+rate12[9]+rate12[10]+rate12[11])
        {
            return 11;
        }
        return 0;
    }

    /**
     * Math.random()产生一个double型的随机数，判断一下
     * 例如0出现的概率为%50，则介于0到0.50中间的返回0
     * @return int
     *
     */
    public static int PercenBoxRandom()
    {
        int randomNumber;
        randomNumber = new Random().nextInt(100000);
        if (randomNumber >= 0 && randomNumber <= box[0])
        {
            return 0;
        }
        else if (randomNumber >= box[0]  && randomNumber <= box[0] + box[1])
        {
            return 1;
        }
        else if (randomNumber >= box[0] + box[1]
                && randomNumber <= box[0] + box[1] + box[2])
        {
            return 2;
        }
        else if (randomNumber >= box[0] + box[1] + box[2]
                && randomNumber <= box[0] + box[1] + box[2] + box[3])
        {
            return 3;
        }
        else if (randomNumber >= box[0] + box[1] + box[2] + box[3]
                && randomNumber <= box[0] + box[1] + box[2] + box[3] + box[4])
        {
            return 4;
        }
        else if (randomNumber >= box[0] + box[1] + box[2] + box[3] + box[4]
                && randomNumber <= box[0] + box[1] + box[2] + box[3] + box[4]
                + box[5])
        {
            return 5;
        } else if (randomNumber >= box[0] + box[1] + box[2] + box[3] + box[4]+box[5]
                && randomNumber <= box[0] + box[1] + box[2] + box[3] + box[4]
                + box[5]+box[6])
        {
            return 6;
        }
        else if (randomNumber >= box[0] + box[1] + box[2] + box[3] + box[4]+box[5]+box[6]
                && randomNumber <= box[0] + box[1] + box[2] + box[3] + box[4]
                + box[5]+box[6]+box[7])
        {
            return 7;
        }
        return 0;
    }


    public static int getRandomInt(int sum) {
       return new Random().nextInt(sum)+1;
    }

    public static int getRandomInt0(int sum) {
        return new Random().nextInt(sum);
    }

    public static int getRandomIntRup(int sum) {
        return new Random().nextInt(sum);
    }
    /**
     * 随机号码
     * @return
     */
    public static String getRandomIntPhone() {
        StringBuilder str=new StringBuilder();
        int randomNumber;
        for (int i = 0; i < 5; i++) {
            randomNumber = new Random().nextInt(10);
            str.append(randomNumber);
        }
        return str.toString();
    }

}
