package com.zhuang.sms.cmpp.model;

import com.zhuang.sms.cmpp.enums.CmppMsgFmtValues;

/**
 * Created by zhuang on 2/3/2018.
 */
public abstract class CmppDeliver implements CmppMessage {

    public class MsgContent4StatusReport {

        //length:8
        private long msgId = 0;

        //length:7
        private String stat = "";

        //length:10
        private String SubmitTime = "";

        //length:10
        private String DoneTime = "";

        //length:cmpp20=21,cmpp30=32
        private String destTerminalId = "";

        private int smscSequence;

        public long getMsgId() {
            return msgId;
        }

        public void setMsgId(long msgId) {
            this.msgId = msgId;
        }

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public String getSubmitTime() {
            return SubmitTime;
        }

        public void setSubmitTime(String submitTime) {
            SubmitTime = submitTime;
        }

        public String getDoneTime() {
            return DoneTime;
        }

        public void setDoneTime(String doneTime) {
            DoneTime = doneTime;
        }

        public String getDestTerminalId() {
            return destTerminalId;
        }

        public void setDestTerminalId(String destTerminalId) {
            this.destTerminalId = destTerminalId;
        }

        public int getSmscSequence() {
            return smscSequence;
        }

        public void setSmscSequence(int smscSequence) {
            this.smscSequence = smscSequence;
        }

        @Override
        public String toString() {
            return "MsgContent4StatusReport{" +
                    "msgId=" + msgId +
                    ", stat='" + stat + '\'' +
                    ", SubmitTime='" + SubmitTime + '\'' +
                    ", DoneTime='" + DoneTime + '\'' +
                    ", destTerminalId='" + destTerminalId + '\'' +
                    ", smscSequence=" + smscSequence +
                    '}';
        }
    }

    protected CmppMessageHeader cmppMessageHeader;

    protected long msgId = 0;

    //length:21
    protected String destId = "";

    //length:10
    protected String serviceId = "";

    protected byte tpPId = 0;

    //长短信拆分时值需设为：1
    protected byte tpUdhi = 0;

    protected byte msgFmt = CmppMsgFmtValues.GB_CHINESE;

    protected byte registeredDelivery = 1;

    protected byte msgLength;

    protected String msgContent = "";

    protected MsgContent4StatusReport msgContent4StatusReport;


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

    public String getDestId() {
        return destId;
    }

    public void setDestId(String destId) {
        this.destId = destId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public byte getTpPId() {
        return tpPId;
    }

    public void setTpPId(byte tpPId) {
        this.tpPId = tpPId;
    }

    public byte getTpUdhi() {
        return tpUdhi;
    }

    public void setTpUdhi(byte tpUdhi) {
        this.tpUdhi = tpUdhi;
    }

    public byte getMsgFmt() {
        return msgFmt;
    }

    public void setMsgFmt(byte msgFmt) {
        this.msgFmt = msgFmt;
    }

    public byte getRegisteredDelivery() {
        return registeredDelivery;
    }

    public void setRegisteredDelivery(byte registeredDelivery) {
        this.registeredDelivery = registeredDelivery;
    }

    public byte getMsgLength() {
        return msgLength;
    }

    public void setMsgLength(byte msgLength) {
        this.msgLength = msgLength;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public MsgContent4StatusReport getMsgContent4StatusReport() {
        return msgContent4StatusReport;
    }

    public void setMsgContent4StatusReport(MsgContent4StatusReport msgContent4StatusReport) {
        this.msgContent4StatusReport = msgContent4StatusReport;
    }

    public boolean isStatusReport() {
        return registeredDelivery == 1;
    }

    @Override
    public byte[] toBytes() {
        return new byte[0];
    }


}
