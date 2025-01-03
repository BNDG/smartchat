package com.hjq.demo.chat.listener

import com.bndg.smack.callback.IConnectionListener

/**
 * @author r
 * @date 2024/8/26
 * @description Brief description of the file content.
 */
object ConnectionListener :
    IConnectionListener {
    val myConnectionListener: MutableList<IConnectionListener> = mutableListOf()
    fun addConnectionListener(listener: IConnectionListener) {
        myConnectionListener.add(listener)
    }

    fun removeConnectionListener(listener: IConnectionListener) {
        myConnectionListener.remove(listener)
    }

    override fun onConnecting() {
        for (listener in myConnectionListener) {
            listener.onConnecting()
        }
    }

    override fun onConnectSuccess() {
        for (listener in myConnectionListener) {
            listener.onConnectSuccess()
        }
    }

    override fun onAuthenticated() {
        for (listener in myConnectionListener) {
            listener.onAuthenticated()
        }
    }

    override fun onConnectFailed(desc: String?) {
        for (listener in myConnectionListener) {
            listener.onConnectFailed(desc)
        }
    }

    override fun onLoginFailed(code: Int, desc: String?) {
        for (listener in myConnectionListener) {
            listener.onLoginFailed(code, desc)
        }
    }

    override fun imLoading() {
        for (listener in myConnectionListener) {
            listener.imLoading()
        }
    }

    override fun imLoadingEnd() {
        for (listener in myConnectionListener) {
            listener.imLoadingEnd()
        }
    }

    override fun onKickedOffline() {
        for (listener in myConnectionListener) {
            listener.onKickedOffline()
        }
    }
}