package com.zhuang.sms.cmpp.model;

import com.zhuang.sms.util.ByteUtils;

import java.util.Arrays;

/**
 * Created by zhuang on 1/27/2018.
 */
public class CmppMessageHeader implements CmppMessage {

    public static final int LENGTH = 12;

    private long totalLength;

    private long commandId;

    private long sequenceId;

    public CmppMessageHeader() {

    }

    public CmppMessageHeader(long totalLength, long commandId, long sequenceId) {
        this.totalLength = totalLength;
        this.commandId = commandId;
        this.sequenceId = sequenceId;
    }

    public long getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(long totalLength) {
        this.totalLength = totalLength;
    }

    public long getCommandId() {
        return commandId;
    }

    public void setCommandId(long commandId) {
        this.commandId = commandId;
    }

    public long getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(long sequenceId) {
        this.sequenceId = sequenceId;
    }


    public int getLength() {
        return LENGTH;
    }

    @Override
    public byte[] toBytes() {

        byte[] totalLengthBytes = ByteUtils.toUnsignedIntBytes(totalLength);
        byte[] commandIdBytes = ByteUtils.toUnsignedIntBytes(commandId);
        byte[] sequenceIdBytes = ByteUtils.toUnsignedIntBytes(sequenceId);

        return ByteUtils.concat(totalLengthBytes, commandIdBytes, sequenceIdBytes);

    }

    @Override
    public void fromBytes(byte[] bytes) {

        totalLength = ByteUtils.toLong(Arrays.copyOfRange(bytes, 0, 4));
        commandId = ByteUtils.toLong(Arrays.copyOfRange(bytes, 4, 8));
        sequenceId = ByteUtils.toLong(Arrays.copyOfRange(bytes, 8, 12));

    }

    @Override
    public String toString() {
        return "CmppMessageHeader{" +
                "totalLength=" + totalLength +
                ", commandId=" + commandId +
                ", sequenceId=" + sequenceId +
                '}';
    }
}
