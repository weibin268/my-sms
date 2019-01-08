package com.zhuang.sms.cmpp;

import com.zhuang.sms.cmpp.enums.CmppVersionValues;
import com.zhuang.sms.cmpp.model.CmppAccountInfo;
import com.zhuang.sms.cmpp.model.CmppSubmit30;
import com.zhuang.sms.util.ThreadUtils;

/**
 * Created by zhuang on 1/27/2018.
 */
public class Cmpp30Client extends BaseCmppClient {

    public Cmpp30Client(String host, int port, CmppAccountInfo cmppAccountInfo) {
        super(CmppVersionValues.CMPP_VERSION_30, host, port, cmppAccountInfo);
    }

    @Override
    protected void send(byte[] msgContentBytes, byte pkTotal, byte pkNumber, byte tpUdhi, byte msgFmt, String[] destTerminalId, String spCodeSuffix) {

        CmppSubmit30 cmppSubmit30 = new CmppSubmit30();

        while (!cmppSendWindow.add(cmppSubmit30.getCmppMessageHeader().getSequenceId())) {
            ThreadUtils.sleep(100);
        }

        cmppSubmit30.setDestTerminalId(destTerminalId);
        cmppSubmit30.setMsgContentBytes(msgContentBytes);
        cmppSubmit30.setMsgSrc(cmppAccountInfo.getSpId());
        cmppSubmit30.setServiceId(cmppAccountInfo.getServiceId());
        cmppSubmit30.setSrcId(cmppAccountInfo.getSpCode() + spCodeSuffix);
        cmppSubmit30.setMsgFmt(msgFmt);

        cmppSubmit30.setPkTotal(pkTotal);
        cmppSubmit30.setPkNumber(pkNumber);
        cmppSubmit30.setTpUdhi(tpUdhi);

        cmppSubmit30.setFeeTerminalType((byte)0);
        cmppSubmit30.setDestTerminalType((byte)0);

        send(cmppSubmit30.toBytes());

    }

}
