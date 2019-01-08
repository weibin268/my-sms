package com.zhuang.sms.cmpp.model;

/**
 * Created by zhuang on 1/27/2018.
 */
public interface CmppMessage {

    public byte[] toBytes();

    public void fromBytes(byte[] bytes);

}
