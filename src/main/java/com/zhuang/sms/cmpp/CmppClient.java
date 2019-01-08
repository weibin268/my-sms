package com.zhuang.sms.cmpp;

import com.zhuang.sms.cmpp.model.CmppAccountInfo;
import com.zhuang.sms.cmpp.model.CmppSendWindow;
import com.zhuang.sms.cmpp.model.CmppSettings;

/**
 * Created by zhuang on 1/27/2018.
 */
public interface CmppClient {

    void connect();

    boolean isConnected();

    void setConnected(boolean connected);

    boolean isSocketConnected();

    byte[] receive();

    void send(byte[] bytes);

    void send(String msgContent, String[] phoneNoList, String spCodeSuffix);

    void sendActiveTest();

    void close();

    void setCmppSettings(CmppSettings cmppSettings);

    CmppSettings getCmppSettings();

    void setCmppAccountInfo(CmppAccountInfo cmppAccountInfo);

    CmppAccountInfo getCmppAccountInfo();

    CmppSendWindow getCmppSendWindow();

    void setCmppSendWindow(CmppSendWindow cmppSendWindow);

    byte getCmppVersion();

}
