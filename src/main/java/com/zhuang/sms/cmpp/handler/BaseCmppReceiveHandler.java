package com.zhuang.sms.cmpp.handler;

import com.zhuang.sms.cmpp.CmppClient;
import com.zhuang.sms.cmpp.enums.CmppCommandIdValues;
import com.zhuang.sms.cmpp.enums.CmppVersionValues;
import com.zhuang.sms.cmpp.model.*;
import com.zhuang.sms.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by zhuang on 1/29/2018.
 */
public abstract class BaseCmppReceiveHandler implements CmppReceiveHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void handle(CmppReceiveContext context) {

        byte[] bytes = context.getBytes();

        CmppClient cmppClient = context.getCmppClient();

        CmppMessageHeader cmppMessageHeader = new CmppMessageHeader();
        cmppMessageHeader.fromBytes(bytes);

        if (cmppMessageHeader.getCommandId() == CmppCommandIdValues.CMPP_CONNECT_RESP) {

            CmppConnectResp cmppConnectResp = null;

            if (cmppClient.getCmppVersion() == CmppVersionValues.CMPP_VERSION_20) {
                cmppConnectResp = new CmppConnectResp20();
            } else if (cmppClient.getCmppVersion() == CmppVersionValues.CMPP_VERSION_30) {
                cmppConnectResp = new CmppConnectResp30();
            }

            cmppConnectResp.setCmppMessageHeader(cmppMessageHeader);
            cmppConnectResp.fromBytes(Arrays.copyOfRange(bytes, CmppMessageHeader.LENGTH, bytes.length));
            if (cmppConnectResp.getStatus() == 0) {
                cmppClient.setConnected(true);
                logger.info("Connect and login gateway successfully！");
            } else {
                cmppClient.setConnected(false);
                logger.info("Connect and login gateway failed！（status:" + cmppConnectResp.getStatus() + "）");
            }
            handle4CmppConnectResp(context, cmppConnectResp);
        } else if (cmppMessageHeader.getCommandId() == CmppCommandIdValues.CMPP_SUBMIT_RESP) {

            context.getCmppClient().getCmppSendWindow().remove(cmppMessageHeader.getSequenceId());

            CmppSubmitResp cmppSubmitResp = new CmppSubmitResp();
            cmppSubmitResp.setCmppMessageHeader(cmppMessageHeader);
            cmppSubmitResp.fromBytes(Arrays.copyOfRange(bytes, CmppMessageHeader.LENGTH, bytes.length));

            handle4CmppSubmitResp(context, cmppSubmitResp);

        } else if (cmppMessageHeader.getCommandId() == CmppCommandIdValues.CMPP_ACTIVE_TEST_RESP) {

            CmppActiveTestResp cmppActiveTestResp = new CmppActiveTestResp();
            cmppActiveTestResp.setCmppMessageHeader(cmppMessageHeader);
            cmppActiveTestResp.fromBytes(Arrays.copyOfRange(bytes, CmppMessageHeader.LENGTH, bytes.length));
            handle4CmppActiveTestResp(context, cmppActiveTestResp);

        } else if (cmppMessageHeader.getCommandId() == CmppCommandIdValues.CMPP_DELIVER) {

            CmppDeliver cmppDeliver = null;

            if (cmppClient.getCmppVersion() == CmppVersionValues.CMPP_VERSION_20) {
                cmppDeliver = new CmppDeliver20();
            } else if (cmppClient.getCmppVersion() == CmppVersionValues.CMPP_VERSION_30) {
                cmppDeliver = new CmppDeliver30();
            }

            cmppDeliver.setCmppMessageHeader(cmppMessageHeader);
            cmppDeliver.fromBytes(Arrays.copyOfRange(bytes, CmppMessageHeader.LENGTH, bytes.length));

            if (cmppClient.getCmppVersion() == CmppVersionValues.CMPP_VERSION_20) {

                CmppDeliverResp20 cmppDeliverResp20 = new CmppDeliverResp20();
                cmppDeliverResp20.setMsgId(cmppDeliver.getMsgId());
                cmppDeliverResp20.setResult((byte) 0);
                cmppClient.send(cmppDeliverResp20.toBytes());

            } else if (cmppClient.getCmppVersion() == CmppVersionValues.CMPP_VERSION_30) {

                CmppDeliverResp30 cmppDeliverResp30 = new CmppDeliverResp30();
                cmppDeliverResp30.setMsgId(cmppDeliver.getMsgId());
                cmppDeliverResp30.setResult(0);
                cmppClient.send(cmppDeliverResp30.toBytes());
            }

            handle4CmppDeliver(context, cmppDeliver);
        } else if (cmppMessageHeader.getCommandId() == CmppCommandIdValues.CMPP_QUERY_RESP) {

            CmppQueryResp cmppQueryResp = new CmppQueryResp();
            cmppQueryResp.setCmppMessageHeader(cmppMessageHeader);
            cmppQueryResp.fromBytes(Arrays.copyOfRange(bytes, CmppMessageHeader.LENGTH, bytes.length));

            handle4CmppQueryResp(context, cmppQueryResp);
        } else if (cmppMessageHeader.getCommandId() == CmppCommandIdValues.CMPP_TERMINATE_RESP) {

            CmppTerminateResp cmppTerminateResp = new CmppTerminateResp();
            cmppTerminateResp.setCmppMessageHeader(cmppMessageHeader);
            cmppClient.setConnected(false);

            logger.info("Logout gateway successfully！！");
            handle4TerminateResp(context, cmppTerminateResp);

        } else {
            logger.info("Unknown CmppMessage:" + ByteUtils.toHexString(bytes));
        }


    }

    public abstract void handle4CmppConnectResp(CmppReceiveContext context, CmppConnectResp cmppConnectResp);

    public abstract void handle4CmppSubmitResp(CmppReceiveContext context, CmppSubmitResp cmppSubmitResp);

    public abstract void handle4CmppActiveTestResp(CmppReceiveContext context, CmppActiveTestResp cmppActiveTestResp);

    public abstract void handle4CmppDeliver(CmppReceiveContext context, CmppDeliver cmppDeliver);

    public abstract void handle4CmppQueryResp(CmppReceiveContext context, CmppQueryResp cmppQueryResp);

    public abstract void handle4TerminateResp(CmppReceiveContext context, CmppTerminateResp cmppTerminateResp);

}
