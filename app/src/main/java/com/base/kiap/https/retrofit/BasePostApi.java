package com.base.kiap.https.retrofit;

import com.base.kiap.bean.base.BaseBankInfoBean;
import com.base.kiap.bean.base.BaseBannerBean;
import com.base.kiap.bean.base.BaseIndexBean;
import com.base.kiap.bean.base.BaseTeamDetailBean;
import com.base.kiap.bean.base.BaseUpiInfoBean;
import com.base.kiap.bean.base.BaseUsdtOrderBean;
import com.base.kiap.bean.base.BaseUserBean;
import com.base.kiap.bean.base.BaseUserInfoBean;
import com.base.kiap.bean.base.BaseTeamIndexInfoBean;
import com.base.kiap.bean.base.BaseWithdrawBean;
import com.base.kiap.bean.base.request.PayoutOrder;
import com.base.kiap.bean.request.BaseResult;

import java.util.LinkedHashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @Author: June
 * @CreateDate: 12/14/20 1:50 PM
 * @Description: java类作用描述
 */
public interface BasePostApi {
    @POST("/login")//登陆
    @FormUrlEncoded
    Observable<BaseResult<BaseUserBean>> login(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/user/regSmsCode")//获取验证码
    @FormUrlEncoded
    Observable<BaseResult<Integer>> registerCode(@FieldMap LinkedHashMap<String, Object> map);


    @POST("/user/smsCode")//获取验证码
    @FormUrlEncoded
    Observable<BaseResult<Integer>> smsCode(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/user/code")//注册
    @FormUrlEncoded
    Observable<BaseResult<Integer>> register(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/banner")//banner
    @FormUrlEncoded
    Observable<BaseResult<BaseBannerBean>> banner();

    @POST("/index")//首页
    @FormUrlEncoded
    Observable<BaseResult<BaseIndexBean>> indexData(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/user/index")//个人中心
    @FormUrlEncoded
    Observable<BaseResult<BaseUserInfoBean>> userInfo();

    @POST("/user/setPayPwd")//修改支付密码
    @FormUrlEncoded
    Observable<BaseResult<Object>> updatePayPwd(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/user/setLoginPwd")//修改登录密码
    @FormUrlEncoded
    Observable<BaseResult<Object>> updateLoginPwd(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/user/forgetPass")//忘记密码
    @FormUrlEncoded
    Observable<BaseResult<BaseUserBean>> forgetPass(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/bank")//添加银行卡
    @FormUrlEncoded
    Observable<BaseResult<Object>> addBank(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/bank")//修改银行卡
    @FormUrlEncoded
    Observable<BaseResult<Object>> updateBank(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/bank")//删除银行卡
    @FormUrlEncoded
    Observable<BaseResult<Object>> deleteBank(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/bank/list")//银行卡list
    @FormUrlEncoded
    Observable<BaseResult<List<BaseBankInfoBean>>> bankList();


    @POST("//upi")//添加Upi
    @FormUrlEncoded
    Observable<BaseResult<Object>> addUpi(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/upi")//修改Upi
    @FormUrlEncoded
    Observable<BaseResult<Object>> updateUpi(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/upi")//删除Upi
    @FormUrlEncoded
    Observable<BaseResult<Object>> deleteUpi(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/upi/list")//Upi list
    @FormUrlEncoded
    Observable<BaseResult<List<BaseUpiInfoBean>>> upiList();


    @POST("/withdraw")//创建卢比提现订单接口
    @FormUrlEncoded
    Observable<BaseResult<Object>> onCreateWithdraw(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/withdraw/switch")//提现开关
    @FormUrlEncoded
    Observable<BaseResult<Object>> withdrawSwitch(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/withdraw/orderId")//根据订单号查询卢比提现订单
    @FormUrlEncoded
    Observable<BaseResult<BaseWithdrawBean>> getWithdraw(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/withdraw/list")//提现订单列表
    @FormUrlEncoded
    Observable<BaseResult<List<BaseWithdrawBean>>> getWithdrawList(@FieldMap LinkedHashMap<String, Object> map);


    @POST("/usdt")//创建usdt充值订单
    @FormUrlEncoded
    Observable<BaseResult<Object>> onCreateUsdtOrder(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/usdt/orderId")//根据订单号查询usdt充值订单
    @FormUrlEncoded
    Observable<BaseResult<BaseUsdtOrderBean>> getUsdtOrder(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/usdt/order/list")//查询用户的usdt订单列表
    @FormUrlEncoded
    Observable<BaseResult<List<BaseUsdtOrderBean>>> getUsdtOrderList(@FieldMap LinkedHashMap<String, Object> map);



    @POST("/team/detail")//Team的Detail
    @FormUrlEncoded
    Observable<BaseResult<List<BaseTeamDetailBean>>> getTeamDetailList(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/team/index")//Team的index页面
    @FormUrlEncoded
    Observable<BaseResult<BaseTeamIndexInfoBean>> teamIndex(@FieldMap LinkedHashMap<String, Object> map);



    @POST("/money")//创建卢比充值订单接口
    @FormUrlEncoded
    Observable<BaseResult<Object>> onCreateRechargeMoney(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/money/payoutOrder")//createPayoutOrder
    @FormUrlEncoded
    Observable<BaseResult<Object>> onCreatePayoutOrder(@Body PayoutOrder user);

    @POST("/money/list")//卢比充值列表页面数据
    @FormUrlEncoded
    Observable<BaseResult<BaseWithdrawBean>> geMoneyOrderInfo(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/money/orderId")//根据订单号查询卢比充值订单
    @FormUrlEncoded
    Observable<BaseResult<List<BaseWithdrawBean>>> getMoneyOrder(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/money/order/list")//根据订单号查询卢比充值订单
    @FormUrlEncoded
    Observable<BaseResult<List<BaseWithdrawBean>>> getMoneyOrderList(@FieldMap LinkedHashMap<String, Object> map);



























}
