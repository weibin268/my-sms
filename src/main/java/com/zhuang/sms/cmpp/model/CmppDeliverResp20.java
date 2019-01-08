package com.zhuang.sms.cmpp.model;

import com.zhuang.sms.util.ByteUtils;

/**
 * Created by zhuang on 2/5/2018.
 */
public class CmppDeliverResp20 extends CmppDeliverResp{

    private byte result=0;

    public byte getResult() {
        return result;
    }

    public void setResult(byte result) {
        this.result = result;
    }

    @Override
    public byte[] toBytes() {

        byte[]msgIdBytes= ByteUtils.toBytes(msgId);
        byte[]resultBytes=new byte[]{this.result};

        byte[] result = ByteUtils.concat(msgIdBytes, resultBytes);
        cmppMessageHeader.setTotalLength(cmppMessageHeader.getLength() + result.length);
        result = ByteUtils.concat(cmppMessageHeader.toBytes(), result);

        return result;
    }

}
