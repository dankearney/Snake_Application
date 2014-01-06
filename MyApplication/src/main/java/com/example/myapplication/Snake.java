package com.example.myapplication;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Dan on 1/5/14.
 */
public class Snake {

    public ArrayList<List<Integer>> positions = new ArrayList<List<Integer>>();
    public Pair<Integer, Integer> foodPosition;

    public Snake() {
        addSquare(0, 0, 0);
    }

    public void addSquare(int x, int y, int direction) {
        ArrayList<Integer> newSquare = new ArrayList<Integer>();
        newSquare.add(0);
        newSquare.add(0);
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
        return true;
    }

    public int numSquares() {
        return this.positions.size();
    }

    public boolean stepSingle(int index) {
        switch (getDirection(index)) {
            case 0:
                this.setPosition(index, getX(index)+1, getY(index), 0);
            case 1:
                this.setPosition(index, getX(index), getY(index)+1, 1);
            case 2:
                this.setPosition(index, getX(index)-1, getY(index), 2);
            case 3:
                this.setPosition(index, getX(index), getY(index)-1, 3);
            default:
                this.setPosition(index, getX(index)+1, getY(index), 0);
        }
        return true;
    }

    public void setPosition(int index, int x, int y, int direction) {
        ArrayList<Integer> newPosition = new ArrayList<Integer>();
        newPosition.add(x);
        newPosition.add(y);
        newPosition.add(direction);
        this.getPositions().set(index, newPosition);
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

}
