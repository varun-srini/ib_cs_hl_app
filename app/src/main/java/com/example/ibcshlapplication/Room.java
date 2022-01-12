package com.example.ibcshlapplication;

import androidx.annotation.NonNull;

import java.util.Locale;
import java.util.*;
public class Room implements Comparator<Room> {
    private int hallway;
    private int ind;
    private double weight;
    private int nw;


    public Room(){}

    public Room(int ind, double weight) {
        nw = 1;
        this.weight = weight;
        this.ind=ind;
        /*
        char re = name.charAt(0);
        int first = (int) re - 64;
        String ret = Integer.toBinaryString(first);
        ret += Integer.toBinaryString(Integer.parseInt(String.valueOf(name.charAt(1)))-1);
        ret += name.charAt(2);
        ret += Integer.toBinaryString(Integer.parseInt(String.valueOf(name.charAt(3))));
        ind = Integer.parseInt(ret, 2);
        */
    }

    public int getHallway() {
        return hallway;
    }

    public int getInd() {
        return ind;
    }

    public void setHallway(int h) {this.hallway =h;}

    public void update_weight(int w) {
        weight *= nw;
        weight += w;
        nw++;
        weight /= nw;
    }

    public double getWeight() {
        return weight;
    }

    @Override public int compare(Room r1, Room r2) {
        if (r1.weight < r2.weight)
            return -1;

        if (r1.weight > r2.weight)
            return 1;

        return 0;
    }


}
