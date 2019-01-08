package com.zhuang.sms.cmpp.model;

import com.zhuang.sms.util.ByteUtils;
import com.zhuang.sms.util.CmppUtils;

import java.util.Arrays;

/**
 * Created by zhuang on 2/5/2018.
 */
public class CmppDeliver30 extends CmppDeliver{

    //length:32
    private String srcTerminalId = "";

    private byte srcTerminalType;

    //length:20
    private String linkId="";

    public String getSrcTerminalId() {
        return srcTerminalId;
    }

    public void setSrcTerminalId(String srcTerminalId) {
        this.srcTerminalId = srcTerminalId;
    }

    public byte getSrcTerminalType() {
        return srcTerminalType;
    }

    public void setSrcTerminalType(byte srcTerminalType) {
        this.srcTerminalType = srcTerminalType;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    @Override
    public void fromBytes(byte[] bytes) {

        int index = 0;
        int length = 8;
        msgId = ByteUtils.toLong(Arrays.copyOfRange(bytes, index, index + length));
        index = index + length;
        length = 21;
        destId = ByteUtils.toStr(Arrays.copyOfRange(bytes, index, index + length));
        index = index + length;
        length = 10;
        serviceId = ByteUtils.toStr(Arrays.copyOfRange(bytes, index, index + length));
        index = index + length;
        length = 1;
        tpPId = bytes[index];
        index = index + length;
        length = 1;
        tpUdhi = bytes[index];
        index = index + length;
        length = 1;
        msgFmt = bytes[index];
        index = index + length;
        length = 32;
        srcTerminalId = ByteUtils.toStr(Arrays.copyOfRange(bytes, index, index + length));
        index = index + length;
        length = 1;
        srcTerminalType = bytes[index];
        index = index + length;
        length = 1;
        registeredDelivery = bytes[index];
        index = index + length;
        length = 1;
        msgLength = bytes[index];

        if (isStatusReport()) {
            msgContent4StatusReport = new CmppDeliver.MsgContent4StatusReport();
            index = index + length;
            length = 8;
            msgContent4StatusReport.setMsgId(ByteUtils.toLong(Arrays.copyOfRange(bytes, index, index + length)));
            index = index + length;
            length = 7;
            msgContent4StatusReport.setStat(ByteUtils.toStr(Arrays.copyOfRange(bytes, index, index + length)));
            index = index + length;
            length = 10;
            msgContent4StatusReport.setSubmitTime(ByteUtils.toStr(Arrays.copyOfRange(bytes, index, index + length)));
            index = index + length;
            length = 10;
            msgContent4StatusReport.setDoneTime(ByteUtils.toStr(Arrays.copyOfRange(bytes, index, index + length)));
            index = index + length;
            length = 32;
            msgContent4StatusReport.setDestTerminalId(ByteUtils.toStr(Arrays.copyOfRange(bytes, index, index + length)));
            index = index + length;
            length = 4;
            msgContent4StatusReport.setSmscSequence(ByteUtils.toInt(Arrays.copyOfRange(bytes, index, index + length)));

        } else {
            index = index + length;
            length = msgLength;
            msgContent = ByteUtils.toStr(Arrays.copyOfRange(bytes, index, index + length), CmppUtils.getCharsetByMsgFmt(msgFmt));
        }
        index = index + length;
        length = 20;
        linkId = ByteUtils.toStr(Arrays.copyOfRange(bytes, index, index + length));


    }

    @Override
    public String toString() {
        return "CmppDeliver30{" +
                "srcTerminalId='" + srcTerminalId + '\'' +
                ", srcTerminalType=" + srcTerminalType +
                ", linkId='" + linkId + '\'' +
                ", cmppMessageHeader=" + cmppMessageHeader +
                ", msgId=" + msgId +
                ", destId='" + destId + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", tpPId=" + tpPId +
                ", tpUdhi=" + tpUdhi +
                ", msgFmt=" + msgFmt +
                ", registeredDelivery=" + registeredDelivery +
                ", msgLength=" + msgLength +
                ", msgContent='" + msgContent + '\'' +
                ", msgContent4StatusReport=" + msgContent4StatusReport +
                '}';
    }
}
