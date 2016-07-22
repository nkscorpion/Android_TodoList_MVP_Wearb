package com.shadowinlife.todolist.userList;

import android.content.Context;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.shadowinlife.todolist.R;
import com.shadowinlife.todolist.models.Entity.Todo;
import com.shadowinlife.todolist.userList.Fragment.BackCardFragment;
import com.shadowinlife.todolist.userList.Fragment.FrontCardFragment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by shadowinlife on 16/7/6.
 */
public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    private static Logger LOG = LoggerFactory.getLogger(ScreenSlidePagerAdapter.class);
    private ArrayList<Todo> todos;
    private Context context;
    private FragmentManager fm;
    private boolean mShowingBack;

    public void setTodoList(ArrayList<Todo> todos) {
        this.todos = todos;
    }

    public ScreenSlidePagerAdapter(FragmentManager fm, ArrayList<Todo> todos) {
        super(fm);
        this.todos = todos;
        this.fm = fm;
    }
    // 初始化每个页卡选项
    @Override
    public Object instantiateItem(ViewGroup viewGroup, int position) {
        this.mShowingBack = false;
        return super.instantiateItem(viewGroup, position);

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        LOG.error("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        FrontCardFragment mFragment = FrontCardFragment.newInstance(position, todos.get(position));
        return mFragment;
    }

    @Override
    public int getCount() {
        return todos.size();
    }
}