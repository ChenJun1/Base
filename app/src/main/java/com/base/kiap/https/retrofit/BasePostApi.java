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
import com.base.kiap.bean.base.DepositInrHistoryBean;
import com.base.kiap.bean.base.DepositInrInfoBean;
import com.base.kiap.bean.base.DepositUsdtInfoBean;
import com.base.kiap.bean.base.request.InrBuyRequest;
import com.base.kiap.bean.base.request.InrListRequest;
import com.base.kiap.bean.base.request.AddBankRequest;
import com.base.kiap.bean.base.request.PinRequest;
import com.base.kiap.bean.base.request.RegisterRequest;
import com.base.kiap.bean.request.BaseResult;
import com.base.kiap.bean.base.request.TeamDetailRequest;

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
    @POST("/logout")
    Observable<BaseResult<Object>> logout();

    @POST("/regSmsCode")//获取验证码
    @FormUrlEncoded
    Observable<BaseResult<Integer>> registerCode(@FieldMap LinkedHashMap<String, Object> map);


    @POST("smsCode")//获取验证码
    @FormUrlEncoded
    Observable<BaseResult<Object>> smsCode(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/register")//注册
    Observable<BaseResult<BaseUserBean>> register(@Body RegisterRequest map);

    @POST("/banner")//banner
    Observable<BaseResult<List<BaseBannerBean>>> banner();

    @POST("/index")//首页
    @FormUrlEncoded
    Observable<BaseResult<BaseIndexBean>> indexData(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/user/index")//个人中心
    Observable<BaseResult<BaseUserInfoBean>> userInfo();

    @POST("/user/setPayPwd")//修改支付密码
    Observable<BaseResult<Object>> updatePayPwd(@Body PinRequest request);

    @POST("/user/setLoginPwd")//修改登录密码
    @FormUrlEncoded
    Observable<BaseResult<Object>> updateLoginPwd(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/user/forgetPass")//忘记密码
    @FormUrlEncoded
    Observable<BaseResult<BaseUserBean>> forgetPass(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/bank/create")//添加银行卡
    Observable<BaseResult<Object>> addBank(@Body AddBankRequest map);

    @POST("/bank/update")//修改银行卡
    Observable<BaseResult<Object>> updateBank(@Body AddBankRequest map);

    @POST("/bank/delete")//删除银行卡
    Observable<BaseResult<Object>> deleteBank(@Body BaseBankInfoBean map);

    @POST("/bank/list")//银行卡list
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
    Observable<BaseResult<DepositUsdtInfoBean>> onCreateUsdtOrder();

    @POST("/usdt/orderId")//根据订单号查询usdt充值订单
    @FormUrlEncoded
    Observable<BaseResult<BaseUsdtOrderBean>> getUsdtOrder(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/usdt/order/list")//查询用户的usdt订单列表
    @FormUrlEncoded
    Observable<BaseResult<List<DepositInrHistoryBean>>> getUsdtOrderList(@FieldMap LinkedHashMap<String, Object> map);



    @POST("/team/detail")//Team的Detail
    Observable<BaseResult<List<BaseTeamDetailBean>>> getTeamDetailList(@Body TeamDetailRequest map);

    @POST("/team/index")//Team的index页面
    @FormUrlEncoded
    Observable<BaseResult<BaseTeamIndexInfoBean>> teamIndex(@FieldMap LinkedHashMap<String, Object> map);



    @POST("/money")//创建卢比充值订单接口
    @FormUrlEncoded
    Observable<BaseResult<Object>> onCreateRechargeMoney(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/money/create")//createPayoutOrder
    Observable<BaseResult<Object>> onCreatePayoutOrder(@Body InrBuyRequest user);

    @POST("/money/list")//卢比充值列表页面数据
    Observable<BaseResult<List<DepositInrInfoBean>>> geMoneyOrderInfo(@Body InrListRequest request);

    @POST("/money/orderId")//根据订单号查询卢比充值订单
    Observable<BaseResult<List<BaseWithdrawBean>>> getMoneyOrder(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/money/order/list")//根据订单号查询卢比充值订单
    @FormUrlEncoded
    Observable<BaseResult<List<DepositInrHistoryBean>>> getMoneyHistoryOrderList(@FieldMap LinkedHashMap<String, Object> map);



























}
