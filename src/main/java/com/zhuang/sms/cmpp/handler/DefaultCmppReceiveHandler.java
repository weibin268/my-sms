package com.zhuang.sms.cmpp.handler;

import com.zhuang.sms.cmpp.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhuang on 1/29/2018.
 */
public class DefaultCmppReceiveHandler extends BaseCmppReceiveHandler {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public void handle4CmppConnectResp(CmppReceiveContext context, CmppConnectResp cmppConnectResp) {

        logger.info(cmppConnectResp.toString());

    }

    @Override
    public void handle4CmppSubmitResp(CmppReceiveContext context, CmppSubmitResp cmppSubmitResp) {

        logger.info(cmppSubmitResp.toString());

    }

    @Override
    public void handle4CmppActiveTestResp(CmppReceiveContext context, CmppActiveTestResp cmppActiveTestResp) {

        logger.info(cmppActiveTestResp.toString());


    }

    @Override
    public void handle4CmppDeliver(CmppReceiveContext context, CmppDeliver cmppDeliver) {

        logger.info(cmppDeliver.toString());

    }

    @Override
    public void handle4CmppQueryResp(CmppReceiveContext context, CmppQueryResp cmppQueryResp) {

        logger.info(cmppQueryResp.toString());

    }

    @Override
    public void handle4TerminateResp(CmppReceiveContext context, CmppTerminateResp cmppTerminateResp) {

        logger.info(cmppTerminateResp.toString());

    }

}
