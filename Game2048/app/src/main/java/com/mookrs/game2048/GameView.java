package com.mookrs.game2048;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

/**
 * Created by mookrs on 16/5/4.
 */
public class GameView extends GridLayout {

    public GameView(Context context) {
        super(context);
        intiGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        intiGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intiGameView();
    }

    private void intiGameView() {

        setColumnCount(4);
        setBackgroundColor(0xFFBBADA0);

        setOnTouchListener(new OnTouchListener() {
            private float startX, startY;
            private float endX, endY;
            private float offsetX, offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        endX = event.getX();
                        endY = event.getY();

                        offsetX = endX - startX;
                        offsetY = endY - startY;

                        if (Math.abs(offsetX) > Math.abs(offsetY)) {
                            if (offsetX < -5) {
                                moveLeft();
                                System.out.println("Left");
                            } else if (offsetX > 5) {
                                moveRight();
                                System.out.println("Right");
                            }
                        } else {
                            if (offsetY < -5) {
                                moveUp();
                                System.out.println("Up");
                            } else if (offsetY > 5) {
                                moveDown();
                                System.out.println("Down");
                            }
                        }
                }
                return true;
            }
        });
    }

    private void moveLeft() {
    }

    private void moveRight() {

    }

    private void moveUp() {
    }

    private void moveDown() {

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int cardSize = (Math.min(w, h) - 10) / 4;
        addCards(cardSize);
    }

    private void addCards(int cardSize) {
        Card card;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                card = new Card(getContext());
                card.setNumber(2);
                addView(card, cardSize, cardSize);
            }
        }
    }
}
