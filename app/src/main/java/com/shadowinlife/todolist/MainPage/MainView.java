package com.shadowinlife.todolist.MainPage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * TODO: document your custom view class.
 */
public interface MainView extends MvpView {
    // show the number of overdue tasks total
    void showOverdueTaskNum();

    // show today's  tasks number
    void showTodayTasksNum();

    void showTotalTaskNum();

}
