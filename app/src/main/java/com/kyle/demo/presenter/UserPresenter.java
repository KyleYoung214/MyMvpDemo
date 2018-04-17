package com.kyle.demo.presenter;

import android.util.Log;

import com.kyle.demo.model.User;
import com.kyle.demo.model.UserModel;
import com.kyle.demo.view.IUserView;

import java.util.List;

public class UserPresenter extends BasePresenter<IUserView> {
    private static final String TAG = "UserPresenter";
    UserModel userModel = UserModel.getInstance();

    public List<User> getAllData() {
        return userModel.getAllData();
    }

    public void updateAllData() {
        if (!isViewAttached()) {
            Log.e(TAG, "Null view, return");
            return;
        }

        Log.i(TAG, "updateAllDate: getting data, should take some time");
        List<User> userList = userModel.getAllData();

        getView().onLoading();

        getView().onDataGot(userList);

        getView().onLoadingFinish();
    }

    public void addData(User user) {
        userModel.addData(user);

        if (isViewAttached()) {
            getView().onLoading();
        }
    }

    public void clearData() {
        userModel.clearData();

        if (isViewAttached()) {
            getView().onLoading();
        }
    }
}
