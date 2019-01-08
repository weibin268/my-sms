package com.zhuang.sms.cmpp;

import com.zhuang.sms.cmpp.handler.BaseCmppReceiveHandler;
import com.zhuang.sms.cmpp.handler.CmppReceiveContext;
import com.zhuang.sms.cmpp.handler.DefaultCmppReceiveHandler;
import com.zhuang.sms.cmpp.model.CmppAccountInfo;
import com.zhuang.sms.cmpp.model.CmppTerminate;
import com.zhuang.sms.util.ThreadUtils;
import org.junit.Test;

/**
 * Created by zhuang on 1/27/2018.
 */
public class Cmpp20ClientTest {

    @Test
    public void connect() throws Exception {

        CmppAccountInfo cmppAccountInfo = new CmppAccountInfo();
        cmppAccountInfo.setAccount("J01063");
        cmppAccountInfo.setPassword("123");
        Cmpp20Client cmpp20Client = new Cmpp20Client("127.0.0.1", 7890, cmppAccountInfo);

        cmpp20Client.connect();

        byte[] bytes = cmpp20Client.receive();

        BaseCmppReceiveHandler baseCmppReceiveHandler =new DefaultCmppReceiveHandler();
        CmppReceiveContext cmppReceiveContext=new CmppReceiveContext();
        cmppReceiveContext.setBytes(bytes);
        cmppReceiveContext.setCmppClient(cmpp20Client);
        baseCmppReceiveHandler.handle(cmppReceiveContext);

    }


    @Test
    public void close() throws Exception {

        CmppAccountInfo cmppAccountInfo = new CmppAccountInfo();
        cmppAccountInfo.setAccount("J01063");
        cmppAccountInfo.setPassword("123");
        cmppAccountInfo.setSpCode("106575581200");
        cmppAccountInfo.setServiceId("MGD0019900");
        cmppAccountInfo.setSpId("450918");
        CmppClient cmppClient = new Cmpp20Client("127.0.0.1", 7890, cmppAccountInfo);

        cmppClient.connect();
        cmppClient.close();

        ThreadUtils.sleep(10000);
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

        cmppClient.connect();

        cmppClient.send(new CmppTerminate().toBytes());

        ThreadUtils.sleep(10000);

    }


    @Test
    public void send() throws Exception {

        CmppAccountInfo cmppAccountInfo = new CmppAccountInfo();
        cmppAccountInfo.setAccount("J01063");
        cmppAccountInfo.setPassword("123");
        cmppAccountInfo.setSpCode("106575581200");
        cmppAccountInfo.setServiceId("MGD0019900");
        cmppAccountInfo.setSpId("450918");
        CmppClient cmppClient = new Cmpp20Client("127.0.0.1", 7890, cmppAccountInfo);

        cmppClient.connect();


        cmppClient.send("早上好！",new String[]{"11111111111"},"");

/*

        CmppQuery cmppQuery=new CmppQuery();
        cmppQuery.setTime("20180202");
        cmppQuery.setQueryType((byte) 0);
        cmppQuery.setQueryCode("");
        cmppClient.send(cmppQuery.toBytes());
*/


        ThreadUtils.sleep(10000);
    }



}