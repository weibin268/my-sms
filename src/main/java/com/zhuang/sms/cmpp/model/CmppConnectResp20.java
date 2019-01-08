package com.zhuang.sms.cmpp.model;

import com.zhuang.sms.util.ByteUtils;

import java.util.Arrays;

/**
 * Created by zhuang on 2/5/2018.
 */
public class CmppConnectResp20 extends CmppConnectResp {

    @Override
    public void fromBytes(byte[] bytes) {

        status= bytes[0];

        authenticatorISMG= ByteUtils.toHexString(Arrays.copyOfRange(bytes,1,17));

        version=bytes[17];

    }

    @Override
    public String toString() {
        return "CmppConnectResp20{" +
                "status=" + status +
                ", cmppMessageHeader=" + cmppMessageHeader +
                ", authenticatorISMG='" + authenticatorISMG + '\'' +
                ", version=" + version +
                '}';
    }
}
