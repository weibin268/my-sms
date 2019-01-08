package com.zhuang.sms.util;

/**
 * Created by zhuang on 2/2/2018.
 */
public class ThreadUtils {

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        while (true) {
            sleep(60 * 60 * 1000);
        }
    }

}
