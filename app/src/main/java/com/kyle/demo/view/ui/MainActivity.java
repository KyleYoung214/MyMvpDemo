package com.kyle.demo.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.kyle.demo.R;
import com.kyle.demo.model.User;
import com.kyle.demo.presenter.UserPresenter;
import com.kyle.demo.view.IUserView;
import com.kyle.demo.view.adapter.UserAdapter;

import java.util.List;

public class MainActivity extends BaseActivity implements IUserView {

    private static final String TAG = "MainActivity";

    private UserPresenter userPresenter;
    private static int counter = 0;

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        userPresenter = new UserPresenter();
        userPresenter.attachView(this);

        userList = userPresenter.getAllData();

        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userPresenter.detachView();
    }

    public void onAddClick(View view) {
        User user = new User("teacher" + counter, 20 + counter, "describe content " + counter);
        if (counter % 2 == 0) {
            user.setType(User.STUDENT);
            user.setName("student " + counter);
        } else {
            user.setType(User.TEACHER);
        }

        userPresenter.addData(user);

        int position = userList.size() - 1;

        userAdapter.notifyItemInserted(position);

        recyclerView.scrollToPosition(position);

        counter++;

    }

    public void onClearClick(View view) {
        userPresenter.clearData();
        counter = 0;

        userAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDataGot(List<User> userList) {
        Log.i(TAG, "onDataGot: update ui");
    }


    private void initView() {
        recyclerView = findViewById(R.id.rv_user);
        userAdapter = new UserAdapter(userList);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL,
                false));
        recyclerView.setAdapter(userAdapter);
    }
}
