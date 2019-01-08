package com.zhuang.sms.cmpp.model;

import com.zhuang.sms.cmpp.enums.CmppCharsetValues;
import org.junit.Test;

/**
 * Created by zhuang on 2/4/2018.
 */
public class CmppMsgContentWrapperTest {
    @Test
    public void getSplitCount() throws Exception {

        String content = "一二三四五6789A一二三四五6789A一二三四五6789A一二三四五6789A一二三四五6789A一二三四五6789A一二三四五6789A一二三四五6789A一二三四五6789A一二三四五6789A一二三四五6789A一二三四五6789A一二三四五6";

        System.out.println(content.length());
        CmppMsgContentWrapper cmppMsgContentWrapper = new CmppMsgContentWrapper(content,CmppCharsetValues.UCS2, 8);
        System.out.println(cmppMsgContentWrapper.getSplitCount());


    }

}