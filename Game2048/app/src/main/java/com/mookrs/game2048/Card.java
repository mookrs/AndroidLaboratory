package com.mookrs.game2048;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by mookrs on 16/5/4.
 */
public class Card extends FrameLayout {

    private Context context;
    private int number = 0;
    private TextView textViewNumber;

    public Card(Context context) {
        super(context);

        this.context = context.getApplicationContext();

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
        if (number > 0) {
            textViewNumber.setText(number + "");
        } else {
            textViewNumber.setText("");
        }

        switch (number){
            case 0:
                textViewNumber.setBackgroundColor(0x33FFFFFF);
                break;
            case 2:
                textViewNumber.setTextColor(ContextCompat.getColor(context, R.color.text2));
                textViewNumber.setBackgroundColor(ContextCompat.getColor(context, R.color.bg2));
                break;
            case 4:
                textViewNumber.setTextColor(ContextCompat.getColor(context, R.color.text4));
                textViewNumber.setBackgroundColor(ContextCompat.getColor(context, R.color.bg4));
                break;
            case 8:
                textViewNumber.setTextColor(ContextCompat.getColor(context, R.color.text8));
                textViewNumber.setBackgroundColor(ContextCompat.getColor(context, R.color.bg8));
                break;
            case 16:
                textViewNumber.setTextColor(ContextCompat.getColor(context, R.color.text16));
                textViewNumber.setBackgroundColor(ContextCompat.getColor(context, R.color.bg16));
                break;
            case 32:
                textViewNumber.setTextColor(ContextCompat.getColor(context, R.color.text32));
                textViewNumber.setBackgroundColor(ContextCompat.getColor(context, R.color.bg32));
                break;
            case 64:
                textViewNumber.setTextColor(ContextCompat.getColor(context, R.color.text64));
                textViewNumber.setBackgroundColor(ContextCompat.getColor(context, R.color.bg64));
                break;
            case 128:
                textViewNumber.setTextColor(ContextCompat.getColor(context, R.color.text128));
                textViewNumber.setBackgroundColor(ContextCompat.getColor(context, R.color.bg128));
                break;
            case 256:
                textViewNumber.setTextColor(ContextCompat.getColor(context, R.color.text256));
                textViewNumber.setBackgroundColor(ContextCompat.getColor(context, R.color.bg256));
                break;
            case 512:
                textViewNumber.setTextColor(ContextCompat.getColor(context, R.color.text512));
                textViewNumber.setBackgroundColor(ContextCompat.getColor(context, R.color.bg512));
                break;
            case 1024:
                textViewNumber.setTextColor(ContextCompat.getColor(context, R.color.text1024));
                textViewNumber.setBackgroundColor(ContextCompat.getColor(context, R.color.bg1024));
                break;
            case 2048:
                textViewNumber.setTextColor(ContextCompat.getColor(context, R.color.text2048));
                textViewNumber.setBackgroundColor(ContextCompat.getColor(context, R.color.bg2048));
                break;
            default:
                textViewNumber.setTextColor(ContextCompat.getColor(context, R.color.textsuper));
                textViewNumber.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bgsuper));
                break;
        }
    }

    public boolean equals(Card o) {
        return getNumber() == o.getNumber();
    }
}
