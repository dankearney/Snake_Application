package com.example.myapplication;

import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Dan on 1/5/14.
 */
public class Snake {

    public ArrayList<Pair<Integer, Integer>> positions = new ArrayList<Pair<Integer, Integer>>();
    public int direction = 0;
    public Pair<Integer, Integer> foodPosition = new Pair<Integer, Integer>(randomCoordinate(), randomCoordinate());
    public int GRID_SIZE = 20;

    public Snake() {
        if (numSquares() == 0) addSquare((int) (GRID_SIZE / 2), (int) (GRID_SIZE / 2));
    }

    // Adds a square to the beginning of the Snake
    public void addSquare(int x, int y) {
        this.positions.add(0, new Pair<Integer, Integer>(x, y));
    }

    // Removes the last square from the Snake
    public void removeSquare() {
        this.positions.remove(this.positions.size() - 1);
    }

    public ArrayList<Pair<Integer, Integer>> getPositions() {
        return this.positions;
    }

    public int randomCoordinate() {
        return (int)(Math.random() * this.GRID_SIZE);
    }

    public boolean ate() {
        return ((getX(0) == this.foodPosition.first ) && (getY(0) == this.foodPosition.second));
    }

    public int numSquares() {
        return this.positions.size();
    }

    public boolean step() {
        switch (getDirection()) {
            case 0:
                this.addSquare(getX(0)+1, getY(0));
                break;
            case 1:
                this.addSquare(getX(0), getY(0)+1);
                break;
            case 2:
                this.addSquare(getX(0)-1, getY(0));
                break;
            case 3:
                this.addSquare(getX(0), getY(0)-1);
                break;
            default:
                this.addSquare(getX(0)+1, getY(0));
        }
        if (collided() || leftGrid()) {
            return false;
        }
        else if (ate()) {
            foodPosition = new Pair<Integer, Integer>(randomCoordinate(), randomCoordinate());
        } else {
            removeSquare();
        }
        return true;
    }

    public boolean leftGrid() {
        return (getX(0) < 0) || (getX(0) > GRID_SIZE) || (getY(0) < 0) || (getY(0) > GRID_SIZE);
    }

    public boolean collided() {
        for (int i=1; i<this.positions.size(); i++) {
            if ((getX(i) == getX(0) ) && (getY(0) == getY(i))) {
                return true;
            }
        }
        return false;
    }

    public int getX(int index) {
        return this.getPositions().get(index).first;
    }

    public int getY(int index) {
        return this.getPositions().get(index).second;
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
