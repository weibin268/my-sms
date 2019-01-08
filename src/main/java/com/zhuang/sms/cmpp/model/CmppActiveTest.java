package com.zhuang.sms.cmpp.model;

import com.zhuang.sms.cmpp.enums.CmppCommandIdValues;
import com.zhuang.sms.util.CmppUtils;

/**
 * Created by zhuang on 2/2/2018.
 */
public class CmppActiveTest implements CmppMessage {

    private CmppMessageHeader cmppMessageHeader;

    public CmppActiveTest() {

        cmppMessageHeader = new CmppMessageHeader();
        cmppMessageHeader.setCommandId(CmppCommandIdValues.CMPP_ACTIVE_TEST);
        cmppMessageHeader.setSequenceId(CmppUtils.getSequenceId());
        cmppMessageHeader.setTotalLength(CmppMessageHeader.LENGTH);

    }

    @Override
    public byte[] toBytes() {
        return cmppMessageHeader.toBytes();
    }

    @Override
    public void fromBytes(byte[] bytes) {

    }
}
