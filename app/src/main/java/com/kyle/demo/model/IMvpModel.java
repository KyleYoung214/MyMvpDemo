package com.kyle.demo.model;

import java.util.List;

public interface IMvpModel<T> {

    void addData(T data);

    boolean removeData(T data);

    T getData(int index);

    List<T> getAllData();

    void clearData();

    int size();
}
