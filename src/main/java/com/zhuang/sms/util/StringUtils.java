package com.zhuang.sms.util;

/**
 * Created by zhuang on 1/27/2018.
 */
public class StringUtils {

    public static void ensureLengthEquals(String value , int length)
    {
        if(value.length()!=length)
        {
            throw new RuntimeException("'"+value+"'的长度不为"+length+"!");
        }
    }

    public static void ensureLengthEqualsOrLessThan(String value , int length)
    {
        if(value.length()>length)
        {
            throw new RuntimeException("'"+value+"'的长度大于"+length+"!");
        }
    }
}
