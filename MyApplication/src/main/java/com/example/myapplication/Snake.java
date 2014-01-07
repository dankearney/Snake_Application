package com.example.myapplication;

import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Dan on 1/5/14.
 */
public class Snake {

    public ArrayList<List<Integer>> positions = new ArrayList<List<Integer>>();
    public Pair<Integer, Integer> foodPosition = new Pair<Integer, Integer>(randomCoordinate(), randomCoordinate());
    public int GRID_SIZE = 10;

    public Snake() {
        if (numSquares() == 0) {
            addSquare(0, 0, 0);
        }
    }

    public void addSquare(int x, int y, int direction) {
        ArrayList<Integer> newSquare = new ArrayList<Integer>();
        newSquare.add(randomCoordinate());
        newSquare.add(randomCoordinate());
        newSquare.add(0);
        this.positions.add(newSquare);
    }

    public ArrayList<List<Integer>> getPositions() {
        return this.positions;
    }

    public boolean step() {
        for (int i=0; i<this.numSquares(); i++) {
            stepSingle(i);
        }
        if (ate()) { handleEaten(); }
        return true;
    }

    public void handleEaten() {
        foodPosition = new Pair<Integer, Integer>(randomCoordinate(), randomCoordinate());
        addSquare(getX(numSquares()-1)-1, getY(numSquares()-1), getDirection(numSquares()-1));
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

    public boolean stepSingle(int index) {
        Log.e(String.valueOf(getDirection(index)),"d");
        switch ((int)getDirection(index)) {
            case 0:
                this.setPosition(index, getX(index)+1, getY(index)-1);
            case 1:
                this.setPosition(index, getX(index)+1, getY(index)+1);
            case 2:
                this.setPosition(index, getX(index)-1, getY(index)+1);
            case 3:
                this.setPosition(index, getX(index), getY(index)-1);
        }
        return true;
    }

    public void setPosition(int index, int x, int y) {
        setX(index, x);
        setY(index, y);
    }

    public int getX(int index) {
        return this.getPositions().get(index).get(0);
    }

    public int getY(int index) {
        return this.getPositions().get(index).get(1);
    }

    public int getDirection(int index) {
        return this.getPositions().get(index).get(2);
    }

    public void setDirection(int index, int direction) {
        this.getPositions().get(index).set(2, direction);
    }

    public void setX(int index, int x) {
        this.getPositions().get(index).set(0, x);
    }

    public void setY(int index, int y) {
        this.getPositions().get(index).set(1, y);
    }

    public boolean setLeadDirection(int direction) {
        this.setDirection(0, direction);
        return true;
    }

}
