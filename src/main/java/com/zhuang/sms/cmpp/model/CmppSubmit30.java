package com.zhuang.sms.cmpp.model;

import com.zhuang.sms.util.ByteUtils;
import com.zhuang.sms.util.CmppUtils;

/**
 * Created by zhuang on 2/5/2018.
 */
public class CmppSubmit30 extends CmppSubmit{

    //length:32
    private String feeTerminalId="";

    private byte feeTerminalType;

    /***
     * 短信接收的号码，可设多个群发
     * length:32*DestUsr_tl
     */
    private String[] destTerminalId;

    private byte destTerminalType;

    //length:20
    private String linkId="";

    public String[] getDestTerminalId() {
        return destTerminalId;
    }

    public void setDestTerminalId(String[] destTerminalId) {
        this.destTerminalId = destTerminalId;
    }

    public String getFeeTerminalId() {
        return feeTerminalId;
    }

    public void setFeeTerminalId(String feeTerminalId) {
        this.feeTerminalId = feeTerminalId;
    }

    public byte getFeeTerminalType() {
        return feeTerminalType;
    }

    public void setFeeTerminalType(byte feeTerminalType) {
        this.feeTerminalType = feeTerminalType;
    }

    public byte getDestTerminalType() {
        return destTerminalType;
    }

    public void setDestTerminalType(byte destTerminalType) {
        this.destTerminalType = destTerminalType;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    @Override
    public byte[] toBytes() {

        byte[] msgIdBytes = ByteUtils.toBytes(msgId);
        byte[] pkTotalBytes = new byte[]{pkTotal};
        byte[] pkNumberBytes = new byte[]{pkNumber};
        byte[] registeredDeliveryBytes = new byte[]{registeredDelivery};
        byte[] msgLevelBytes = new byte[]{msgLevel};
        byte[] serviceIdBytes = ByteUtils.toBytes(serviceId, 10);
        byte[] feeUserTypeBytes = new byte[]{feeUserType};
        byte[] feeTerminalIdBytes = ByteUtils.toBytes(feeTerminalId, 32);
        byte[] feeTerminalTypeBytes=new byte[]{feeTerminalType};

        byte[] tpPIdBytes = new byte[]{tpPId};
        byte[] tpUdhiBytes = new byte[]{tpUdhi};
        byte[] msgFmtBytes = new byte[]{msgFmt};
        byte[] msgSrcBytes = ByteUtils.toBytes(msgSrc, 6);
        byte[] feeTypeBytes = feeType.getBytes();
        byte[] feeCodeBytes = ByteUtils.toBytes(feeCode, 6);
        byte[] valIdTimeBytes = ByteUtils.toBytes(valIdTime, 17);
        byte[] atTimeBytes = ByteUtils.toBytes(atTime, 17);
        byte[] srcIdBytes = ByteUtils.toBytes(srcId, 21);
        destUsrTl = (byte) destTerminalId.length;
        byte[] destUsrTlBytes = new byte[]{destUsrTl};
        byte[] destTerminalIdBytes = new byte[32 * destTerminalId.length];
        for (int i = 0; i < destUsrTl; i++) {
            System.arraycopy(ByteUtils.toBytes(destTerminalId[i], 32), 0, destTerminalIdBytes, 32 * i, 32);
        }
        byte[] destTerminalTypeBytes=new byte[]{destTerminalType};
        byte[] msgLengthBytes;
        if (msgContentBytes == null) {
            msgContentBytes = ByteUtils.toBytes(msgContent, CmppUtils.getCharsetByMsgFmt(msgFmt));
        }
        msgLength = (byte) msgContentBytes.length;
        msgLengthBytes = new byte[]{msgLength};

        byte[] linkIdBytes = ByteUtils.toBytes(linkId, 20);

        byte[] result = ByteUtils.concat(msgIdBytes, pkTotalBytes, pkNumberBytes, registeredDeliveryBytes, msgLevelBytes,
                serviceIdBytes, feeUserTypeBytes, feeTerminalIdBytes,feeTerminalTypeBytes, tpPIdBytes, tpUdhiBytes, msgFmtBytes, msgSrcBytes,
                feeTypeBytes, feeCodeBytes, valIdTimeBytes, atTimeBytes, srcIdBytes, destUsrTlBytes, destTerminalIdBytes,destTerminalTypeBytes,
                msgLengthBytes, msgContentBytes, linkIdBytes);

        cmppMessageHeader.setTotalLength(cmppMessageHeader.getLength() + result.length);
        result = ByteUtils.concat(cmppMessageHeader.toBytes(), result);

        return result;
    }

}
