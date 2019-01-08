package com.zhuang.sms.cmpp.model;

import com.zhuang.sms.cmpp.enums.CmppCommandIdValues;
import com.zhuang.sms.util.CmppUtils;

/**
 * Created by zhuang on 2/3/2018.
 */
public abstract class CmppDeliverResp implements CmppMessage{

    protected CmppMessageHeader cmppMessageHeader;

    protected long msgId = 0;


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


    public CmppDeliverResp()
    {
        cmppMessageHeader = new CmppMessageHeader();
        cmppMessageHeader.setCommandId(CmppCommandIdValues.CMPP_DELIVER_RESP);
        cmppMessageHeader.setSequenceId(CmppUtils.getSequenceId());
    }


    @Override
    public void fromBytes(byte[] bytes) {

    }

}
