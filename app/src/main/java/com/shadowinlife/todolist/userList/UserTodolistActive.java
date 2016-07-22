package com.shadowinlife.todolist.userList;

import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import com.ingenic.glass.api.touchboard.GestureDetector;
import com.shadowinlife.todolist.Animation.DepthPageTransformer;
import com.shadowinlife.todolist.R;
import com.shadowinlife.todolist.models.Entity.Todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shadowinlife on 16/7/3.
 */
public class UserTodolistActive extends FragmentActivity implements IListView, GestureDetector.OnGestureListener {
    private static Logger LOG = LoggerFactory.getLogger(UserTodolistActive.class);
    public static final String STATE_LIST = "TodoList";
    private IListPresent presenter;

    public IListPresent getPresenter() {
        if (presenter == null) {
            presenter = new ListPresent(this, this);
        }
        return presenter;
    }

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    @BindView(R.id.todolist_pager)
    ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private ScreenSlidePagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_todolist_active);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            //TODO should add arraylist to mAdapter
            mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager(), new ArrayList<Todo>());
        } else {
            ArrayList<Todo> todoArrayList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager(), todoArrayList);
        }
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new DepthPageTransformer());

        getPresenter().refreshSession();
    }


    @Override
    public void setTodos(ArrayList<Todo> todos) {
        mPagerAdapter.setTodoList(todos);
    }

    @Override
    public void notifyListDataSetChanged() {
        mPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyListItemRemoved(int position) {

    }

    @Override
    public void notifyListItemInserted(int position) {

    }

    @Override
    public void showItemDialog(Todo todo, CharSequence[] items) {

    }

    @Override
    public void showTodoViewToEdit(Todo todo) {

    }

    @Override
    public void showTodoView() {

    }

    /*
     *  Glass Guesture detect
     */
    @Override
    public boolean onDown(boolean b) {
        return false;
    }

    @Override
    public boolean onUp(MotionEvent motionEvent, boolean b) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1, boolean b) {
        return false;
    }

    @Override
    public boolean onLongPress(boolean b) {
        return false;
    }

    @Override
    public boolean onSlideUp(boolean b) {
        return false;
    }

    @Override
    public boolean onSlideDown(boolean b) {
        return false;
    }

    @Override
    public boolean onSlideLeft(boolean b) {
        mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        return true;
    }

    @Override
    public boolean onSlideRight(boolean b) {
        mPager.setCurrentItem(mPager.getCurrentItem() + 1);
        return true;
    }

    @Override
    public boolean onTap(boolean b) {
        return false;
    }

    @Override
    public boolean onDoubleTap(boolean b) {
        return false;
    }

    public void ClickCard(View view) {

    }
}
