package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Pair;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

public class SnakeActivity extends Activity {

    public Snake snake;
    private static Handler customHandler = new Handler();

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        initializeSnake();
        playSnake();
    }

    public void playSnake() {
        new Runnable() {
            public void run() {
                step();
                resetSnake();
                SnakeActivity.customHandler.postDelayed(this, 500);
            }
        }.run();
    }

    public boolean step() {
        return this.snake.step();
    }

    public void initializeSnake() {
        this.snake = new Snake();
        setContentView(R.layout.snake_activity);
        SnakeView sv = (SnakeView)findViewById(R.id.view);
        sv.setSnake(this.getSnake());
    }

    public void resetSnake() {
        SnakeView sv = (SnakeView)findViewById(R.id.view);
        sv.setSnake(this.getSnake());
        sv.invalidate();
    }

    public Snake getSnake() {
        return this.snake;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}