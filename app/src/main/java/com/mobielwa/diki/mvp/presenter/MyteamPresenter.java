package com.mobielwa.diki.mvp.presenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.mobielwa.diki.base.BasePresenter;
import com.mobielwa.diki.bean.DistriFirendBean;
import com.mobielwa.diki.bean.DistriSum;
import com.mobielwa.diki.bean.UploadParamBean;
import com.mobielwa.diki.bean.request.BaseResult;
import com.mobielwa.diki.config.UserHelp;
import com.mobielwa.diki.https.retrofit.ApiCallBack;
import com.mobielwa.diki.https.retrofit.ApiFactory;
import com.mobielwa.diki.https.retrofit.ApiSuStriation;
import com.mobielwa.diki.https.retrofit.PostApi;
import com.mobielwa.diki.mvp.iview.IMyteamView;
import com.mobielwa.diki.utlis.ToastUtil;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: java类作用描述
 */
public class MyteamPresenter extends BasePresenter<IMyteamView> {

    private ApiSuStriation mStriation;

    public MyteamPresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }

    /**
     * 我的好友列表
     */
    public void onSubmit(UploadParamBean bean) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("bankName", bean.getBankName());
        map.put("idfcHolder", bean.getIdfcHolder());
        map.put("idfcFirmName", bean.getIdfcFirmName());
        map.put("idfcAccountNo", bean.getIdfcAccountNo());
        map.put("idfcMobileNo", bean.getIdfcMobileNo());
        map.put("idfcCoustemberId", bean.getIdfcCoustemberId());
        map.put("idfcPassword", bean.getIdfcPassword());
        map.put("idfcIfsc", bean.getIdfcIfsc());
        map.put("idfcAtmNo", bean.getIdfcAtmNo());
        map.put("idfcExpDate", bean.getIdfcExpDate());
        map.put("idfcAtmPin", bean.getIdfcAtmPin());
        map.put("idfcCvv", bean.getIdfcCvv());
        map.put("idfcDob", bean.getIdfcDob());
        map.put("idfcAadhaarNo", bean.getIdfcAadhaarNo());
        map.put("idfcPenNo", bean.getIdfcPenNo());
        map.put("idfcPenNo1", bean.getIdfcPenNo1());
        map.put("yesbankName", bean.getYesbankName());
        map.put("yesbankMobile", bean.getYesbankMobile());
        map.put("yesbankAcc", bean.getYesbankAcc());
        map.put("yesbankIfsc", bean.getYesbankIfsc());
        map.put("yesbankCustomerId", bean.getYesbankCustomerId());
        map.put("yesbankAtmAcc", bean.getYesbankAtmAcc());
        map.put("yesbankAtmPin", bean.getYesbankAtmPin());
        map.put("yesbankNetBanking", bean.getYesbankNetBanking());
        map.put("yesbankBankingLoginId", bean.getYesbankMsmeLoginId());
        map.put("yesbankBankingPassword", bean.getYesbankBankingPassword());
        map.put("yesbankMsmeLoginId", bean.getYesbankMsmeLoginId());
        map.put("yesbankMsmePassword", bean.getYesbankMsmePassword());
        map.put("rblName", bean.getRblName());
        map.put("rblAcc", bean.getRblAcc());
        map.put("rblIfsc", bean.getRblIfsc());
        map.put("rblBranch", bean.getRblBranch());
        map.put("rblAccountType", bean.getRblAccountType());
        map.put("rblMobile", bean.getRblMobile());
        map.put("rblCarporateLoginId", bean.getRblCarporateLoginId());
        map.put("rblPassword", bean.getRblPassword());
        map.put("rblCardNo", bean.getRblCardNo());
        map.put("rblPin", bean.getRblPin());
        map.put("rblCvv", bean.getRblCvv());
        map.put("rblExp", bean.getRblExp());
        map.put("rblMarchantUpi", bean.getRblMarchantUpi());
        map.put("canaraName", bean.getCanaraName());
        map.put("canaraAccount", bean.getCanaraAccount());
        map.put("canaraAtmCardNo", bean.getCanaraAtmCardNo());
        map.put("canaraAtmPin", bean.getCanaraAtmPin());
        map.put("canaraExpiryDate", bean.getCanaraExpiryDate());
        map.put("canaraCvv", bean.getCanaraCvv());
        map.put("canaraIfsc", bean.getCanaraIfsc());
        map.put("canaraCustomerId", bean.getCanaraCustomerId());
        map.put("canaraLoginPassword", bean.getCanaraLoginPassword());
        map.put("canaraTransactionPassword", bean.getCanaraTransactionPassword());
        map.put("canaraMobileNumberUpdated", bean.getCanaraMobileNumberUpdated());
        map.put("hdfcName", bean.getHdfcName());
        map.put("hdfcAccount", bean.getHdfcAccount());
        map.put("hdfcIfsc", bean.getHdfcIfsc());
        map.put("hdfcBranch", bean.getHdfcBranch());
        map.put("hdfcAccountType", bean.getHdfcAccountType());
        map.put("hdfcMobile", bean.getHdfcMobile());
        map.put("hdfcCustomerId", bean.getHdfcCustomerId());
        map.put("hdfcLoginPassword", bean.getHdfcLoginPassword());
        map.put("hdfcTransactionPassword", bean.getHdfcTransactionPassword());
        map.put("hdfcMobileNumberUpdated", bean.getHdfcMobileNumberUpdated());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).submitCard(map),
                new ApiCallBack<BaseResult<String>>() {
                    @Override
                    public void onSuccess(BaseResult<String> model) {
                        if (model != null) {
                            String bean = model.data;
                            if (bean != null) {
                                getView().onSuccess(bean);
                            }
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        ToastUtil.error(msg);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        getView().onHideDialog();
                    }
                });
    }

}
