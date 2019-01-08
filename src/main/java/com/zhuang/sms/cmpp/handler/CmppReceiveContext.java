package com.zhuang.sms.cmpp.handler;

import com.zhuang.sms.cmpp.CmppClient;

/**
 * Created by zhuang on 2/2/2018.
 */
public class CmppReceiveContext {

    private byte[] bytes;

    private CmppClient cmppClient;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public CmppClient getCmppClient() {
        return cmppClient;
    }

    public void setCmppClient(CmppClient cmppClient) {
        this.cmppClient = cmppClient;
    }
}
