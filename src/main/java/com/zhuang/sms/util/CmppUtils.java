package com.zhuang.sms.util;

import com.zhuang.sms.cmpp.enums.CmppCharsetValues;
import com.zhuang.sms.cmpp.enums.CmppMsgFmtValues;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhuang on 1/27/2018.
 */
public class CmppUtils {

    private static long sequenceId = 0;

    public static byte[] getAuthenticatorSource(String account, String password) {
        return getAuthenticatorSource(account, password, getTimestamp());
    }

    public static byte[] getAuthenticatorSource(String account, String password, String timestamp) {

        StringUtils.ensureLengthEquals(account, 6);

        byte[] result = new byte[6 + 9 + password.length() + 10];

        int index = 0;

        for (char ch : account.toCharArray()) {
            result[index++] = (byte) ch;
        }

        index += 9;

        for (char ch : password.toCharArray()) {
            result[index++] = (byte) ch;
        }


        for (char ch : timestamp.toCharArray()) {
            result[index++] = (byte) ch;
        }

        return EncryptionUtils.encryptByMD5(result);
    }

    public static synchronized long getSequenceId() {
        return ++sequenceId;
    }

    public static String getTimestamp() {
        return new SimpleDateFormat("MMddHHmmss").format(new Date());
    }

    public static String getCharsetByMsgFmt(byte msgFmt) {
        if (msgFmt == CmppMsgFmtValues.UCS2) {
            return CmppCharsetValues.UCS2;
        } else if (msgFmt == CmppMsgFmtValues.GB_CHINESE) {
            return CmppCharsetValues.GB2312;
        } else if (msgFmt == CmppMsgFmtValues.ASCII) {
            return CmppCharsetValues.ASCII;
        } else {
            return null;
        }
    }
}
