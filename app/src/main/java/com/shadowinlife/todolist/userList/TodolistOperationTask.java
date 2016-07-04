package com.shadowinlife.todolist.userList;

import android.os.AsyncTask;

/**
 * Created by shadowinlife on 16/7/2.
 */
public class TodolistOperationTask extends AsyncTask<Void, Void, Integer> {

    // Callback - Listener
    public interface TodolistStatusListener {
        public void onTodolistStatusListener(String todolistName);
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return null;
    }
}
