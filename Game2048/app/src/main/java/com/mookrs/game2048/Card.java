package com.mookrs.game2048;

import android.content.Context;
import android.widget.FrameLayout;

/**
 * Created by mookrs on 16/5/4.
 */
public class Card extends FrameLayout {

    private int number = 0;

    public Card(Context context) {
        super(context);
        setNumber(0);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
