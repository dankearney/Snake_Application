package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
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
        Runnable run = new Runnable() {
            public void run() {
                step();
                SnakeActivity.customHandler.postDelayed(this, 1000);
            }
        };
        SnakeActivity.customHandler.postDelayed(run, 1000);
    }

    public void step() {
        this.snake.step();
        SnakeView sv = (SnakeView)findViewById(R.id.view);
        sv.setSnake(this.getSnake());
        sv.invalidate();
    }

    public void initializeSnake() {
        this.snake = new Snake();
        setContentView(R.layout.snake_activity);
        SnakeView sv = (SnakeView)findViewById(R.id.view);
        sv.setSnake(this.getSnake());
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

    public boolean setLeadDirection(int direction) {
        this.snake.setDirection(direction);
        return true;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == 0) {
            switch ((int)(event.getKeyCode())) {
                case 32: return setLeadDirection(0);
                case 47: return setLeadDirection(1);
                case 29: return setLeadDirection(2);
                case 51: return setLeadDirection(3);
                default: return super.dispatchKeyEvent(event);
            }
        } else {
            return super.dispatchKeyEvent(event);
        }
    }
}