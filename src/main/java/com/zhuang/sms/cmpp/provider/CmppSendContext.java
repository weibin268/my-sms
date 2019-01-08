package com.zhuang.sms.cmpp.provider;

import com.zhuang.sms.cmpp.model.CmppAccountInfo;

/**
 * Created by zhuang on 2/3/2018.
 */
public class CmppSendContext {

    private CmppAccountInfo cmppAccountInfo;

    public CmppAccountInfo getCmppAccountInfo() {
        return cmppAccountInfo;
    }

    public void setCmppAccountInfo(CmppAccountInfo cmppAccountInfo) {
        this.cmppAccountInfo = cmppAccountInfo;
    }
}
