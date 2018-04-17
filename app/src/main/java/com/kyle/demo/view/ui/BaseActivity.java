package com.kyle.demo.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kyle.demo.view.IMvpView;


public class BaseActivity extends AppCompatActivity implements IMvpView {
    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onLoading() {
        Log.i(TAG, "onLoading, show loading");
    }

    @Override
    public void onLoadingFinish() {
        Log.i(TAG, "onLoadingFinish, finish loading");
    }
}
