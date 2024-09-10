package com.base.kiap.https.retrofit;

import com.base.kiap.bean.oldbean.ApplyBean;
import com.base.kiap.bean.oldbean.ApplyChannBean;
import com.base.kiap.bean.oldbean.BannerBean;
import com.base.kiap.bean.oldbean.BindBean;
import com.base.kiap.bean.oldbean.CashRecordBean;
import com.base.kiap.bean.oldbean.CodeBean;
import com.base.kiap.bean.oldbean.ConfigBean;
import com.base.kiap.bean.oldbean.DistriFirendBean;
import com.base.kiap.bean.oldbean.ExtractRecordBean;
import com.base.kiap.bean.oldbean.FinancialBean;
import com.base.kiap.bean.oldbean.FinancialOrderBean;
import com.base.kiap.bean.oldbean.FinancialSumBean;
import com.base.kiap.bean.oldbean.IndexBean;
import com.base.kiap.bean.oldbean.IndexStepSatus;
import com.base.kiap.bean.oldbean.MemberBean;
import com.base.kiap.bean.oldbean.MemberCardBean;
import com.base.kiap.bean.oldbean.MemberIndexBean;
import com.base.kiap.bean.oldbean.OrderBean;
import com.base.kiap.bean.oldbean.OssBean;
import com.base.kiap.bean.oldbean.PayoutGetBean;
import com.base.kiap.bean.oldbean.RechargeRecordBean;
import com.base.kiap.bean.oldbean.UsdtBean;
import com.base.kiap.bean.oldbean.UsdtIndexBean;
import com.base.kiap.bean.oldbean.UserBean;
import com.base.kiap.bean.oldbean.WithdrawalFeeBean;
import com.base.kiap.bean.dao.MessageBean;
import com.base.kiap.bean.request.BaseResult;

import java.util.LinkedHashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @Author: June
 * @CreateDate: 12/14/20 1:50 PM
 * @Description: java类作用描述
 */
public interface PostApi {

    @POST("/user/find")//获取用户信息
    @FormUrlEncoded
    Observable<BaseResult<UserBean>> findUser(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/index/index")//获取首页信息
    @FormUrlEncoded
    Observable<BaseResult<IndexBean>> findIndex(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/user/indexStatus")//获取首页完成状态
    @FormUrlEncoded
    Observable<BaseResult<IndexStepSatus>> findIndexStatus(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/levelSet/acceptVip")
    @FormUrlEncoded
    Observable<BaseResult<Object>> acceptVip(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/levelSet/rejectVip")
    @FormUrlEncoded
    Observable<BaseResult<Object>> rejectVip(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/user/login")//登陆
    @FormUrlEncoded
    Observable<BaseResult<UserBean>> login(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/user/code")//获取验证码
    @FormUrlEncoded
    Observable<BaseResult<CodeBean>> code(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/index/indexBotmom")//首页Banner
    @FormUrlEncoded
    Observable<BaseResult<List<BannerBean>>> indexBotmom(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/member/index")//会员首页
    @FormUrlEncoded
    Observable<BaseResult<MemberIndexBean>> memberIndex(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/member/memberList")//会员列表
    @FormUrlEncoded
    Observable<BaseResult<List<MemberBean>>> memberList(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/member/addMember")//增加成员
    @FormUrlEncoded
    Observable<BaseResult<Object>> addMember(@FieldMap LinkedHashMap<String, Object> map);


    @POST("/member/cardList")//会员列表
    @FormUrlEncoded
    Observable<BaseResult<List<MemberCardBean>>> cardList(@FieldMap LinkedHashMap<String, Object> map);


    @POST("/user/setName")//设置名称
    @FormUrlEncoded
    Observable<BaseResult<Object>> onSetName(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/index/stepOk")//步骤完成
    @FormUrlEncoded
    Observable<BaseResult<String>> stepOk(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/telegramBot/bind")//绑定
    @FormUrlEncoded
    Observable<BaseResult<BindBean>> findBind(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/user/setPayPwd")//设置支付密码
    @FormUrlEncoded
    Observable<BaseResult<Object>> onSetPayPwd(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/user/onSetWidthrawalAddr")//设置提现地址
    @FormUrlEncoded
    Observable<BaseResult<Object>> onSetWidthrawalAddr(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/withdrawal/findUsdtAddr")//设置提现地址
    @FormUrlEncoded
    Observable<BaseResult<String>> findUsdtAddr(@FieldMap LinkedHashMap<String, Object> map);


    @POST("/user/onSetUsdtAddress")//绑定USDT
    @FormUrlEncoded
    Observable<BaseResult<Object>> onSetUsdtAddress(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/turntable/findCount")//查询大转盘次数
    @FormUrlEncoded
    Observable<BaseResult<Integer>> findTurntableCount(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/turntable/draw")//抽奖
    @FormUrlEncoded
    Observable<BaseResult<Integer>> turntableDraw(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/withdrawal/list")//提现记录
    @FormUrlEncoded
    Observable<BaseResult<List<ExtractRecordBean>>> onWithdrawal(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/incomePay/income")//收入记录
    @FormUrlEncoded
    Observable<BaseResult<List<CashRecordBean>>> onIncomeRecord(@FieldMap LinkedHashMap<String, Object> map);


    @POST("/incomePay/pay")//支出记录
    @FormUrlEncoded
    Observable<BaseResult<List<CashRecordBean>>> onPayRecord(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/withdrawal/list")//USDT兑换
    @FormUrlEncoded
    Observable<BaseResult<List<OrderBean>>> onOrderList(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/usdt/index")//USDT首页
    @FormUrlEncoded
    Observable<BaseResult<UsdtIndexBean>> onIndex(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/task/placeOrder")//接单列表
    @FormUrlEncoded
    Observable<BaseResult<Object>> onPlaceOrder(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/task/submitTask")//提交任务
    @FormUrlEncoded
    Observable<BaseResult<Object>> onSubmitTask(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/ossKey/find")//阿里云授权
    @FormUrlEncoded
    Observable<BaseResult<OssBean>> onOssKey(@FieldMap LinkedHashMap<String, Object> map);


    @POST("/card/save")//保存银行卡
    @FormUrlEncoded
    Observable<BaseResult<Object>> onSaveBank(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/setting/find")//系统配置
    @FormUrlEncoded
    Observable<BaseResult<ConfigBean>> onSettingFind(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/withdrawal/collect")//申请提现
    @FormUrlEncoded
    Observable<BaseResult<String>> onApplyWithdrawal(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/withdrawal/collectUsdt")//申请提现
    @FormUrlEncoded
    Observable<BaseResult<String>> onApplyWithdrawalUsdt(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/withdrawal/bankWithdrawal")//判断是否可以使用银行提现
    @FormUrlEncoded
    Observable<BaseResult<Boolean>> isBankWithdrawal(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/withdrawal/withdrawalFee")//提现免费
    @FormUrlEncoded
    Observable<BaseResult<WithdrawalFeeBean>> withdrawalFee(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/withdrawal/getRate")//获取汇率
    @FormUrlEncoded
    Observable<BaseResult<Float>> getRate(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/withdrawal/withdrawalMethod")//提现方式
    @FormUrlEncoded
    Observable<BaseResult<List<ApplyChannBean>>> withdrawalMethod(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/withdrawal/findUsdtBind")//判断是否已经绑定USDT
    @FormUrlEncoded
    Observable<BaseResult<Boolean>> findUsdtBind(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/withdrawal/findBankBind")//判断是否已经绑定银行卡
    @FormUrlEncoded
    Observable<BaseResult<Boolean>> findBankBind(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/pay/recharge")//充值
    @FormUrlEncoded
    Observable<BaseResult<ApplyBean>> onApplyrecharge(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/usdt/find")//查询USDT地址
    @FormUrlEncoded
    Observable<BaseResult<UsdtBean>> findUsdtAddress(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/withdrawal/getInfo")//查询余额和汇率
    @FormUrlEncoded
    Observable<BaseResult<PayoutGetBean>> getWithdrawalInfo(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/usdt/rechargeRecord")//查询充值记录
    @FormUrlEncoded
    Observable<BaseResult<List<RechargeRecordBean>>> rechargeDetail(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/usdt/createOrder")//创建USDT订单
    @FormUrlEncoded
    Observable<BaseResult<String>> createUsdtOrder(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/pay/buyMembers")//购买会员
    @FormUrlEncoded
    Observable<BaseResult<ApplyBean>> onBuyMembers(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/levelSet/balanceUpgrade")//会员升级
    @FormUrlEncoded
    Observable<BaseResult<Long>> onBalanceUpgrade(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/prize/winning")//中奖
    @FormUrlEncoded
    Observable<BaseResult<Object>> onWinning(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/index/install")//中奖
    @FormUrlEncoded
    Observable<BaseResult<Object>> install(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/payMethod/find")//获取渠道支付方式
    @FormUrlEncoded
    Observable<BaseResult<List<ApplyChannBean>>> payMethod(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/rzPay/callback")//获取支付rzkey
    @FormUrlEncoded
    Observable<BaseResult<Object>> getRzcallback(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/notice/count")//查询消息未读数
    @FormUrlEncoded
    Observable<BaseResult<Integer>> getMsgCount(@FieldMap LinkedHashMap<String, Object> map);


    @POST("/notice/find")//消息列表
    @FormUrlEncoded
    Observable<BaseResult<List<MessageBean>>> getMsgList(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/faq/find")//FAQ
    @FormUrlEncoded
    Observable<BaseResult<List<MessageBean>>> getFaqList(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/distrib/list")//分销好友
    @FormUrlEncoded
    Observable<BaseResult<List<DistriFirendBean>>> getDistribList(@FieldMap LinkedHashMap<String, Object> map);


    @POST("/member/submitCard")//提交卡信息
    @FormUrlEncoded
    Observable<BaseResult<String>> submitCard(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/cfp/product/find")//查询理财列表
    @FormUrlEncoded
    Observable<BaseResult<List<FinancialBean>>> getProductFindList(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/cfp/product/buy")//购买理财
    @FormUrlEncoded
    Observable<BaseResult<Object>> ProductBuy(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/cfp/order/find")//查询理财列表
    @FormUrlEncoded
    Observable<BaseResult<List<FinancialOrderBean>>> getProductOrderFindList(@FieldMap LinkedHashMap<String, Object> map);

    @POST("/cfp/order/withdrawald")//理财提现
    @FormUrlEncoded
    Observable<BaseResult<Object>> onProductwithdrawald(@FieldMap LinkedHashMap<String, Object> map);


    @POST("/cfp/order/sum")//汇总信息
    @FormUrlEncoded
    Observable<BaseResult<FinancialSumBean>> onProductsum(@FieldMap LinkedHashMap<String, Object> map);
}
