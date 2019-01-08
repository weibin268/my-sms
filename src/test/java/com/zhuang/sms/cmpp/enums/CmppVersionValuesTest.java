package com.zhuang.sms.cmpp.enums;

import com.zhuang.sms.util.ByteUtils;
import org.junit.Test;

/**
 * Created by zhuang on 2/5/2018.
 */
public class CmppVersionValuesTest {

    @Test
    public void test()
    {
        System.out.println(CmppVersionValues.CMPP_VERSION_20);
        System.out.println(CmppVersionValues.CMPP_VERSION_30);

        System.out.println(0x21);

        System.out.println(ByteUtils.toHexString(new byte[]{ 108}));
    }

}