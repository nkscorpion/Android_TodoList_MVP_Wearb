package com.shadowinlife.todolist.userList;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.shadowinlife.todolist.R;

public class UserTodolistActive extends MvpActivity<UserTodolistView, UserTodolistPresent> implements UserTodolistView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_todolist_active);
    }

    @NonNull
    @Override
    public UserTodolistPresent createPresenter() {
        return null;
    }
}
