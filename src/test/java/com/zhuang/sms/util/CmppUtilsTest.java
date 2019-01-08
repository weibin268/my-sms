package com.zhuang.sms.util;

import org.junit.Test;

/**
 * Created by zhuang on 1/27/2018.
 */
public class CmppUtilsTest {

    @Test
    public void getAuthenticatorSource() throws Exception {

        System.out.println(ByteUtils.toHexString(CmppUtils.getAuthenticatorSource("J01063","123","0128210101")));

    }

    @Test
    public void getTimestamp()
    {
        System.out.println(CmppUtils.getTimestamp());
    }
}