package com.zhuang.sms.cmpp.provider;

import java.util.List;

/**
 * Created by zhuang on 2/3/2018.
 */
public interface CmppSendProvider {

    List<CmppSendItem> getSendItems(CmppSendContext context);

}
