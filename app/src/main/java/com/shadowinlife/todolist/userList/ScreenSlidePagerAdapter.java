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

    public void flipCard() {
        LOG.error("try to flip card");
        if (mShowingBack) {
            fm.popBackStack();
            return;
        }

        // Flip to the back.

        mShowingBack = true;

        // Create and commit a new fragment transaction that adds the fragment for
        // the back of the card, uses custom animations, and is part of the fragment
        // manager's back stack.

        fm.beginTransaction()
                // Replace the default fragment animations with animator resources
                // representing rotations when switching to the back of the card, as
                // well as animator resources representing rotations when flipping
                // back to the front (e.g. when the system Back button is pressed).
                .setCustomAnimations(
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)

                // Replace any fragments currently in the container view with a
                // fragment representing the next page (indicated by the
                // just-incremented currentPage variable).
                .replace(R.id.todolist_pager, BackCardFragment.newInstance("test", "tst"))

                // Add this transaction to the back stack, allowing users to press
                // Back to get to the front of the card.
                .addToBackStack(null)

                // Commit the transaction.
                .commit();
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