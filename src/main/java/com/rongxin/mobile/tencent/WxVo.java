package com.rongxin.mobile.tencent;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author rx
 * @version 1.0
 * @date 2023/2/9 8:50
 */
@Data
public class WxVo {
    @ApiModelProperty(value = "用户昵称")
    private String userName;
    @ApiModelProperty(value = "code")
    private String code;
    @ApiModelProperty(value = "openId")
    private String openId;
    @ApiModelProperty(value = "加密数据")
    private String encryptedData;

    @ApiModelProperty(value = "iv")
    private String iv;

    @ApiModelProperty(value = "sessionKey")
    private String sessionKey;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
