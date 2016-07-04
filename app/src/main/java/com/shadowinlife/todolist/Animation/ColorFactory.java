package com.shadowinlife.todolist.Animation;

import android.graphics.Color;

/**
 * Created by shadowinlife on 16/7/5.
 */
public class ColorFactory {
    public static int getBackGroundColor(int position) {
        switch (position % 6) {
            case 0:
                return Color.BLACK;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.RED;
            case 3:
                return Color.YELLOW;
            case 4:
                return Color.GRAY;
            case 5:
                return Color.GREEN;
            default:
                return Color.WHITE;
        }
    }
}
