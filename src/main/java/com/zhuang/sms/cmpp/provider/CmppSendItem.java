package com.zhuang.sms.cmpp.provider;

/**
 * Created by zhuang on 2/3/2018.
 */
public class CmppSendItem {

    private String[] phoneNoList;

    private String spCode;

    /***
     * length<=8
     */
    private String spCodeSuffix;

    private String msgContent;

    public String[] getPhoneNoList() {
        return phoneNoList;
    }

    public void setPhoneNoList(String[] phoneNoList) {
        this.phoneNoList = phoneNoList;
    }

    public String getSpCode() {
        return spCode;
    }

    public void setSpCode(String spCode) {
        this.spCode = spCode;
    }

    public String getSpCodeSuffix() {
        return spCodeSuffix;
    }

    public void setSpCodeSuffix(String spCodeSuffix) {
        this.spCodeSuffix = spCodeSuffix;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
}
