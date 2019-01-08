package com.zhuang.sms.cmpp.model;

import com.zhuang.sms.util.ByteUtils;
import com.zhuang.sms.util.CmppUtils;

import java.util.Arrays;

/**
 * Created by zhuang on 2/5/2018.
 */
public class CmppDeliver20 extends CmppDeliver{

    //length:21
    protected String srcTerminalId = "";

    //length:8
    protected String reserve = "";

    public String getSrcTerminalId() {
        return srcTerminalId;
    }

    public void setSrcTerminalId(String srcTerminalId) {
        this.srcTerminalId = srcTerminalId;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
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
        length = 21;
        srcTerminalId = ByteUtils.toStr(Arrays.copyOfRange(bytes, index, index + length));
        index = index + length;
        length = 1;
        registeredDelivery = bytes[index];
        index = index + length;
        length = 1;
        msgLength = bytes[index];

        if (isStatusReport()) {
            msgContent4StatusReport = new MsgContent4StatusReport();
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
            length = 21;
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
        length = 8;
        reserve = ByteUtils.toStr(Arrays.copyOfRange(bytes, index, index + length));

    }

    @Override
    public String toString() {
        return "CmppDeliver20{" +
                "srcTerminalId='" + srcTerminalId + '\'' +
                ", reserve='" + reserve + '\'' +
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
