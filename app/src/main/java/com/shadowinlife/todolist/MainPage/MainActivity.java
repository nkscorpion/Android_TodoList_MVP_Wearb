package com.shadowinlife.todolist.MainPage;

import android.app.Activity;
import android.os.Bundle;

import com.shadowinlife.todolist.R;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainActivity extends Activity {
    private static final Logger LOG = LoggerFactory.getLogger(MainActivity.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
