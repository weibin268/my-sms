package com.zhuang.sms.cmpp.provider;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by zhuang on 2/3/2018.
 */
public class DefaultCmppSendProvider implements CmppSendProvider {

    private Queue<CmppSendItem> sendItemQueue;

    public Queue<CmppSendItem> getSendItemQueue() {
        return sendItemQueue;
    }

    public void setSendItemQueue(Queue<CmppSendItem> sendItemQueue) {
        this.sendItemQueue = sendItemQueue;
    }

    public DefaultCmppSendProvider() {

        sendItemQueue = new LinkedList<>();
    }

    @Override
    public List<CmppSendItem> getSendItems(CmppSendContext context) {

        List<CmppSendItem> result = new ArrayList<CmppSendItem>();

        CmppSendItem cmppSendItem = sendItemQueue.poll();
        if (cmppSendItem != null) {
            result.add(cmppSendItem);
        }

        return result;

    }
}
