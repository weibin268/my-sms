package com.zhuang.sms.cmpp.model;

import com.zhuang.sms.util.ByteUtils;
import com.zhuang.sms.util.CmppUtils;

/**
 * Created by zhuang on 2/5/2018.
 */
public class CmppSubmit20 extends CmppSubmit{


    //length:20
    private String linkID;

    //length:21
    private String feeTerminalId = "";

    /***
     * 短信接收的号码，可设多个群发
     * length:21*DestUsr_tl
     */
    private String[] destTerminalId;

    private String reserve = "";

    public String[] getDestTerminalId() {
        return destTerminalId;
    }

    public void setDestTerminalId(String[] destTerminalId) {
        this.destTerminalId = destTerminalId;
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
        byte[] feeTerminalIdBytes = ByteUtils.toBytes(feeTerminalId, 21);
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
        byte[] destTerminalIdBytes = new byte[21 * destTerminalId.length];
        for (int i = 0; i < destUsrTl; i++) {
            System.arraycopy(ByteUtils.toBytes(destTerminalId[i], 21), 0, destTerminalIdBytes, 21 * i, 21);
        }
        byte[] msgLengthBytes;
        if (msgContentBytes == null) {
            msgContentBytes = ByteUtils.toBytes(msgContent, CmppUtils.getCharsetByMsgFmt(msgFmt));
        }
        msgLength = (byte) msgContentBytes.length;
        msgLengthBytes = new byte[]{msgLength};

        byte[] reserveBytes = ByteUtils.toBytes(reserve, 8);

        byte[] result = ByteUtils.concat(msgIdBytes, pkTotalBytes, pkNumberBytes, registeredDeliveryBytes, msgLevelBytes,
                serviceIdBytes, feeUserTypeBytes, feeTerminalIdBytes, tpPIdBytes, tpUdhiBytes, msgFmtBytes, msgSrcBytes,
                feeTypeBytes, feeCodeBytes, valIdTimeBytes, atTimeBytes, srcIdBytes, destUsrTlBytes, destTerminalIdBytes,
                msgLengthBytes, msgContentBytes, reserveBytes);

        cmppMessageHeader.setTotalLength(cmppMessageHeader.getLength() + result.length);
        result = ByteUtils.concat(cmppMessageHeader.toBytes(), result);

        return result;
    }

}
