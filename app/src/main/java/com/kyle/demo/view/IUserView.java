package com.kyle.demo.view;

import com.kyle.demo.model.User;

import java.util.List;

public interface IUserView extends IMvpView {
    void onDataGot(List<User> userList);
}
