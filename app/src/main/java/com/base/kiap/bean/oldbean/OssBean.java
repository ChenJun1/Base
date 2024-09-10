package com.base.kiap.bean.oldbean;

/**
 * @Author: June
 * @CreateDate: 12/30/20 5:25 PM
 * @Description: java类作用描述
 */
public class OssBean {
    private String AccessKeyId;
    private String AccessKeySecret;
    private String SecurityToken;

    public String getAccessKeyId() {
        return AccessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        AccessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return AccessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        AccessKeySecret = accessKeySecret;
    }

    public String getSecurityToken() {
        return SecurityToken;
    }

    public void setSecurityToken(String securityToken) {
        SecurityToken = securityToken;
    }
}
