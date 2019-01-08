package com.zhuang.sms.cmpp.model;

import com.zhuang.sms.util.ByteUtils;

/**
 * Created by zhuang on 2/5/2018.
 */
public class CmppDeliverResp30 extends CmppDeliverResp {

    private int result = 0;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public byte[] toBytes() {

        byte[] msgIdBytes = ByteUtils.toBytes(msgId);
        byte[] resultBytes = ByteUtils.toBytes(result);

        byte[] result = ByteUtils.concat(msgIdBytes, resultBytes);
        cmppMessageHeader.setTotalLength(cmppMessageHeader.getLength() + result.length);
        result = ByteUtils.concat(cmppMessageHeader.toBytes(), result);

        return result;
    }
}
