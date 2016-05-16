package com.mookrs.game2048;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int score = 0;
    private int bestScore = 0;
    private TextView textViewScore;
    private TextView textViewBest;
    private static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewScore = (TextView) findViewById(R.id.text_view_score);
        textViewBest = (TextView) findViewById(R.id.text_view_best);

        SharedPreferences sp = getSharedPreferences("game2048", Context.MODE_PRIVATE);
        bestScore = sp.getInt("best", 0);
        textViewBest.setText(bestScore + "");

        mainActivity = this;
    }

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    public void clearScore() {
        score = 0;
    }

    public void showScore() {
        textViewScore.setText(score + "");
        textViewBest.setText(bestScore + "");
    }

    public void addScore(int s) {
        score += s;
        showScore();
    }

    public int getScore() {
        return score;
    }

    public void setBestScore(int s) {
        bestScore = s;
        showScore();
    }
}
