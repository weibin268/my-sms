package com.zhuang.sms.cmpp.model;

/**
 * Created by zhuang on 2/3/2018.
 */
public class CmppSettings {

    private int activeTestDelaySeconds = 50;

    private int smsTitleWordCount = 0;

    public CmppSettings() {

    }

    public int getActiveTestDelaySeconds() {
        return activeTestDelaySeconds;
    }

    public void setActiveTestDelaySeconds(int activeTestDelaySeconds) {
        this.activeTestDelaySeconds = activeTestDelaySeconds;
    }

    public int getSmsTitleWordCount() {
        return smsTitleWordCount;
    }

    public void setSmsTitleWordCount(int smsTitleWordCount) {
        this.smsTitleWordCount = smsTitleWordCount;
    }
}
