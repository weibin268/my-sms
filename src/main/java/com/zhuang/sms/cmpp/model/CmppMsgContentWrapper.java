package com.zhuang.sms.cmpp.model;

import com.zhuang.sms.util.ByteUtils;

import java.util.Arrays;

/**
 * Created by zhuang on 2/3/2018.
 */
public class CmppMsgContentWrapper {

    /***
     * 短信内容
     */
    private String content;

    /***
     * 短信内容字节数组
     */
    private byte[] contentBytes;

    /***
     * 短信台头标题字数
     */
    private int titleWordCount;

    /***
     * 短信内容长度
     */
    private int totalLength;

    /***
     * 第一条短信的可用长度
     */
    private int firstAvailableLength;

    /***
     * 拆分后的短信数量
     */
    private int splitCount;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getContentBytes() {
        return contentBytes;
    }

    public void setContentBytes(byte[] contentBytes) {
        this.contentBytes = contentBytes;
    }

    public int getTitleWordCount() {
        return titleWordCount;
    }

    public void setTitleWordCount(int titleWordCount) {
        this.titleWordCount = titleWordCount;
    }

    public int getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }

    public int getFirstAvailableLength() {
        return firstAvailableLength;
    }

    public void setFirstAvailableLength(int firstAvailableLength) {
        this.firstAvailableLength = firstAvailableLength;
    }

    public int getSplitCount() {
        return splitCount;
    }

    public void setSplitCount(int splitCount) {
        this.splitCount = splitCount;
    }

    public CmppMsgContentWrapper(String content, String charset, int titleWordCount) {
        this.content = content;
        this.titleWordCount = titleWordCount;
        contentBytes = ByteUtils.toBytes(content, charset);
        totalLength = contentBytes.length;
        firstAvailableLength = 140 - (titleWordCount * 2); //短信长度=短信总长度-抬头数字量
        int firstTake = firstAvailableLength - 6;
        splitCount = 1 + (int) Math.ceil((double)(totalLength - firstTake) / 134);
    }

    public boolean isNeedSplit() {
        return totalLength > firstAvailableLength;
    }

    public CmppSubmit.MsgContext4Split[] split() {
        if (!isNeedSplit())
            throw new RuntimeException("no need to split!");

        CmppSubmit.MsgContext4Split[] result = new CmppSubmit.MsgContext4Split[getSplitCount()];

        for (int i = 0; i < getSplitCount(); i++) {

            result[i] = new CmppSubmit.MsgContext4Split();
            result[i].setPkTotal(getSplitCount());
            result[i].setPkNumber(i + 1);

            int firstLength = getFirstAvailableLength() - 6;
            int index = 0;
            int length = 0;

            if (i == 0) {
                //第一条
                index = 0;
                length = firstLength;

            } else if (i != getSplitCount() - 1) {
                index = firstLength + (i - 1) * 134;
                length = 134;

            } else {
                //最后一条
                index = firstLength + (i - 1) * 134;
                length = getContentBytes().length - index;
            }
            result[i].setMsgContentBytes(Arrays.copyOfRange(getContentBytes(), index, index + length));
        }

        return result;
    }

}
