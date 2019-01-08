package com.zhuang.sms.cmpp.model;

import com.zhuang.sms.util.StringUtils;

/**
 * Created by zhuang on 1/27/2018.
 */
public abstract class CmppConnectResp implements CmppMessage {

    protected CmppMessageHeader cmppMessageHeader;

    protected int status;

    protected String authenticatorISMG;

    protected byte version;

    public CmppMessageHeader getCmppMessageHeader() {
        return cmppMessageHeader;
    }

    public void setCmppMessageHeader(CmppMessageHeader cmppMessageHeader) {
        this.cmppMessageHeader = cmppMessageHeader;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public String getAuthenticatorISMG() {
        return authenticatorISMG;
    }

    public void setAuthenticatorISMG(String authenticatorISMG) {

        StringUtils.ensureLengthEquals(authenticatorISMG, 16);

        this.authenticatorISMG = authenticatorISMG;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }


    @Override
    public byte[] toBytes() {
        return new byte[0];
    }


}
