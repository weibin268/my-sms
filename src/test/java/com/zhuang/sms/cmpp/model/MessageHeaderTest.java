package com.zhuang.sms.cmpp.model;

import com.zhuang.sms.util.ByteUtils;
import org.junit.Test;

/**
 * Created by zhuang on 1/27/2018.
 */
public class MessageHeaderTest {

    @Test
    public void toBytes() throws Exception {


        CmppMessageHeader cmppMessageHeader =new CmppMessageHeader(10,1,1);


        System.out.println(ByteUtils.toHexString(cmppMessageHeader.toBytes()));


    }

}