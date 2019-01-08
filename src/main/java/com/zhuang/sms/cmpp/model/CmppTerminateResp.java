package com.zhuang.sms.cmpp.model;

/**
 * Created by zhuang on 2/5/2018.
 */
public class CmppTerminateResp implements CmppMessage{

    private CmppMessageHeader cmppMessageHeader;

    public CmppMessageHeader getCmppMessageHeader() {
        return cmppMessageHeader;
    }

    public void setCmppMessageHeader(CmppMessageHeader cmppMessageHeader) {
        this.cmppMessageHeader = cmppMessageHeader;
    }

    public CmppTerminateResp()
    {
    }

    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    public void fromBytes(byte[] bytes) {
    }

    @Override
    public String toString() {
        return "CmppTerminateResp{" +
                "cmppMessageHeader=" + cmppMessageHeader +
                '}';
    }
}
