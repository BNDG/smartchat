package com.hjq.demo.app;

import com.hjq.base.BaseFragment;
import com.hjq.demo.action.ToastAction;
import com.hjq.demo.http.model.HttpData;
import com.hjq.http.listener.OnHttpListener;

import okhttp3.Call;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : Fragment 业务基类
 */
public abstract class AppFragment<A extends AppActivity> extends BaseFragment<A>
        implements ToastAction, OnHttpListener<Object> {

    /**
     * 当前加载对话框是否在显示中
     */
    public boolean isShowDialog() {
        A activity = getAttachActivity();
        if (activity == null) {
            return false;
        }
        return activity.isShowDialog();
    }

    /**
     * 显示加载对话框
     */
    public void showDialog() {
        A activity = getAttachActivity();
        if (activity == null) {
            return;
        }
        activity.showDialog();
    }

    /**
     * 隐藏加载对话框
     */
    public void hideDialog() {
        A activity = getAttachActivity();
        if (activity == null) {
            return;
        }
        activity.hideDialog();
    }

    /**
     * {@link OnHttpListener}
     */

    @Override
    public void onHttpStart(Call call) {
        showDialog();
    }

    @Override
    public void onHttpSuccess(Object result) {
        if (!(result instanceof HttpData)) {
            return;
        }
        toast(((HttpData<?>) result).getMsg());
    }

    @Override
    public void onHttpFail(Throwable throwable) {
        toast(throwable.getMessage());
    }

    @Override
    public void onHttpEnd(Call call) {
        hideDialog();
    }
}