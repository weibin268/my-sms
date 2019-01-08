package com.zhuang.sms.cmpp;

import com.zhuang.sms.SmsEngine;
import com.zhuang.sms.cmpp.handler.DefaultCmppReceiveHandler;
import com.zhuang.sms.cmpp.model.CmppAccountInfo;
import com.zhuang.sms.cmpp.model.CmppTerminate;
import com.zhuang.sms.cmpp.provider.DefaultCmppSendProvider;
import com.zhuang.sms.util.ThreadUtils;
import org.junit.Test;

/**
 * Created by zhuang on 1/30/2018.
 */
public class CmppSmsEngineTest {

    @Test
    public void start() throws Exception {

        CmppAccountInfo cmppAccountInfo = new CmppAccountInfo();
        cmppAccountInfo.setAccount("J01063");
        cmppAccountInfo.setPassword("123");
        cmppAccountInfo.setSpCode("106575581200");
        cmppAccountInfo.setServiceId("MGD0019900");
        cmppAccountInfo.setSpId("450918");
        CmppClient cmppClient = new Cmpp30Client("127.0.0.1", 7890, cmppAccountInfo);

        cmppClient.getCmppSettings().setActiveTestDelaySeconds(3);
        cmppClient.getCmppSettings().setSmsTitleWordCount(8);

        DefaultCmppSendProvider defaultCmppSendProvider = new DefaultCmppSendProvider();
        DefaultCmppReceiveHandler defaultCmppReceiveHandler = new DefaultCmppReceiveHandler();
        SmsEngine smsEngine = new CmppSmsEngine(cmppClient, defaultCmppSendProvider, defaultCmppReceiveHandler);

        smsEngine.start();
/*

        for (int i = 0; i < 100; i++) {
            ThreadUtils.sleep(1000);
            CmppSendItem sendItem = new CmppSendItem();
            sendItem.setPhoneNoList(new String[]{"88888888888"});
            //String content = "一二三四五6789A一二三四五6789A一二三四五6789A一二三四五6789A一二三四五6789A一二三四五6789A一二三四五6789A一二三四五6789A一二三四五6789A一二三四五6789A一二三四五6789A一二三四五6789A一二三四五6";
            String content = "早上好！" + i;
            sendItem.setMsgContent(content);

            defaultCmppSendProvider.getSendItemQueue().offer(sendItem);
        }
*/


/*
        ThreadUtils.sleep(10000);
        smsEngine.stop();
        System.out.println("stop");
*/

        ThreadUtils.stop();

    }


    @Test
    public void startMore() throws Exception {

        for (int i = 0; i < 10; i++) {

            CmppAccountInfo cmppAccountInfo = new CmppAccountInfo();
            cmppAccountInfo.setAccount("J01063");
            cmppAccountInfo.setPassword("123");
            cmppAccountInfo.setSpCode("106575581200");
            cmppAccountInfo.setServiceId("MGD0019900");
            cmppAccountInfo.setSpId("450918");
            CmppClient cmppClient = new Cmpp20Client("127.0.0.1", 7890, cmppAccountInfo);

            cmppClient.getCmppSettings().setActiveTestDelaySeconds(3);

            SmsEngine smsEngine = new CmppSmsEngine(cmppClient);

            smsEngine.start();

/*
            ThreadUtils.sleep(10000);
            smsEngine.stop();
            System.out.println("stop");
*/

        }


        ThreadUtils.stop();

    }

    @Test
    public void terminate() throws Exception {

        CmppAccountInfo cmppAccountInfo = new CmppAccountInfo();
        cmppAccountInfo.setAccount("J01063");
        cmppAccountInfo.setPassword("123");
        cmppAccountInfo.setSpCode("106575581200");
        cmppAccountInfo.setServiceId("MGD0019900");
        cmppAccountInfo.setSpId("450918");
        CmppClient cmppClient = new Cmpp20Client("127.0.0.1", 7890, cmppAccountInfo);

        //cmppClient.getCmppSettings().setActiveTestDelaySeconds(3);
        cmppClient.getCmppSettings().setSmsTitleWordCount(8);

        DefaultCmppSendProvider defaultCmppSendProvider = new DefaultCmppSendProvider();
        DefaultCmppReceiveHandler defaultCmppReceiveHandler = new DefaultCmppReceiveHandler();
        SmsEngine smsEngine = new CmppSmsEngine(cmppClient, defaultCmppSendProvider, defaultCmppReceiveHandler);

        smsEngine.start();

        ThreadUtils.sleep(5000);

        cmppClient.send(new CmppTerminate().toBytes());

        ThreadUtils.stop();

    }

    @Test
    public void stop() throws Exception {

        CmppAccountInfo cmppAccountInfo = new CmppAccountInfo();
        cmppAccountInfo.setAccount("J01063");
        cmppAccountInfo.setPassword("123");
        cmppAccountInfo.setSpCode("106575581200");
        cmppAccountInfo.setServiceId("MGD0019900");
        cmppAccountInfo.setSpId("450918");
        CmppClient cmppClient = new Cmpp20Client("127.0.0.1", 7890, cmppAccountInfo);

        cmppClient.getCmppSettings().setActiveTestDelaySeconds(3);
        cmppClient.getCmppSettings().setSmsTitleWordCount(8);

        DefaultCmppSendProvider defaultCmppSendProvider = new DefaultCmppSendProvider();
        DefaultCmppReceiveHandler defaultCmppReceiveHandler = new DefaultCmppReceiveHandler();
        SmsEngine smsEngine = new CmppSmsEngine(cmppClient, defaultCmppSendProvider, defaultCmppReceiveHandler);

        smsEngine.start();

        ThreadUtils.sleep(5000);

        smsEngine.stop();

        ThreadUtils.sleep(5000);

        smsEngine.start();

        ThreadUtils.stop();

    }


}