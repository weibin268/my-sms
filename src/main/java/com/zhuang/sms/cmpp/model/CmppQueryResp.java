package com.zhuang.sms.cmpp.model;

import com.zhuang.sms.util.ByteUtils;

import java.util.Arrays;

/**
 * Created by zhuang on 2/5/2018.
 */
public class CmppQueryResp implements CmppMessage{

    private CmppMessageHeader cmppMessageHeader;

    /***
     * 时间YYYYMMDD(精确至日)。
     * length:8
     */
    private String time;

    /***
     * 查询类别：
     * 0：总数查询；
     * 1：按业务类型查询
     */
    private byte queryType;

    //length:10
    private String queryCode;

    private int mtTlMsg;

    private int mtTlusr;

    private int mtScs;

    private int mtWt;

    private int mtFl;

    private int moScs;

    private int moWt;

    private int moFl;


    public CmppMessageHeader getCmppMessageHeader() {
        return cmppMessageHeader;
    }

    public void setCmppMessageHeader(CmppMessageHeader cmppMessageHeader) {
        this.cmppMessageHeader = cmppMessageHeader;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public byte getQueryType() {
        return queryType;
    }

    public void setQueryType(byte queryType) {
        this.queryType = queryType;
    }

    public String getQueryCode() {
        return queryCode;
    }

    public void setQueryCode(String queryCode) {
        this.queryCode = queryCode;
    }

    public int getMtTlMsg() {
        return mtTlMsg;
    }

    public void setMtTlMsg(int mtTlMsg) {
        this.mtTlMsg = mtTlMsg;
    }

    public int getMtTlusr() {
        return mtTlusr;
    }

    public void setMtTlusr(int mtTlusr) {
        this.mtTlusr = mtTlusr;
    }

    public int getMtScs() {
        return mtScs;
    }

    public void setMtScs(int mtScs) {
        this.mtScs = mtScs;
    }

    public int getMtWt() {
        return mtWt;
    }

    public void setMtWt(int mtWt) {
        this.mtWt = mtWt;
    }

    public int getMtFl() {
        return mtFl;
    }

    public void setMtFl(int mtFl) {
        this.mtFl = mtFl;
    }

    public int getMoScs() {
        return moScs;
    }

    public void setMoScs(int moScs) {
        this.moScs = moScs;
    }

    public int getMoWt() {
        return moWt;
    }

    public void setMoWt(int moWt) {
        this.moWt = moWt;
    }

    public int getMoFl() {
        return moFl;
    }

    public void setMoFl(int moFl) {
        this.moFl = moFl;
    }

    public CmppQueryResp()
    {

    }

    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    public void fromBytes(byte[] bytes) {

        int index = 0;
        int length = 8;
        time = ByteUtils.toStr(Arrays.copyOfRange(bytes, index, index + length));
        index = index + length;
        length = 1;
        queryType= bytes[index];
        index = index + length;
        length = 10;
        queryCode = ByteUtils.toStr(Arrays.copyOfRange(bytes, index, index + length));
        index = index + length;
        length = 4;
        mtTlMsg = ByteUtils.toInt(Arrays.copyOfRange(bytes, index, index + length));
        index = index + length;
        length = 4;
        mtTlusr = ByteUtils.toInt(Arrays.copyOfRange(bytes, index, index + length));
        index = index + length;
        length = 4;
        mtScs = ByteUtils.toInt(Arrays.copyOfRange(bytes, index, index + length));
        index = index + length;
        length = 4;
        mtWt = ByteUtils.toInt(Arrays.copyOfRange(bytes, index, index + length));
        index = index + length;
        length = 4;
        mtFl = ByteUtils.toInt(Arrays.copyOfRange(bytes, index, index + length));
        index = index + length;
        length = 4;
        moScs = ByteUtils.toInt(Arrays.copyOfRange(bytes, index, index + length));
        index = index + length;
        length = 4;
        moWt = ByteUtils.toInt(Arrays.copyOfRange(bytes, index, index + length));
        index = index + length;
        length = 4;
        moFl = ByteUtils.toInt(Arrays.copyOfRange(bytes, index, index + length));

    }

    @Override
    public String toString() {
        return "CmppQueryResp{" +
                "cmppMessageHeader=" + cmppMessageHeader +
                ", time='" + time + '\'' +
                ", queryType=" + queryType +
                ", queryCode='" + queryCode + '\'' +
                ", mtTlMsg=" + mtTlMsg +
                ", mtTlusr=" + mtTlusr +
                ", mtScs=" + mtScs +
                ", mtWt=" + mtWt +
                ", mtFl=" + mtFl +
                ", moScs=" + moScs +
                ", moWt=" + moWt +
                ", moFl=" + moFl +
                '}';
    }
}
