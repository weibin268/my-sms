package com.zhuang.sms.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by zhuang on 1/27/2018.
 */
public class ByteUtils {

    private static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String toHexString(byte[] bytes) {
        int length = bytes.length;
        char chars[] = new char[length * 2];
        int j = 0;
        for (int i = 0; i < length; i++) {
            byte byte0 = bytes[i];
            chars[j++] = hexDigits[byte0 >>> 4 & 0xf];
            chars[j++] = hexDigits[byte0 & 0xf];
        }

        return new String(chars);
    }

    public static byte[] toBytes(int value) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(4);

        byteBuffer.putInt(value);

        return byteBuffer.array();

    }

    public static byte[] toBytes(long value) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);

        byteBuffer.putLong(value);

        return byteBuffer.array();
    }

    public static byte[] toBytes(String value, int length) {

        byte[] result;

        if (value.length() == length) {
            result = value.getBytes();
        } else if (value.length() < length) {
            result = value.getBytes();
            result = ByteUtils.concat(result, new byte[length - result.length]);
        } else {
            result = value.substring(0, length).getBytes();
        }

        return result;

    }

    public static byte[] toBytes(String value, String charset) {

        byte[] result = null;

        try {
            result = value.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;

    }

    public static long toLong(byte[] bytes) {

        if (bytes.length == 4) {
            bytes = concat(new byte[4], bytes);
        }

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);

        byteBuffer.put(bytes);

        byteBuffer.flip();

        return byteBuffer.getLong();
    }

    public static int toInt(byte[] bytes) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(4);

        byteBuffer.put(bytes);

        byteBuffer.flip();

        return byteBuffer.getInt();
    }

    public static byte[] toUnsignedIntBytes(long value) {

        byte[] bytes = toBytes(value);
        return Arrays.copyOfRange(bytes, 4, 8);

    }

    public static String toStr(byte[] bytes) {
        char[] chars = new char[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            chars[i] = (char) bytes[i];
        }

        return new String(chars);
    }

    public static String toStr(byte[] bytes,String charset) {

        String result=null;

        try {
            result= new String(bytes,charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static byte[] concat(byte[]... bytesArray) {
        int totalLength = 0;

        for (byte[] bytes : bytesArray) {
            totalLength = totalLength + bytes.length;
        }

        byte[] result = new byte[totalLength];

        int destPost = 0;

        for (byte[] bytes : bytesArray) {

            System.arraycopy(bytes, 0, result, destPost, bytes.length);
            destPost = destPost + bytes.length;
        }

        return result;
    }


}
