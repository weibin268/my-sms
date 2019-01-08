package com.zhuang.sms.cmpp;

import com.zhuang.sms.cmpp.enums.CmppVersionValues;
import com.zhuang.sms.cmpp.model.*;
import com.zhuang.sms.util.ThreadUtils;

/**
 * Created by zhuang on 1/27/2018.
 */
public class Cmpp20Client extends BaseCmppClient {

    public Cmpp20Client(String host, int port, CmppAccountInfo cmppAccountInfo) {
        super(CmppVersionValues.CMPP_VERSION_20,host,port,cmppAccountInfo);
    }

    @Override
    protected void send(byte[] msgContentBytes, byte pkTotal, byte pkNumber, byte tpUdhi, byte msgFmt, String[] destTerminalId, String spCodeSuffix) {

        CmppSubmit20 cmppSubmit20 = new CmppSubmit20();

        while (!cmppSendWindow.add(cmppSubmit20.getCmppMessageHeader().getSequenceId())) {
            ThreadUtils.sleep(100);
        }

        cmppSubmit20.setDestTerminalId(destTerminalId);
        cmppSubmit20.setMsgContentBytes(msgContentBytes);
        cmppSubmit20.setMsgSrc(cmppAccountInfo.getSpId());
        cmppSubmit20.setServiceId(cmppAccountInfo.getServiceId());
        cmppSubmit20.setSrcId(cmppAccountInfo.getSpCode() + spCodeSuffix);
        cmppSubmit20.setMsgFmt(msgFmt);

        cmppSubmit20.setPkTotal(pkTotal);
        cmppSubmit20.setPkNumber(pkNumber);
        cmppSubmit20.setTpUdhi(tpUdhi);

        send(cmppSubmit20.toBytes());
    }
}
