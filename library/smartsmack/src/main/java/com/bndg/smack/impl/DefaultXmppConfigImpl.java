package com.bndg.smack.impl;

import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;

import org.jivesoftware.smack.ConnectionConfiguration;

import com.bndg.smack.BuildConfig;
import com.bndg.smack.constant.SmartConstants;
import com.bndg.smack.contract.ISmartCommConfig;

/**
 * 默认XmppConfig实现类
 */
public class DefaultXmppConfigImpl implements ISmartCommConfig {
    @Override
    public String getDomainName() {

        String xmppDomain = SPUtils.getInstance(SmartConstants.SP_NAME)
                .getString(SmartConstants.CONSTANT_DOMAIN);
        if (TextUtils.isEmpty(xmppDomain)) {
            return BuildConfig.xmppDomain;
        } else {
            return xmppDomain;
        }
    }

    @Override
    public String getHostAddress() {
        String xmppHost = SPUtils.getInstance(SmartConstants.SP_NAME)
                .getString(SmartConstants.CONSTANT_HOST);
        if (TextUtils.isEmpty(xmppHost)) {
            return BuildConfig.xmppHostAddress;
        } else {
            return xmppHost;
        }
    }

    @Override
    public int getPort() {
        int xmppPort = SPUtils.getInstance(SmartConstants.SP_NAME)
                .getInt(SmartConstants.CONSTANT_PORT);
        if (xmppPort == -1) {
            return BuildConfig.xmppPort;
        } else {
            return xmppPort;
        }
    }

    @Override
    public ConnectionConfiguration.SecurityMode getSecurityMode() {
        String sec = SPUtils.getInstance(SmartConstants.SP_NAME).getString(SmartConstants.SECURITY_MODE);
        if (SmartConstants.SECURITY_MODE_REQUIRED.equals(sec)) {
            return ConnectionConfiguration.SecurityMode.required;
        } else if (SmartConstants.SECURITY_MODE_DISABLED.equals(sec)) {
            return ConnectionConfiguration.SecurityMode.disabled;
        }
        return ConnectionConfiguration.SecurityMode.ifpossible;
    }
}
