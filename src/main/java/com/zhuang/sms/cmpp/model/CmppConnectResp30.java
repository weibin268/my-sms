package com.zhuang.sms.cmpp.model;

import com.zhuang.sms.util.ByteUtils;

import java.util.Arrays;

/**
 * Created by zhuang on 2/5/2018.
 */
public class CmppConnectResp30 extends CmppConnectResp {


    @Override
    public void fromBytes(byte[] bytes) {

        int index = 0;
        int length = 4;
        status = ByteUtils.toInt(Arrays.copyOfRange(bytes, index, index + length));
        index = index + length;
        length = 16;
        authenticatorISMG= ByteUtils.toHexString(Arrays.copyOfRange(bytes,index,index+length));
        index = index + length;
        length = 1;
        version=bytes[index];

    }

    @Override
    public String toString() {
        return "CmppConnectResp30{" +
                "status=" + status +
                ", cmppMessageHeader=" + cmppMessageHeader +
                ", authenticatorISMG='" + authenticatorISMG + '\'' +
                ", version=" + version +
                '}';
    }
}
