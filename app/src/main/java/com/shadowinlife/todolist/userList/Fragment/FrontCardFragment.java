package com.shadowinlife.todolist.userList.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import android.app.Fragment;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextClock;
import android.widget.TextView;

import com.shadowinlife.todolist.Animation.ColorFactory;
import com.shadowinlife.todolist.R;
import com.shadowinlife.todolist.models.Entity.Todo;
import com.shadowinlife.todolist.userList.IListPresent;
import com.shadowinlife.todolist.userList.ListPresent;
import com.todddavies.components.progressbar.ProgressWheel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FrontCardFragment extends Fragment {
    private static final Logger LOG = LoggerFactory.getLogger(FrontCardFragment.class);

    private int position;
    private Todo todo;

    @BindView(R.id.frontcard_Title)
    TextView title;

    @BindView(R.id.activity_countdown_timer_days)
    ProgressWheel mDaysWheel;
    @BindView(R.id.activity_countdown_timer_days_text)
    TextView mDaysLabel;
    @BindView(R.id.activity_countdown_timer_hours)
    ProgressWheel mHoursWheel;
    @BindView(R.id.activity_countdown_timer_hours_text)
    TextView mHoursLabel;
    @BindView(R.id.activity_countdown_timer_minutes)
    ProgressWheel mMinutesWheel;
    @BindView(R.id.activity_countdown_timer_minutes_text)
    TextView mMinutesLabel;
    @BindView(R.id.activity_countdown_timer_seconds)
    ProgressWheel mSecondsWheel;
    @BindView(R.id.activity_countdown_timer_seconds_text)
    TextView mSecondsLabel;

    // Values displayed by the timer
    private int mDisplayDays;
    private int mDisplayHours;
    private int mDisplayMinutes;
    private int mDisplaySeconds;


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
            position = bundle.getInt("position");
        } else {
            position = 0;
        }
        if (bundle.containsKey("todo")) {
            todo = bundle.getParcelable("todo");
        } else {
            todo = new Todo();
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

        Long current = new Date().getTime();
        Long countBack = current - todo.getEdited();

        new CountDownTimer(3600000, 1000) {

            public void onTick(long millisUntilFinished) {
                // decompose difference into days, hours, minutes and seconds
                mDisplayDays = (int) ((millisUntilFinished / 1000) / 86400);
                mDisplayHours = (int) (((millisUntilFinished / 1000) - (mDisplayDays * 86400)) / 3600);
                mDisplayMinutes = (int) (((millisUntilFinished / 1000) - ((mDisplayDays * 86400) + (mDisplayHours * 3600))) / 60);
                mDisplaySeconds = (int) ((millisUntilFinished / 1000) % 60);

                if (mDisplayDays == 0) {
                    mDaysWheel.setVisibility(View.INVISIBLE);
                    mDaysLabel.setVisibility(View.INVISIBLE);

                } else {
                    mDaysWheel.setText(String.valueOf(mDisplayDays));
                    mDaysWheel.setProgress(mDisplayDays);
                }

                if (mDisplayHours == 0) {
                    mHoursWheel.setVisibility(View.INVISIBLE);
                    mHoursLabel.setVisibility(View.INVISIBLE);
                } else {
                    mHoursWheel.setText(String.valueOf(mDisplayHours));
                    mHoursWheel.setProgress(mDisplayHours * 15);
                }

                mMinutesWheel.setText(String.valueOf(mDisplayMinutes));
                mMinutesWheel.setProgress(mDisplayMinutes * 6);

                Animation an = new RotateAnimation(0.0f, 90.0f, 250f, 273f);
                an.setFillAfter(true);

                mSecondsWheel.setText(String.valueOf(mDisplaySeconds));
                mSecondsWheel.setProgress(mDisplaySeconds * 6);
            }

            public void onFinish() {
                //TODO: this is where you would launch a subsequent activity if you'd like.  I'm currently just setting the seconds to zero
                title.setText("Finish");
            }
        }.start();

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
