package com.shadowinlife.todolist.userList;

import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shadowinlife.todolist.Animation.ColorFactory;
import com.shadowinlife.todolist.R;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FrontCardFragment extends Fragment {
    private static Logger LOG = LoggerFactory.getLogger(FrontCardFragment.class);
    int mNum;
    @BindView(R.id.Todolist_Title)
    TextView title;

    static FrontCardFragment newInstance(int num) {
        FrontCardFragment mFragment = new FrontCardFragment();
        Bundle args = new Bundle();
        args.putInt("num", num);
        mFragment.setArguments(args);
        return mFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        System.out.println("mNum Fragment create =" + mNum);
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
        rootView.setBackgroundColor(ColorFactory.getBackGroundColor(mNum));
        title.setTextColor(Color.WHITE);
        title.setText(String.valueOf(mNum));
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
