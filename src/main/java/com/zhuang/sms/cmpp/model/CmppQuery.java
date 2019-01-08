package com.zhuang.sms.cmpp.model;

import com.zhuang.sms.cmpp.enums.CmppCommandIdValues;
import com.zhuang.sms.util.ByteUtils;
import com.zhuang.sms.util.CmppUtils;

/**
 * Created by zhuang on 2/5/2018.
 */
public class CmppQuery implements CmppMessage{

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

    //length:8
    private String reserve = "";

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

    public CmppQuery()
    {
        cmppMessageHeader = new CmppMessageHeader();
        cmppMessageHeader.setCommandId(CmppCommandIdValues.CMPP_QUERY);
        cmppMessageHeader.setSequenceId(CmppUtils.getSequenceId());
    }

    @Override
    public byte[] toBytes() {

        byte[] timeBytes = ByteUtils.toBytes(time,8);
        byte[] queryTypeBytes = new byte[]{queryType};
        byte[] queryCodeBytes = ByteUtils.toBytes(queryCode,10);
        byte[] reserveBytes = ByteUtils.toBytes(reserve, 8);

        byte[] result = ByteUtils.concat(timeBytes, queryTypeBytes, queryCodeBytes, reserveBytes);
        cmppMessageHeader.setTotalLength(cmppMessageHeader.getLength() + result.length);
        result = ByteUtils.concat(cmppMessageHeader.toBytes(), result);

        return result;
    }

    @Override
    public void fromBytes(byte[] bytes) {

    }
}
