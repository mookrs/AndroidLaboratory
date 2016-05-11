package com.mookrs.game2048;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mookrs on 16/5/4.
 */
public class GameView extends GridLayout {

    // 记录游戏
    private Card[][] cardMap = new Card[4][4];
    // 空点列表
    private List<Point> emptyPoints = new ArrayList<>();

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
                            } else if (offsetX > 5) {
                                moveRight();
                            }
                        } else {
                            if (offsetY < -5) {
                                moveUp();
                            } else if (offsetY > 5) {
                                moveDown();
                            }
                        }
                }
                return true;
            }
        });
    }

    private void moveLeft() {
        boolean isMoved = false;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // 当前位置往右扫描
                for (int y = j + 1; y < 4; y++) {
                    if (cardMap[i][y].getNumber() > 0) {
                        if (cardMap[i][j].getNumber() <= 0) {
                            cardMap[i][j].setNumber(cardMap[i][y].getNumber());
                            cardMap[i][y].setNumber(0);
                            // 从j+1处再来
                            y = j + 1;
                            isMoved = true;
                        } else if (cardMap[i][j].equals(cardMap[i][y])) {
                            cardMap[i][j].setNumber(cardMap[i][j].getNumber() * 2);
                            cardMap[i][y].setNumber(0);
                            MainActivity.getMainActivity().addScore(cardMap[i][j].getNumber());
                            isMoved = true;
                        }
                    }
                }
            }
        }

        if (isMoved) {
            addRandomNumber();
            checkGame();
        }
    }

    private void moveRight() {
        boolean isMoved = false;

        for (int i = 0; i < 4; i++) {
            for (int j = 3; j >= 0; j--) {
                // 当前位置往左扫描
                for (int y = j - 1; y >= 0; y--) {
                    if (cardMap[i][y].getNumber() > 0) {
                        if (cardMap[i][j].getNumber() <= 0) {
                            cardMap[i][j].setNumber(cardMap[i][y].getNumber());
                            cardMap[i][y].setNumber(0);
                            y = j - 1;
                            isMoved = true;
                        } else if (cardMap[i][j].equals(cardMap[i][y])) {
                            cardMap[i][j].setNumber(cardMap[i][j].getNumber() * 2);
                            cardMap[i][y].setNumber(0);
                            MainActivity.getMainActivity().addScore(cardMap[i][j].getNumber());
                            isMoved = true;
                        }
                    }
                }
            }
        }

        if (isMoved) {
            addRandomNumber();
            checkGame();
        }
    }

    private void moveUp() {
        boolean isMoved = false;

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                // 当前位置往下扫描
                for (int x = i + 1; x < 4; x++) {
                    if (cardMap[x][j].getNumber() > 0) {
                        if (cardMap[i][j].getNumber() <= 0) {
                            cardMap[i][j].setNumber(cardMap[x][j].getNumber());
                            cardMap[x][j].setNumber(0);
                            x = i + 1;
                            isMoved = true;
                        } else if (cardMap[i][j].equals(cardMap[x][j])) {
                            cardMap[i][j].setNumber(cardMap[i][j].getNumber() * 2);
                            cardMap[x][j].setNumber(0);
                            MainActivity.getMainActivity().addScore(cardMap[i][j].getNumber());
                            isMoved = true;
                        }
                    }
                }
            }
        }

        if (isMoved) {
            addRandomNumber();
            checkGame();
        }
    }

    private void moveDown() {
        boolean isMoved = false;

        for (int j = 0; j < 4; j++) {
            for (int i = 3; i >= 0; i--) {
                // 当前位置往上扫描
                for (int x = i - 1; x >= 0; x--) {
                    if (cardMap[x][j].getNumber() > 0) {
                        if (cardMap[i][j].getNumber() <= 0) {
                            cardMap[i][j].setNumber(cardMap[x][j].getNumber());
                            cardMap[x][j].setNumber(0);
                            x = i - 1;
                            isMoved = true;
                        } else if (cardMap[i][j].equals(cardMap[x][j])) {
                            cardMap[i][j].setNumber(cardMap[i][j].getNumber() * 2);
                            cardMap[x][j].setNumber(0);
                            MainActivity.getMainActivity().addScore(cardMap[i][j].getNumber());
                            isMoved = true;
                        }
                    }
                }
            }
        }

        if (isMoved) {
            addRandomNumber();
            checkGame();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int cardSize = (Math.min(w, h) - 10) / 4;
        addCards(cardSize);

        startGame();
    }


    private void startGame() {
        // clearScore()如果调用showScore()，上边的onSizeChanged()会被调用两次，原因未知
        MainActivity.getMainActivity().clearScore();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cardMap[i][j].setNumber(0);
            }
        }

        // 初始化两个卡片
        addRandomNumber();
        addRandomNumber();
    }

    private void addCards(int cardSize) {
        Card card;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                card = new Card(getContext());
                card.setNumber(0);
                addView(card, cardSize, cardSize);
                cardMap[i][j] = card;
            }
        }
    }

    private void addRandomNumber() {
        emptyPoints.clear();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cardMap[i][j].getNumber() <= 0) {
                    emptyPoints.add(new Point(i, j));
                }
            }
        }

        Point point = emptyPoints.remove((int) (Math.random() * emptyPoints.size()));
        // 按9:1的比例生成2和4
        cardMap[point.x][point.y].setNumber(Math.random() > 0.1 ? 2 : 4);
    }

    private void checkGame() {
        boolean isCompleted = true;

        ALL:
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cardMap[i][j].getNumber() <= 0 ||
                        (i > 0 && cardMap[i][j].equals(cardMap[i - 1][j])) ||
                        (i < 3 && cardMap[i][j].equals(cardMap[i + 1][j])) ||
                        (j > 0 && cardMap[i][j].equals(cardMap[i][j - 1])) ||
                        (j < 3 && cardMap[i][j].equals(cardMap[i][j + 1]))) {
                    isCompleted = false;
                    break ALL;
                }
            }
        }

        if (isCompleted) {
            new AlertDialog.Builder(getContext())
                    .setTitle("2048")
                    .setMessage("游戏结束")
                    .setPositiveButton("重新开始", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startGame();
                        }
                    }).show();
        }
    }
}
