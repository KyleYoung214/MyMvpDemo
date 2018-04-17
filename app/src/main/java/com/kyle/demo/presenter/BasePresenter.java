package com.kyle.demo.presenter;

import com.kyle.demo.view.IMvpView;

public class BasePresenter<V extends IMvpView> {
    private V mMvpView;

    public void attachView(V mvpView) {
        mMvpView = mvpView;
    }

    public void detachView() {
        mMvpView = null;
    }

    protected boolean isViewAttached() {
        return mMvpView != null;
    }

    protected V getView() {
        return mMvpView;
    }
}
