package com.base.kiap.https.retrofit;

public enum StatusEnum {
    SUCCESS(200 ,"请求处理成功"),
    FAIL(300 ,"失败"),
    UNAUTHORIZED(401 ,"用户认证失败"),
    FORBIDDEN(403 ,"权限不足"),
    TOKEN_EXPIRE(211 ,"token过期"),
    SERVICE_ERROR(500, "系统异常，请稍后重试"),
    OPERATE_FREQUENTLY(999, "操作过快，稍后重试"),
    PARAM_INVALID(1000, "无效的参数"),
    MONEY_ORDER_NO_STOCK(1001, "卢比充值订单库存不足"),
    MONEY_ORDER_CREATE_FAIL(1002, "卢比充值订单创建失败"),
    PAYOUT_ORDER_ID_EXISTED(1003, "代付订单号重复"),
    PAY_USER_NOT_EXIST(1004, "商户不存在"),
    PAYOUT_ORDER_SIGN_ERROR(1005, "签名不正确"),
    PAY_USER_BALANCE_INSUFFICIENT(1006, "商户余额不足"),
    USDT_ORDER_CREATE_FAIL(1007, "USDT充值订单创建失败"),
    UPI_ALREADY_EXIST(1008, "UPI已经存在"),
    BANK_UPI_CREATE_FAIL(1009, "UPI创建失败"),
    UPI_NO_EXIST(1010, "UPI不存在"),
    BANK_UPI_UPDATE_FAIL(1011, "UPI编辑失败"),
    BANK_UPI_DELETE_FAIL(1012, "UPI删除失败"),
    BANK_ALREADY_EXIST(1013, "银行信息已经存在"),
    BANK_CREATE_FAIL(1014, "银行信息新增失败"),
    BANK_NO_EXIST(1015, "银行信息不存在"),
    BANK_UPDATE_FAIL(1016, "银行编辑失败"),
    BANK_DELETE_FAIL(1017, "银行删除失败"),
    USER_NOT_EXIST(1018, "用户不存在"),
    USER_BALANCE_NOT_ENOUGH(1019, "用户余额不足"),
    WITHDRAW_ORDER_CREATE_FAIL(1020, "提现订单创建失败"),
    MONEY_ORDER_PROCESSING(1021, "存在未完成的卢比充值订单，不能创建新订单"),
    ERR_INVITE_CODE(1022, "邀请码错误"),
    ERR_SMS_VERIFY(1023, "短信验证码错误"),
    ERR_NOT_ACCOUNT(1024, "账号已存在"),
    ERR_SMS(1025, "短信发送失败"),
    ERR_PHONE(1026, "手机号错误"),
    ERR_PASSWORD(1027, "密码错误"),
    ERR_SIGN(1028, "签名错误"),

    ;
    public final Integer code;

    public final String message;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
