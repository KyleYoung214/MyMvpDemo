package com.kyle.demo.view.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidkun.xtablayout.XTabLayout;
import com.kyle.demo.R;
import com.kyle.demo.model.User;
import com.kyle.demo.presenter.UserPresenter;
import com.kyle.demo.view.IUserView;
import com.kyle.demo.view.adapter.TestAdapter;
import com.kyle.demo.view.adapter.UserAdapter;

import java.util.List;
import java.util.Observable;

public class MainActivity extends BaseActivity implements IUserView {

    private static final String TAG = "MainActivity";

    private UserPresenter userPresenter;
    private static int counter = 0;

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList;
    private TextView testSelectTV;

    private RecyclerView testRv;
    private TestAdapter testAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "--onCreate: ");

        setContentView(R.layout.activity_main);

        userPresenter = new UserPresenter();
        userPresenter.attachView(this);

        userList = userPresenter.getAllData();

        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "--onDestroy: ");
        userPresenter.detachView();

        //itemClicked.deleteObservers();
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

        testSelectTV = findViewById(R.id.text_select);
        testSelectTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (testSelectTV.isSelected()) {
                    testSelectTV.setSelected(false);
                    testSelectTV.setBackgroundColor(Color.RED);
                } else {
                    testSelectTV.setSelected(true);
                    testSelectTV.setBackgroundColor(Color.BLUE);
                }
            }
        });

        testAdapter = new TestAdapter();
        testRv = findViewById(R.id.rv_test);
        testRv.setLayoutManager(new LinearLayoutManager(this));
//        testRv.setAdapter(testAdapter);

        xTabLayout = findViewById(R.id.x_tablayout);
        for (String str : mChannelsStr) {
            xTabLayout.addTab(xTabLayout.newTab().setText(str));
        }

        findViewById(R.id.tab_types).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TypeActivity.class);
                intent.putExtra("tabs", mChannelsStr);
                startActivityForResult(intent, 0);
            }
        });
    }

    private XTabLayout xTabLayout;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "cancel selsect", Toast.LENGTH_SHORT).show();
            } else {
                int index = data.getIntExtra("index", -1);
                if (index >= 0) {
                    xTabLayout.getTabAt(index).select();
                } else {
                    Toast.makeText(this, "index -1??", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private String[] mChannelsStr =
            new String[]{"推荐", "游戏", "视频", "搞笑", "军事", "社会",
                    "国际", "本地", "趣图", "体育", "音乐", "其他"};

    public static class ItemClicked extends Observable {
        public void updateData(String str) {
            setChanged();
            notifyObservers(str);
        }
    }

    public static ItemClicked itemClicked = new ItemClicked();

    public void onTestClick(View view) {
        startActivity(new Intent(MainActivity.this, PermissionActivity.class));
    }
}
