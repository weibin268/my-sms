package com.zhuang.sms.cmpp.model;


import com.zhuang.sms.util.ByteUtils;

import java.util.Arrays;

/**
 * Created by zhuang on 2/1/2018.
 */
public class CmppSubmitResp implements CmppMessage{

    private CmppMessageHeader cmppMessageHeader;

    private long msgId = 0;

    private byte result;

    public CmppMessageHeader getCmppMessageHeader() {
        return cmppMessageHeader;
    }

    public void setCmppMessageHeader(CmppMessageHeader cmppMessageHeader) {
        this.cmppMessageHeader = cmppMessageHeader;
    }

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public byte getResult() {
        return result;
    }

    public void setResult(byte result) {
        this.result = result;
    }

    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    public void fromBytes(byte[] bytes) {

        msgId= ByteUtils.toLong(Arrays.copyOfRange(bytes,0,8));
        result=bytes[8];

    }

    @Override
    public String toString() {
        return "CmppSubmitResp{" +
                "cmppMessageHeader=" + cmppMessageHeader +
                ", msgId=" + msgId +
                ", result=" + result +
                '}';
    }
}
