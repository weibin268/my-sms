package com.zhuang.sms.cmpp;

import com.zhuang.sms.cmpp.model.CmppAccountInfo;
import com.zhuang.sms.util.ThreadUtils;
import org.junit.Test;

/**
 * Created by zhuang on 2/5/2018.
 */
public class Cmpp30ClientTest {


    @Test
    public void send() throws Exception {

        CmppAccountInfo cmppAccountInfo = new CmppAccountInfo();
        cmppAccountInfo.setAccount("J01063");
        cmppAccountInfo.setPassword("123");
        cmppAccountInfo.setSpCode("106575581200");
        cmppAccountInfo.setServiceId("MGD0019900");
        cmppAccountInfo.setSpId("450918");
        CmppClient cmppClient = new Cmpp30Client("127.0.0.1", 7890, cmppAccountInfo);

        cmppClient.connect();

        cmppClient.send("早上好！",new String[]{"11111111111"},"");


        ThreadUtils.sleep(10000);
    }

}