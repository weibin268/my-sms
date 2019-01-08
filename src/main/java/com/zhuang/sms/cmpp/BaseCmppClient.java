package com.zhuang.sms.cmpp;

import com.zhuang.sms.cmpp.enums.CmppMsgFmtValues;
import com.zhuang.sms.cmpp.model.*;
import com.zhuang.sms.util.ByteUtils;
import com.zhuang.sms.util.CmppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

/**
 * Created by zhuang on 2/3/2018.
 */
public abstract class BaseCmppClient implements CmppClient {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected byte cmppVersion;

    protected boolean connected = false;

    protected Socket socket;

    protected String host;

    protected int port;

    protected CmppAccountInfo cmppAccountInfo;

    protected CmppSettings cmppSettings;

    protected CmppSendWindow cmppSendWindow;

    @Override
    public byte getCmppVersion() {
        return cmppVersion;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public CmppAccountInfo getCmppAccountInfo() {
        return cmppAccountInfo;
    }

    public void setCmppAccountInfo(CmppAccountInfo cmppAccountInfo) {
        this.cmppAccountInfo = cmppAccountInfo;
    }

    public CmppSettings getCmppSettings() {
        return cmppSettings;
    }

    public void setCmppSettings(CmppSettings cmppSettings) {
        this.cmppSettings = cmppSettings;
    }

    public CmppSendWindow getCmppSendWindow() {
        return cmppSendWindow;
    }

    public void setCmppSendWindow(CmppSendWindow cmppSendWindow) {
        this.cmppSendWindow = cmppSendWindow;
    }

    public boolean isConnected() {
        return connected;
    }

    public synchronized void setConnected(boolean connected) {
        this.connected = connected;
    }

    public BaseCmppClient(byte cmppVersion, String host, int port, CmppAccountInfo cmppAccountInfo) {
        this.cmppVersion = cmppVersion;
        this.host = host;
        this.port = port;
        this.cmppAccountInfo = cmppAccountInfo;
        this.cmppSettings = new CmppSettings();
        this.cmppSendWindow = new CmppSendWindow(16);
    }

    @Override
    public void connect() {

        try {

            socket = new Socket(host, port);

            OutputStream outputStream = socket.getOutputStream();

            CmppConnect cmppConnect = new CmppConnect();
            cmppConnect.setSourceAddr(cmppAccountInfo.getAccount());
            cmppConnect.setAuthenticatorSource(CmppUtils.getAuthenticatorSource(cmppAccountInfo.getAccount(), cmppAccountInfo.getPassword()));
            cmppConnect.setVersion(cmppVersion);

            byte[] bytes = cmppConnect.toBytes();

            logger.info("Connect Data:" + ByteUtils.toHexString(bytes));

            outputStream.write(bytes);

        } catch (IOException e) {
            throw new RuntimeException("connect error!", e);
        }

    }

    @Override
    public boolean isSocketConnected() {
        return socket != null && socket.isConnected();
    }

    @Override
    public void send(byte[] bytes) {
        try {

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);

            logger.info("Send Data:" + ByteUtils.toHexString(bytes));

        } catch (IOException e) {
            setConnected(false);
            throw new RuntimeException("send error!", e);
        }
    }

    @Override
    public void send(String msgContent, String[] phoneNoList, String spCodeSuffix) {

        if (spCodeSuffix == null) spCodeSuffix = "";

        //长短信拆分需要用UCS2编码
        byte msgFmt = CmppMsgFmtValues.UCS2;
        String charset = CmppUtils.getCharsetByMsgFmt(msgFmt);

        CmppMsgContentWrapper cmppMsgContentWrapper = new CmppMsgContentWrapper(msgContent, charset, cmppSettings.getSmsTitleWordCount());

        if (cmppMsgContentWrapper.isNeedSplit()) {
            CmppSubmit.MsgContext4Split[] msgContext4Splits = cmppMsgContentWrapper.split();

            for (CmppSubmit.MsgContext4Split item : msgContext4Splits) {
                send(item.toBytes(), (byte) item.getPkTotal(), (byte) item.getPkNumber(), (byte) 1, msgFmt, phoneNoList, spCodeSuffix);
            }

        } else {
            msgFmt = CmppMsgFmtValues.GB_CHINESE;
            charset = CmppUtils.getCharsetByMsgFmt(msgFmt);
            send(ByteUtils.toBytes(msgContent, charset), (byte) 1, (byte) 1, (byte) 0, msgFmt, phoneNoList, spCodeSuffix);
        }
    }

    protected abstract void send(byte[] msgContentBytes, byte pkTotal, byte pkNumber, byte tpUdhi, byte msgFmt, String[] destTerminalId, String spCodeSuffix);

    @Override
    public void sendActiveTest() {
        send(new CmppActiveTest().toBytes());
    }

    @Override
    public byte[] receive() {

        try {

            InputStream inputStream = socket.getInputStream();

            byte[] headBytes = new byte[CmppMessageHeader.LENGTH];
            inputStream.read(headBytes, 0, headBytes.length);

            int bodyLength = ByteUtils.toInt(Arrays.copyOfRange(headBytes, 0, 4)) - CmppMessageHeader.LENGTH;
            byte[] bodyBytes = new byte[bodyLength];
            inputStream.read(bodyBytes, 0, bodyBytes.length);

            byte[] result = ByteUtils.concat(headBytes, bodyBytes);

            logger.info("Receive Data:" + ByteUtils.toHexString(result));

            return result;

        } catch (IOException e) {
            setConnected(false);
            throw new RuntimeException("receive error!", e);
        }


    }

    @Override
    public void close() {
        try {

            cmppSendWindow.clear();

            setConnected(false);

            if (socket != null) {
                socket.close();
            }

        } catch (IOException e) {
            throw new RuntimeException("socket.close() error!", e);
        }
    }
}
