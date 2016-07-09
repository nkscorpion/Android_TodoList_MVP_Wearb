package com.shadowinlife.todolist.userList.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shadowinlife.todolist.Animation.ColorFactory;
import com.shadowinlife.todolist.R;
import com.shadowinlife.todolist.models.Entity.Todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FrontCardFragment extends Fragment {
    private static Logger LOG = LoggerFactory.getLogger(FrontCardFragment.class);
    private int position;
    private Todo todo;
    @BindView(R.id.Todolist_Title)
    TextView title;

    public static FrontCardFragment newInstance(int position, Todo todo) {
        FrontCardFragment mFragment = new FrontCardFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putParcelable("todo", todo);
        mFragment.setArguments(args);
        return mFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle.containsKey("position")) {
            this.position = bundle.getInt("position");
        } else {
            this.position = 0;
        }
        if (bundle.containsKey("todo")) {
            this.todo = bundle.getParcelable("todo");
        } else {
            this.todo = new Todo();
        }
    }

    @Override
    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = (View) inflater.inflate(
                R.layout.fragment_front_card, container, false);

        ButterKnife.bind(this, rootView);
        rootView.setBackgroundColor(ColorFactory.getBackGroundColor(position));
        title.setTextColor(Color.WHITE);
        title.setText(todo.getTitle());
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}