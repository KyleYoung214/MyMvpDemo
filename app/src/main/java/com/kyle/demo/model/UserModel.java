package com.kyle.demo.model;

import java.util.ArrayList;
import java.util.List;


public class UserModel implements IMvpModel<User> {

    private static UserModel INSTANCE = new UserModel();

    public static UserModel getInstance() {
        return INSTANCE;
    }

    private ArrayList<User> users;

    private UserModel() {
        users = new ArrayList<>();
    }

    @Override
    public void addData(User data) {
        if (null == data) {
            throw new NullPointerException("User data should not be null!");
        }
        users.add(data);
    }

    @Override
    public boolean removeData(User data) {
        if (null == data) {
            throw new NullPointerException("User data should not be null!");
        }
        return users.remove(data);
    }

    @Override
    public User getData(int index) {
        if (index >= users.size()) {
            throw new IllegalArgumentException("too large index or size is 0");
        }
        return users.get(index);
    }

    @Override
    public List<User> getAllData() {
        return users;
    }

    @Override
    public void clearData() {
        users.clear();
    }

    @Override
    public int size() {
        return users.size();
    }
}
