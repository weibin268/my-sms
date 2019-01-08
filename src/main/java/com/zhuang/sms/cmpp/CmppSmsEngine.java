package com.zhuang.sms.cmpp;

import com.zhuang.sms.SmsEngine;
import com.zhuang.sms.cmpp.handler.CmppReceiveHandler;
import com.zhuang.sms.cmpp.handler.CmppReceiveContext;
import com.zhuang.sms.cmpp.handler.DefaultCmppReceiveHandler;
import com.zhuang.sms.cmpp.provider.CmppSendContext;
import com.zhuang.sms.cmpp.provider.CmppSendItem;
import com.zhuang.sms.cmpp.provider.CmppSendProvider;
import com.zhuang.sms.cmpp.provider.DefaultCmppSendProvider;
import com.zhuang.sms.util.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by zhuang on 1/30/2018.
 */
public class CmppSmsEngine implements SmsEngine {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private boolean running;

    private Thread connectThread;

    private Thread receiveThread;

    private Thread sendThread;

    private Thread activeTestThread;

    private CmppClient cmppClient;

    private CmppReceiveHandler cmppReceiveHandler;

    private CmppSendProvider cmppSendProvider;

    public CmppReceiveHandler getCmppReceiveHandler() {
        return cmppReceiveHandler;
    }

    public void setCmppReceiveHandler(CmppReceiveHandler cmppReceiveHandler) {
        this.cmppReceiveHandler = cmppReceiveHandler;
    }

    public CmppSendProvider getCmppSendProvider() {
        return cmppSendProvider;
    }

    public void setCmppSendProvider(CmppSendProvider cmppSendProvider) {
        this.cmppSendProvider = cmppSendProvider;
    }

    public CmppSmsEngine(CmppClient cmppClient)
    {
        this(cmppClient,new DefaultCmppSendProvider(),new DefaultCmppReceiveHandler());
    }

    public CmppSmsEngine(CmppClient cmppClient, CmppSendProvider cmppSendProvider, CmppReceiveHandler cmppReceiveHandler) {
        this.cmppClient = cmppClient;
        this.cmppSendProvider = cmppSendProvider;
        this.cmppReceiveHandler = cmppReceiveHandler;
    }

    @Override
    public void start() {

        running = true;

        connectThread = startConnectThread();

        ThreadUtils.sleep(100);

        receiveThread = startReceiveThread();

        sendThread = startSendThread();

        activeTestThread = startActiveTestThread();

    }

    @Override
    public void stop() {

        running = false;

        try {

            if (connectThread != null) connectThread.join();
            if (sendThread != null) sendThread.join();
            if (receiveThread != null) receiveThread.join();
            if (activeTestThread != null) activeTestThread.join();

            cmppClient.close();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Thread startConnectThread() {
        Thread thread = new Thread(() -> {
            while (running) {
                try {
                    if (!cmppClient.isConnected()) {
                        cmppClient.close();
                        cmppClient.connect();
                    }

                    ThreadUtils.sleep(5000);
                } catch (Exception e) {
                    logger.error("ConnectThread error", e);
                    ThreadUtils.sleep(5000);
                }

            }
        });
        thread.start();
        return thread;
    }

    private Thread startReceiveThread() {

        Thread thread = new Thread(() -> {
            while (running) {
                try {
                    if (!cmppClient.isSocketConnected()) {
                        ThreadUtils.sleep(5000);
                        continue;
                    }

                    byte[] receivedBytes = cmppClient.receive();
                    CmppReceiveContext cmppReceiveContext = new CmppReceiveContext();
                    cmppReceiveContext.setBytes(receivedBytes);
                    cmppReceiveContext.setCmppClient(cmppClient);

                    cmppReceiveHandler.handle(cmppReceiveContext);
                } catch (Exception e) {
                    logger.error("ReceiveThread error", e);
                    ThreadUtils.sleep(5000);
                }
            }
        });
        thread.start();
        return thread;
    }

    private Thread startSendThread() {

        Thread thread = new Thread(() -> {
            while (running) {
                try {

                    if (!cmppClient.isConnected()) {
                        ThreadUtils.sleep(5000);
                        continue;
                    }

                    CmppSendContext cmppSendContext = new CmppSendContext();
                    cmppSendContext.setCmppAccountInfo(cmppClient.getCmppAccountInfo());
                    List<CmppSendItem> sendItems = cmppSendProvider.getSendItems(cmppSendContext);

                    if (sendItems.size() == 0) {
                        ThreadUtils.sleep(1000);
                        continue;
                    }

                    for (CmppSendItem item : sendItems) {
                        cmppClient.send(item.getMsgContent(), item.getPhoneNoList(), item.getSpCodeSuffix());
                    }

                } catch (Exception e) {
                    logger.error("SendThread error", e);
                    ThreadUtils.sleep(5000);
                }
            }
        });
        thread.start();
        return thread;
    }

    private Thread startActiveTestThread() {

        Thread thread = new Thread(() -> {
            while (running) {
                try {
                    ThreadUtils.sleep(cmppClient.getCmppSettings().getActiveTestDelaySeconds() * 1000);
                    if (cmppClient.isConnected()) {
                        cmppClient.sendActiveTest();
                    }

                } catch (Exception e) {

                    logger.error("ActiveTestThread error", e);
                    ThreadUtils.sleep(5000);
                }
            }
        });
        thread.start();
        return thread;
    }

}
