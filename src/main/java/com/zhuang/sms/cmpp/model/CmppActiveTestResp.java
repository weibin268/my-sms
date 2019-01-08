package com.zhuang.sms.cmpp.model;

/**
 * Created by zhuang on 2/2/2018.
 */
public class CmppActiveTestResp implements CmppMessage {

    private CmppMessageHeader cmppMessageHeader;

    private byte reserved;


    public CmppMessageHeader getCmppMessageHeader() {
        return cmppMessageHeader;
    }

    public void setCmppMessageHeader(CmppMessageHeader cmppMessageHeader) {
        this.cmppMessageHeader = cmppMessageHeader;
    }

    public CmppActiveTestResp() {

    }

    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    public void fromBytes(byte[] bytes) {

        reserved= bytes[0];

    }

    @Override
    public String toString() {
        return "CmppActiveTestResp{" +
                "cmppMessageHeader=" + cmppMessageHeader +
                ", reserved=" + reserved +
                '}';
    }
}
