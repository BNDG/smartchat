package com.bndg.smack.callback;

/**
 * author : r
 * time   : 2024/6/8 7:26 PM
 * desc   : 连接状态监听器
 */
public interface IConnectionListener {
    // 连接中
    void onConnecting();

    // 已连接
    void onConnectSuccess();

    // 已鉴权
    void onAuthenticated();

    // 连接失败
    void onConnectFailed(String desc);

    // 登录失败
    void onLoginFailed(int code, String desc);

    void imLoading();

    // im加载结束
    void imLoadingEnd();

    // 当前用户被踢下线
    void onKickedOffline();

}
