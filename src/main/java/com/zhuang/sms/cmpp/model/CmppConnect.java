package com.zhuang.sms.cmpp.model;

import com.zhuang.sms.cmpp.enums.CmppCommandIdValues;
import com.zhuang.sms.util.ByteUtils;
import com.zhuang.sms.util.CmppUtils;
import com.zhuang.sms.util.StringUtils;

/**
 * Created by zhuang on 1/27/2018.
 */
public class CmppConnect implements CmppMessage {

    private CmppMessageHeader cmppMessageHeader;

    private String sourceAddr;

    private byte[] authenticatorSource;

    private byte version;

    private long timestamp;

    public CmppMessageHeader getCmppMessageHeader() {
        return cmppMessageHeader;
    }

    public void setCmppMessageHeader(CmppMessageHeader cmppMessageHeader) {
        this.cmppMessageHeader = cmppMessageHeader;
    }

    public String getSourceAddr() {
        return sourceAddr;
    }

    public void setSourceAddr(String sourceAddr) {

        StringUtils.ensureLengthEquals(sourceAddr, 6);

        this.sourceAddr = sourceAddr;
    }

    public byte[] getAuthenticatorSource() {
        return authenticatorSource;
    }

    public void setAuthenticatorSource(byte[] authenticatorSource) {

        this.authenticatorSource = authenticatorSource;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public CmppConnect() {

        cmppMessageHeader = new CmppMessageHeader();
        cmppMessageHeader.setCommandId(CmppCommandIdValues.CMPP_CONNECT);
        cmppMessageHeader.setSequenceId(CmppUtils.getSequenceId());
        timestamp=Long.parseLong(CmppUtils.getTimestamp());

    }

    @Override
    public byte[] toBytes() {

        byte[] sourceAddrBytes = sourceAddr.getBytes();
        byte[] versionBytes = new byte[]{version};
        byte[] timestampBytes = ByteUtils.toUnsignedIntBytes(timestamp);

        byte[] result = ByteUtils.concat(sourceAddrBytes, authenticatorSource, versionBytes, timestampBytes);
        cmppMessageHeader.setTotalLength(cmppMessageHeader.getLength() + result.length);
        result = ByteUtils.concat(cmppMessageHeader.toBytes(), result);

        return result;
    }

    @Override
    public void fromBytes(byte[] bytes) {

    }

}
