package com.zhuang.sms.cmpp.model;

/**
 * Created by zhuang on 2/3/2018.
 */
public class CmppSendWindow {

    long[] items;

    public CmppSendWindow(int size)
    {
        items=new long[size];
    }

    public boolean add(long sequenceId)
    {
        boolean result=false;

        for (int i = 0; i < items.length; i++) {

            if(items[i]==0)
            {
                items[i]=sequenceId;
                result=true;
                break;
            }
        }

        return result;
    }

    public boolean remove(long sequenceId)
    {
        boolean result=false;

        for (int i = 0; i < items.length; i++) {

            if(items[i]==sequenceId)
            {
                items[i]=0;
                result=true;
                break;
            }
        }

        return result;
    }

    public void clear()
    {
        for (int i = 0; i < items.length; i++) {
            items[i]=0;
        }
    }

    public void resize(int size)
    {
        items=new long[size];
    }
}
