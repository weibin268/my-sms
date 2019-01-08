package com.zhuang.sms.cmpp.model;

import com.zhuang.sms.cmpp.enums.CmppCommandIdValues;
import com.zhuang.sms.cmpp.enums.CmppMsgFmtValues;
import com.zhuang.sms.util.ByteUtils;
import com.zhuang.sms.util.CmppUtils;
import com.zhuang.sms.util.StringUtils;

/**
 * Created by zhuang on 1/29/2018.
 */
public abstract class CmppSubmit implements CmppMessage {

    public static class MsgContext4Split{

        private int pkTotal;

        private int pkNumber;

        private byte[] msgContentBytes;

        public int getPkTotal() {
            return pkTotal;
        }

        public void setPkTotal(int pkTotal) {
            this.pkTotal = pkTotal;
        }

        public int getPkNumber() {
            return pkNumber;
        }

        public void setPkNumber(int pkNumber) {
            this.pkNumber = pkNumber;
        }

        public byte[] getMsgContentBytes() {
            return msgContentBytes;
        }

        public void setMsgContentBytes(byte[] msgContentBytes) {
            this.msgContentBytes = msgContentBytes;
        }

        public byte[] toBytes()
        {
            byte[] headBytes = new byte[6];
            headBytes[0] = 0x05;
            headBytes[1] = 0x00;
            headBytes[2] = 0x03;
            //bytes[3]=0x00;
            headBytes[4] = (byte) pkTotal;
            headBytes[5] = (byte) pkNumber;

            return ByteUtils.concat(headBytes,msgContentBytes);
        }
    }

    protected CmppMessageHeader cmppMessageHeader;

    protected long msgId = 0;

    //长短信拆分时值需作相应修改
    protected byte pkTotal = 1;

    //长短信拆分时值需作相应修改
    protected byte pkNumber = 1;

    protected byte registeredDelivery = 1;

    protected byte msgLevel = 0;

    //length:10
    protected String serviceId = "";

    protected byte feeUserType = 0;

    protected byte tpPId = 0;

    //长短信拆分时值需设为：1
    protected byte tpUdhi = 0;

    //长短信拆分时值需设为：UCS2
    protected byte msgFmt = CmppMsgFmtValues.GB_CHINESE;

    //length:6,信息内容来源(SP_Id)
    protected String msgSrc;

    //length:2
    protected String feeType = "01";

    //length:6
    protected String feeCode = "0";

    protected String valIdTime = "";

    protected String atTime = "";

    //服务代码
    protected String srcId;

    //短信群发时值需设为：群发的目标用户数量
    protected byte destUsrTl = 1;


    protected byte msgLength;

    protected String msgContent = "";

    protected byte[] msgContentBytes;


    public CmppMessageHeader getCmppMessageHeader() {
        return cmppMessageHeader;
    }

    public void setCmppMessageHeader(CmppMessageHeader cmppMessageHeader) {
        this.cmppMessageHeader = cmppMessageHeader;
    }

    public byte getPkTotal() {
        return pkTotal;
    }

    public void setPkTotal(byte pkTotal) {
        this.pkTotal = pkTotal;
    }

    public byte getPkNumber() {
        return pkNumber;
    }

    public void setPkNumber(byte pkNumber) {
        this.pkNumber = pkNumber;
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

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        StringUtils.ensureLengthEquals(serviceId, 10);
        this.serviceId = serviceId;
    }

    public String getMsgSrc() {
        return msgSrc;
    }

    public void setMsgSrc(String msgSrc) {
        StringUtils.ensureLengthEquals(msgSrc, 6);
        this.msgSrc = msgSrc;
    }

    public String getSrcId() {
        return srcId;
    }

    public void setSrcId(String srcId) {
        this.srcId = srcId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public byte[] getMsgContentBytes() {
        return msgContentBytes;
    }

    public void setMsgContentBytes(byte[] msgContentBytes) {
        this.msgContentBytes = msgContentBytes;
    }

    public CmppSubmit() {

        cmppMessageHeader = new CmppMessageHeader();
        cmppMessageHeader.setCommandId(CmppCommandIdValues.CMPP_SUBMIT);
        cmppMessageHeader.setSequenceId(CmppUtils.getSequenceId());
    }


    @Override
    public void fromBytes(byte[] bytes) {

    }
}
