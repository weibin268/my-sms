package com.zhuang.sms.cmpp.model;

/**
 * Created by zhuang on 1/27/2018.
 */
public class CmppAccountInfo {

    /***
     *登录网关帐号
     */
    private String account;

    /***
     * 登录网关密码
     */
    private String password;

    /***
     *企业代码
     */
    private String spId;

    /***
     * 服务代码
     */
    private String spCode;

    /***
     * 业务代码
     */
    private String serviceId;


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public String getSpCode() {
        return spCode;
    }

    public void setSpCode(String spCode) {
        this.spCode = spCode;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

}
