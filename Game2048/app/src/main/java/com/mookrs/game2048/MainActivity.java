package com.mookrs.game2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int score = 0;
    private TextView textViewScore;
    private static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewScore = (TextView) findViewById(R.id.text_view_score);
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
    }

    public void addScore(int s) {
        score += s;
        showScore();
    }
}
