package com.zhuang.sms.util;

import com.zhuang.sms.cmpp.enums.CmppCharsetValues;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by zhuang on 1/27/2018.
 */
public class ByteUtilsTest {
    @Test
    public void toHexString() throws Exception {

        System.out.println(ByteUtils.toHexString(ByteUtils.toBytes(2147483647)));

        System.out.println(ByteUtils.toHexString(ByteUtils.toBytes(2147483647L)));

        System.out.println(ByteUtils.toHexString(ByteUtils.toBytes(2147483648L)));

        System.out.println(ByteUtils.toHexString(ByteUtils.toUnsignedIntBytes(2147483648L)));


    }


    @Test
    public void toBytes()
    {

        System.out.println(ByteUtils.toHexString(ByteUtils.toBytes(39)));
        System.out.println(ByteUtils.toHexString(ByteUtils.toBytes(39L)));
        System.out.println(ByteUtils.toHexString(ByteUtils.toBytes("庄伟斌", CmppCharsetValues.UCS2)));
        System.out.println(ByteUtils.toHexString(ByteUtils.toBytes("庄伟斌", CmppCharsetValues.GB2312)));

    }

    @Test
    public void toLong()
    {
        byte[] bytes = ByteUtils.toBytes(3699L);

        bytes= Arrays.copyOfRange(bytes,4,8);

        System.out.println(ByteUtils.toLong(bytes));

    }
}