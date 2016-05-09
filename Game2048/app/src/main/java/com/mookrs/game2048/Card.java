package com.mookrs.game2048;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by mookrs on 16/5/4.
 */
public class Card extends FrameLayout {

    private int number = 0;
    private TextView textViewNumber;

    public Card(Context context) {
        super(context);

        textViewNumber = new TextView(getContext());
        textViewNumber.setTextSize(32);
        textViewNumber.setBackgroundColor(0x33FFFFFF);
        textViewNumber.setGravity(Gravity.CENTER);

        LayoutParams lp = new LayoutParams(-1, -1);
        lp.setMargins(10, 10, 0, 0);
        addView(textViewNumber, lp);

        setNumber(0);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        textViewNumber.setText(number + "");
    }

    public boolean equals(Card o) {
        return getNumber() == o.getNumber();
    }
}
