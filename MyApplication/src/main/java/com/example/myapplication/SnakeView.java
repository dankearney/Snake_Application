package com.example.myapplication;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.View;

/**
 * Created by Dan on 1/5/14.
 */
public class SnakeView extends View {

    public Paint backgroundPaint;
    public Paint snakeBodyColor;
    public int canvasPadding = 50;
    public int SQUARE_WIDTH = 25;
    public Snake snake;
    public int top;
    public int left;
    public int right;
    public int bottom;

    public SnakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.backgroundPaint = new Paint();
        this.backgroundPaint.setColor(Color.rgb(220,220, 220));
        this.snakeBodyColor = new Paint();
        this.snakeBodyColor.setColor(Color.rgb(100,110, 255));
    }

    public void measureScreen() {
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        this.top = this.canvasPadding;
        this.left = this.canvasPadding;
        this.right = metrics.widthPixels - this.canvasPadding;
        this.bottom = this.getBottom() - this.canvasPadding;
        this.SQUARE_WIDTH = (int) ((right - left) / this.snake.GRID_SIZE);
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public void onDraw(Canvas c) {
        measureScreen();
        c.drawRect(this.left, this.top, this.right, this.bottom, this.backgroundPaint);
        drawFood(c);
        drawSnake(c);
    }

    public void drawFood(Canvas c) {
        this.drawSquare(c, this.snake.foodPosition.first, this.snake.foodPosition.second);
    }

    public void drawSnake(Canvas c) {
        for (int i=0; i<this.snake.numSquares(); i++) {
            drawSquare(c, this.snake.getX(i), this.snake.getY(i));
        }
    }

    public void drawSquare(Canvas c, int x, int y) {
        c.drawRect(
                this.canvasPadding + x*this.SQUARE_WIDTH,
                this.canvasPadding + y*this.SQUARE_WIDTH,
                this.canvasPadding + (x+1)*this.SQUARE_WIDTH,
                this.canvasPadding + (y+1)*this.SQUARE_WIDTH,
                this.snakeBodyColor
        );
    }

}
